package db.xml;


import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.BiomaterialList;


public class XMLManager {

	private static final String PERSISTENCE_PROVIDER = "project-provider";
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	public XMLManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void Xml2JavaBiomaterial() throws JAXBException{
		// Create the JAXBContext
				JAXBContext jaxbContext = JAXBContext.newInstance(BiomaterialList.class);
				// Get the unmarshaller
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

				// Use the Unmarshaller to unmarshall the XML document from a file
				File file = new File("./xmls/External-Biomaterial.xml");
				
				BiomaterialList biomaterial = (BiomaterialList) unmarshaller.unmarshal(file);
								
				// Store the biomaterial in the database
				// Create entity manager
				factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
				em = factory.createEntityManager();
				em.getTransaction().begin();
				em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
				em.getTransaction().commit();

				// Create a transaction
				EntityTransaction tx1 = em.getTransaction();

				// Start transaction
				tx1.begin();

				// Persist
				SQLManager sql_manager = new SQLManager();
				sql_manager.Stablish_connection();
				
	            List<Biomaterial> biomat_for_persist = sql_manager.List_all_biomaterials();
	            for (Biomaterial biomat: biomat_for_persist) {
	            	sql_manager.Insert_new_biomaterial(biomat);
	            }
	            sql_manager.Close_connection();
	            
				// End transaction
				tx1.commit();
	}
	
	
	public void Java2XmlBiomaterial(BiomaterialList list) throws JAXBException {
		
		
		em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(BiomaterialList.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		
		File file = new File("./xmls/Sample-Biomaterial.xml");
		// Use the Marshaller to marshal the Java object to a file
		marshaller.marshal(list, file);
		// Printout
		marshaller.marshal(list, System.out);
		
	}
	
	
	public void Xml2HtmlBiomaterial() {
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File("./xmls/Biomaterial-Style.xslt")));
			transformer.transform(new StreamSource(new File("./xmls/External-Biomaterial.xml")),new StreamResult(new File("./xmls/BENGMAT.html")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
