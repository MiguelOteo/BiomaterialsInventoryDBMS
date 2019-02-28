package UImenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UIConsole {

	public static void main(String args[]) {
	  try {
		  BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		  int option = 0;
		
	      while(option != 2) {
			
		     option = (int)console.read();
		     switch(option) {

			     case 1: {
			    	 break;
			     }
			     case 2: {
			    	 System.out.print("Programme closed");
			     }
			 }
		  }
	   } catch(IOException menu_error) {
		   menu_error.printStackTrace();
		   return;
	   }
	}
}
