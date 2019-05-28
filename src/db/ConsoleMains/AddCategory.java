package db.ConsoleMains;

import java.util.List;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Benefits;
import db.pojos.Category;
import db.pojos.Client;

public class AddCategory {

	public static void main(String[] args) {
		
		JPAManager manager = new JPAManager();
		SQLManager manager2 = new SQLManager();
		manager.Stablish_connection();
		manager2.Stablish_connection();
		manager2.Create_tables();
		
		// "None", 500, 0                +
		// "Bronze III", 1000, 501       +
		// "Bronze II", 2500, 1001       +
		// "Bronze I", 5000, 2501        + 
		// "Silver II", 8000, 5001      +
		// "Silver I", 16000, 8001      +
		// "Gold II", 36000, 16001       +
		// "Gold I", 50000, 36001
		// "Platinum II" 100000, 50001
		// "Platinum I" 200000, 100001
		// "Diamond", 500000, 200001
		
		// (float) 0, 0          +
		// (float) 5, 2          +
		// (float) 10, 4         +
		// (float) 15, 8         +
		// (float) 20, 16        +
		// (float) 25, 32        +
		// (float) 30, 64
		// (float) 40, 128
		// (float) 50, 256
		
		Category cat0 = new Category("None", 500, 0);
		Benefits ben0 = new Benefits((float) 0, 0 );
		manager.Insert_new_benefit(ben0);
		cat0.setBenefits(ben0);
		manager.Insert_new_category(cat0);
		Category cat1 = new Category("Bronze III", 1000, 501);
		Benefits ben1 = new Benefits((float) 5, 2 );
		manager.Insert_new_benefit(ben1);
		cat1.setBenefits(ben1);
		manager.Insert_new_category(cat1);
		Category cat2 = new Category("Bronze II", 2500, 1001 );
		Benefits ben2 = new Benefits((float) 10, 4);
		manager.Insert_new_benefit(ben2);
		cat2.setBenefits(ben2);
		manager.Insert_new_category(cat2);
		Category cat3 = new Category("Bronze I", 5000, 2501  );
		Benefits ben3 = new Benefits((float) 15, 8);
		manager.Insert_new_benefit(ben3);
		cat3.setBenefits(ben3);
		manager.Insert_new_category(cat3);
		Category cat4 = new Category("Silver II", 8000, 5001);
		Benefits ben4 = new Benefits((float) 20, 16);
		manager.Insert_new_benefit(ben4);
		cat4.setBenefits(ben4);
		manager.Insert_new_category(cat4);
		Category cat5 = new Category("Silver I", 16000, 8001 );
		Benefits ben5 = new Benefits((float) 25, 32);
		manager.Insert_new_benefit(ben5);
		cat5.setBenefits(ben5);
		manager.Insert_new_category(cat5);
		Category cat6 = new Category("Gold II", 36000, 16001);
		Benefits ben6 = new Benefits((float) 30, 64);
		manager.Insert_new_benefit(ben6);
		cat6.setBenefits(ben6);
		manager.Insert_new_category(cat6);
		Category cat7 = new Category("Gold I", 50000, 36001);
		Benefits ben7 = new Benefits((float) 35, 128);
		manager.Insert_new_benefit(ben7);
		cat7.setBenefits(ben7);
		manager.Insert_new_category(cat7);
		Category cat8 = new Category("Platinum II", 100000, 50001);
		Benefits ben8 = new Benefits( (float) 40, 256);
		manager.Insert_new_benefit(ben8);
		cat8.setBenefits(ben8);
		manager.Insert_new_category(cat8);
		Category cat9 = new Category("Platinum I", 200000, 100001);
		Benefits ben9 = new Benefits((float) 45, 518);
		manager.Insert_new_benefit(ben9);
		cat9.setBenefits(ben9);
		manager.Insert_new_category(cat9);
		Category cat10 = new Category("Diamond", 500000, 200001);
		Benefits ben10 = new Benefits((float) 50, 1024);
		manager.Insert_new_benefit(ben10);
		cat10.setBenefits(ben10);
		manager.Insert_new_category(cat10);
		
	}	
}
