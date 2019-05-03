package db.xml;

import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import db.pojos.Biomaterial;

public class Xml2JavaBiomaterial {

	private static final String PERSISTENCE_PROVIDER = "company-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Biomaterial.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./xmls/External-Biomaterial.xml");
		Biomaterial biomaterial = (Biomaterial) unmarshaller.unmarshal(file);

		// Print the report
		System.out.println("Biomaterial:");
		System.out.println("Name: " + biomaterial.getName_product());
		System.out.println("Biomaterial id: " + biomaterial.getBiomaterial_id());
		System.out.println("Utility: " + biomaterial.getUtility());
		System.out.println("Maintenance: " + biomaterial.getMaintenance());
		System.out.println("Price per unit: " + biomaterial.getPrice_unit());
		System.out.println("Available units: " + biomaterial.getAvailable_units());

		// Store the biomaterial in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();
		
		// End transaction
		tx1.commit();
	}
}
