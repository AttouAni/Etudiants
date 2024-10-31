package tp8;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAOImpl implements EtudiantDAO {

	@Override
	public List<Etudiant> findAll()
	{
        try
        {
          Connection cnx = Connexion.getInstance();

          Statement stmt = cnx.createStatement();

          ResultSet rs = stmt.executeQuery("select * from Etudiant");

          List<Etudiant> liste = new ArrayList<Etudiant>();

          while (rs.next())
          {
        	  Etudiant e= new Etudiant();

	          e.setCin(rs.getInt("cin"));
	          e.setNom(rs.getString("nom"));
	          e.setEtat(rs.getBoolean("etat"));

	          liste.add(e);                                                                     
          }

          rs.close();
          stmt.close();

          return liste;
        }
        catch (SQLException e)
        {
               e.printStackTrace();
        }

        return null;
	}

	@Override
	public Etudiant findByCin(int c)
	{
	    try
	    {
	        Connection cnx = Connexion.getInstance();
	        Statement stmt = cnx.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Etudiant WHERE cin = " + c);

	        if (rs.next())
	        {
	            Etudiant e = new Etudiant();
	            e.setCin(rs.getInt("cin"));
	            e.setNom(rs.getString("nom"));
	            e.setEtat(rs.getBoolean("etat"));

	            rs.close();
	            stmt.close();
	            return e;
	        }
	    }
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public int insert(Etudiant e)
	{
	    try
	    {
	        Connection cnx = Connexion.getInstance();
	        Statement stmt = cnx.createStatement();
	        String query = String.format("INSERT INTO Etudiant(cin, nom, etat) VALUES (%d, '%s', %b)",
	                e.getCin(), e.getNom(), e.getEtat());
	        int rowsAffected = stmt.executeUpdate(query);
	        stmt.close();
	        return rowsAffected;
	    }
	    catch (SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	    return 0;
	}

	@Override
	public int update(Etudiant e)
	{
	    try
	    {
	        Connection cnx = Connexion.getInstance();
	        Statement stmt = cnx.createStatement();
	        String query = String.format("UPDATE Etudiant SET nom = '%s', etat = %b WHERE cin = %d",
	                e.getNom(), e.getEtat(), e.getCin());
	        int rowsAffected = stmt.executeUpdate(query);
	        stmt.close();
	        return rowsAffected;
	    }
	    catch (SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	    return 0;
	}

	@Override
	public int delete(int c)
	{
	    try
	    {
	        Connection cnx = Connexion.getInstance();
	        Statement stmt = cnx.createStatement();
	        String query = "DELETE FROM Etudiant WHERE cin = " + c;
	        int rowsAffected = stmt.executeUpdate(query);
	        stmt.close();
	        return rowsAffected;
	    }
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	    }
	    return 0;
	}
}
