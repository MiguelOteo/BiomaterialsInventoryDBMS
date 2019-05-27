package db.ConsoleMains;

import db.jpa.JPAManager;
import db.pojos.Benefits;


public class DeleteBenefits {

	public static void main(String args[]) {
		
		JPAManager manager = new JPAManager();
		manager.Stablish_connection();
		
		Benefits benefits = manager.Search_benefits_by_id(2);
		manager.Delete_stored_benefits(benefits);
	} 
}
