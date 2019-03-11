package db.UImenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UIConsole {

	public static void main(String args[]) throws IOException {
		
	  try {
		
		  BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		  char option = '0';
		
	      while(option != '2') {
			
		     option = (char)console.read();
		     switch(option) {

			     case '1': {
			    	 break;
			     }
			     case '2': {
			    	 System.out.print("Programme closed");
			     }
			 }
		  }
	   } catch(Exception e) {
		   
	   }
	}
}
