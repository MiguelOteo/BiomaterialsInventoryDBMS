package db.xml;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import db.pojos.Biomaterial;


public class XMLManager {

	private static final String PERSISTENCE_PROVIDER = "persistence.xml";
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	public XMLManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void Xml2JavaBiomaterial(Biomaterial biomaterial) throws JAXBException{
		// Create the JAXBContext
				JAXBContext jaxbContext = JAXBContext.newInstance(Biomaterial.class);
				// Get the unmarshaller
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

				// Use the Unmarshaller to unmarshall the XML document from a file
				File file = new File("./xmls/Biomaterials-Report.xml");
				biomaterial = (Biomaterial) unmarshaller.unmarshal(file);
					
				
				// Store the report in the database
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

				// Persist
				em.persist(biomaterial);
				
				// End transaction
				tx1.commit();
	}
	
	
	public void Java2XmlBiomaterial(Biomaterial biomaterial) throws JAXBException {
		em = Persistence.createEntityManagerFactory("persistence.xml").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Biomaterial.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./xmls/Biomaterial-Report.xml");
		marshaller.marshal(biomaterial, file);
		// Printout
		marshaller.marshal(biomaterial, System.out);

	}
	
}
