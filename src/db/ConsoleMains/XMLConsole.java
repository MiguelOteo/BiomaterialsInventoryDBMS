package db.ConsoleMains;

import java.io.IOException;
import javax.xml.bind.JAXBException;

import db.jdbc.SQLManager;
import db.pojos.BiomaterialList;
import db.xml.XMLManager;

public class XMLConsole {

	private static XMLManager manager;
	private static SQLManager sql_manager;
	
	/**
	 * @param args
	 * @throws JAXBException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main (String args[]) throws JAXBException, NumberFormatException, IOException {
		
		manager = new XMLManager();
		sql_manager = new SQLManager();
		
		boolean everything_ok = sql_manager.Stablish_connection();

			BiomaterialList list = new BiomaterialList();
			list.setBiomaterials(sql_manager.List_all_biomaterials());
			
			manager.Java2XmlBiomaterial(list);
			manager.Xml2JavaBiomaterial();
			manager.Xml2HtmlBiomaterial();
			
	}	
}
