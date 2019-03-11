package db.UImenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import db.jdbc.SQLManager;

public class Menu {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		SQLManager manager = new SQLManager();
		boolean done = manager.Stablish_connection();
		boolean tables_done = manager.Check_if_tables_exist();
			
			if(tables_done == true) {
				System.out.print("\n The tables have been charged.");
			} else {
				System.out.print("\n ...Charging tables, wait a moment...");
				done = manager.Create_tables();
			}
			
			if (done==true) {
				
			System.out.println("\n ____________________________________________________\n");
            System.out.println("SELECT AN OPTION: \n"
                    + "1) View \n"
                    + "2) Insert \n" 
                    + "3) Modify \n"
                    + "4) Delete\n"
                    + "5) Exit");
			System.out.println("____________________________________________________");
            
			int option, optionView, optionInsert, optionModify, optionDelete;
			String readString;
			
            do {
             	System.out.println("Option number[1-5]: ");
			 	readString = console.readLine();
		        option = Integer.parseInt(readString);
			} while (option < 1 || option > 6);	

	     switch(option) {
	     
	     	//View//
	     	case 1:
	            System.out.println("WHAT WOULD YOU LIKE TO SEE: "
	                     + "\n1) Client\t"
	                     + "\n2) Biomaterial\t" 
	                     + "\n3) Transaction\t"
	                     + "\n4) Category\t"
	                     + "\n5) Benefits\t"
	                     + "\n6) Maintenance\t"
	                     + "\n7) Utility\t"
	                     + ""
	                     + "\nOption number:\t ");
	
	            do {
	             	System.out.println("Option number(1-7): ");
	             	readString = console.readLine();
		            optionView = Integer.parseInt(readString);
				} while (optionView < 1 || optionView > 6);	
	             
	             switch (optionView) {
	             
	             	case 1:
	             	case 2:
	             	case 3:
	             	case 4:
	             	case 5:
	             	case 6:
	             	case 7:
	             	}
	          
	          //Insert//
	          case 2:
	        	  System.out.println("WHAT WOULD YOU LIKE TO INSERT:\n"
                		 + "\n1) Client\t"
 	                     + "\n2) Biomaterial\t" 
 	                     + "\n3) Transaction\t"
 	                     + "\n4) Category\t"
 	                     + "\n5) Benefits\t"
 	                     + "\n6) Maintenance\t"
 	                     + "\n7) Utility\t"
 	                     + ""
 	                     + "\nOption number:\t ");

	        	  do {
		             	System.out.println("Option number(1-7): ");
		             	readString = console.readLine();
		             	optionInsert = Integer.parseInt(readString);
					} while (optionInsert < 1 || optionInsert > 6);	
                  
                  switch (optionInsert) {
                  
	                  case 1:
	                  case 2:
	                  case 3:
	                  case 4:
	                  case 5:
	                  case 6:
	             	  case 7:
                  }
                  
              //Modify//    
	          case 3:
		            System.out.println("WHAT WOULD YOU LIKE TO MODIFY: "
		                     + "\n1) Client\t"
		                     + "\n2) Biomaterial\t" 
		                     + "\n3) Transaction\t"
		                     + "\n4) Category\t"
		                     + "\n5) Benefits\t"
		                     + "\n6) Maintenance\t"
		                     + "\n7) Utility\t"
		                     + ""
		                     + "\nOption number:\t ");
		
		            do {
		             	System.out.println("Option number(1-7): ");
		             	readString = console.readLine();
		             	optionModify = Integer.parseInt(readString);
					} while (optionModify < 1 || optionModify > 6);	
		             
		             switch (optionModify) {
		             
		             	case 1:
		             	case 2:
		             	case 3:
		             	case 4:
		             	case 5:
		             	case 6:
		             	case 7:
		             	}
		             
		      //Delete//
	          case 4:
		            System.out.println("WHAT WOULD YOU LIKE TO DELETE: "
		                     + "\n1) Client\t"
		                     + "\n2) Biomaterial\t" 
		                     + "\n3) Transaction\t"
		                     + "\n4) Category\t"
		                     + "\n5) Benefits\t"
		                     + "\n6) Maintenance\t"
		                     + "\n7) Utility\t"
		                     + ""
		                     + "\nOption number:\t ");
		
		            do {
		             	System.out.println("Option number(1-7): ");
		             	readString = console.readLine();
		             	optionDelete = Integer.parseInt(readString);
					} while (optionDelete < 1 || optionDelete > 6);	
		             
		             switch (optionDelete) {
		             
		             	case 1:
		             	case 2:
		             	case 3:
		             	case 4:
		             	case 5:
		             	case 6:
		             	case 7:
		             	}
		             
		      //Exit-close connection//
	          /*case 5:
	        	  boolean close_done = manager.Close_connection();
					
					if (close_done == true) {
						System.out.println("\n Program closed. ");} 
					else {
						System.out.println("\n Closing the program has caused a problem. ");
					}
					System.exit(0);*/
	     		}
	     
			}else {
				System.out.println("\n Failed to stablish aconection. ");
				manager.Close_connection();
			}
    }
}