package tp8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion { 
	private static Connection connect; 
		String url="jdbc:ucanaccess://" + System.getProperty("user.dir") + "/Etudiant.accdb";
		private Connexion() { 
			try {
				connect=(Connection) DriverManager.getConnection(url,"user", 
			"motdepasse");
			}  
			catch (SQLException e) { 
				e.printStackTrace();
			}
		}
		public static Connection getInstance() {
		if(connect == null) 
			synchronized(Connexion.class) { 
		if(connect == null) {new Connexion();}} 
		return connect;
		} 
} 
