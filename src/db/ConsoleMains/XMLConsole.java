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

		boolean tables_exist = sql_manager.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = sql_manager.Create_tables();
		}
		
		if(everything_ok) {
		
			
			BiomaterialList list = new BiomaterialList();
			list.setBiomaterials(sql_manager.List_all_biomaterials());
			
			manager.Java2XmlBiomaterial(list);
			//el DTD chacker nos dice que el external-Biomaterial.xml es valido y esta bien formado. entonces ni idea del error siguiente: 
			
			manager.Xml2JavaBiomaterial();
			//Must transform XML to html document - WE MUST VERIFFY IT WHEN ERROR IS SOLVED
			//manager.Xml2HtmlBiomaterial();
			
	}
		
	}
	
}
