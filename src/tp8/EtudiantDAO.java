package tp8;

import java.util.List;

public interface EtudiantDAO { 
	public List<Etudiant> findAll(); 
	public Etudiant findByCin(int c); 
	public int insert(Etudiant e); 
	public int update(Etudiant e); 
	public int delete(int c); 
 } 
