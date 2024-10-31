package tp8;

public class Etudiant { 
private int cin; 
private String nom; 
private Boolean etat; 
public Etudiant() { 
}  
public Etudiant(int cin, String nom, Boolean etat) { 
this.cin = cin; 
this.nom = nom; 
this.etat = etat; 
} 
 
public int getCin() { 
return cin; 
} 
public void setCin(int cin) { 
this.cin = cin; 
} 
public String getNom() { 
return nom; 
} 
public void setNom(String nom) { 
this.nom = nom; 
} 
public Boolean getEtat() { 
return etat; 
} 
public void setEtat(Boolean etat) { 
this.etat = etat; 
} 
@Override 
public String toString() { 
return "Etudiant [cin=" + cin + ", nom=" + nom + ", etat=" + etat + "]";
} 
}
