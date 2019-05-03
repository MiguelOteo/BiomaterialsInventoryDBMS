package db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import db.pojos.Biomaterial;

public class Java2XmlBiomaterial {

	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	private static void printBiomaterials() {
		Query q1 = em.createNativeQuery("SELECT * FROM biomaterials", Biomaterial.class);
		@SuppressWarnings("unchecked")
		List<Biomaterial> reps = (List<Biomaterial>) q1.getResultList();
		// Print the departments
		for (Biomaterial rep : reps) {
			System.out.println(rep);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// Get the entity manager
		// Note that we are using the class' entity manager
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Biomaterial.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		// Choose the biomaterial to turn into an XML
		// Choose his new id
		printBiomaterials();
		System.out.print("Choose a biomaterial to turn into an XML file:");
		int biomaterial_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM biomaterials WHERE id = ?", Biomaterial.class);
		q2.setParameter(1, biomaterial_id);
		Biomaterial biomaterial = (Biomaterial) q2.getSingleResult();
		
		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./xmls/Sample-Biomaterial.xml");
		marshaller.marshal(biomaterial, file);
		// Printout
		marshaller.marshal(biomaterial, System.out);

	}
}
