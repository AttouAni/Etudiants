package tp8;

import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FrmAffichage extends JFrame implements ActionListener
{
	JPanel panNord = new JPanel();
	JPanel panCentre = new JPanel();
	JPanel panSud = new JPanel();
	
	JLabel lblCbo = new JLabel("CIN Etudiant");
	JComboBox<Integer>  cboCin = new JComboBox<Integer>();
	JLabel lblNom = new JLabel("Nom");
	JLabel lblCin = new JLabel("CIN");
	JLabel lblVide = new JLabel("");
	JTextField txtNom = new JTextField();
	JTextField txtCin = new JTextField();
	JCheckBox chkRed = new JCheckBox("Redoublant");
	JButton btnVider = new JButton ("Vider");
	JButton btnAjouter = new JButton ("Ajouter");
	JButton btnModifier = new JButton ("Modifier");
	JButton btnSupprimer = new JButton ("Supprimer");
	
	public void initGUI()
	{
	    setTitle("Gestion des Etudiants");
	    setSize(500, 200);

	    panNord.setLayout(new FlowLayout());
	    panNord.add(lblCbo);
	    panNord.add(cboCin);
	    
	    panCentre.setLayout(new GridLayout(0, 2));
	    panCentre.add(lblCin);
	    panCentre.add(txtCin);
	    panCentre.add(lblNom);
	    panCentre.add(txtNom);
	    panCentre.add(lblVide);
	    panCentre.add(chkRed);
	    
	    panSud.add(btnVider);
	    panSud.add(btnAjouter);
	    panSud.add(btnModifier);
	    panSud.add(btnSupprimer);

	    add(panNord, BorderLayout.NORTH);
	    add(panCentre, BorderLayout.CENTER);
	    add(panSud, BorderLayout.SOUTH);

	    btnVider.addActionListener(this);
	    btnAjouter.addActionListener(this);
	    btnModifier.addActionListener(this);
	    btnSupprimer.addActionListener(this);
	    cboCin.addActionListener(this);
	}

	public FrmAffichage()
	{
	    initGUI();
	    this.refresh();
	    this.vider();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
	    if (e.getSource() == btnVider)
	    {
	        vider();
	    }
	    else if (e.getSource() == btnAjouter)
	    {
	        Etudiant etudiant = new Etudiant();
	        etudiant.setCin(Integer.parseInt(txtCin.getText()));
	        etudiant.setNom(txtNom.getText());
	        etudiant.setEtat(chkRed.isSelected());

	        EtudiantDAO dao = new EtudiantDAOImpl();
	        int rowsAffected = dao.insert(etudiant);

	        if (rowsAffected > 0)
	        {
	            System.out.println("Etudiant ajouté avec succès !");
	            refresh(); 
	            vider();
	        }
	        else
	        {
	            System.out.println("Erreur lors de l'ajout de l'étudiant.");
	        }
	    }
	    else if (e.getSource() == btnModifier)
	    {
	        Etudiant etudiant = new Etudiant();
	        etudiant.setCin(Integer.parseInt(txtCin.getText()));
	        etudiant.setNom(txtNom.getText());
	        etudiant.setEtat(chkRed.isSelected());

	        EtudiantDAO dao = new EtudiantDAOImpl();
	        int rowsAffected = dao.update(etudiant);

	        if (rowsAffected > 0)
	        {
	            System.out.println("Etudiant mis à jour avec succès !");
	            refresh();
	            vider();
	        }
	        else
	        {
	            System.out.println("Erreur lors de la mise à jour de l'étudiant.");
	        }
	    }
	    else if (e.getSource() == btnSupprimer)
	    {
	        int cin = Integer.valueOf(txtCin.getText());

	        EtudiantDAO dao = new EtudiantDAOImpl();
	        int rowsAffected = dao.delete(cin);

	        if (rowsAffected > 0)
	        {
	            System.out.println("Etudiant supprimé avec succès !");
	            refresh();
	            vider();
	        }
	        else
	        {
	            System.out.println("Erreur lors de la suppression de l'étudiant.");
	        }
	    }
	    
	    	if(e.getSource()==cboCin && cboCin.getSelectedIndex()>=0) {
	    		btnAjouter.setEnabled(false);
	    		txtCin.setEnabled(false);
		    	int id = (int) cboCin.getSelectedItem();
		    	EtudiantDAO dao = new EtudiantDAOImpl();
		    	Etudiant et = dao.findByCin(id);
		    	if(et !=null) {
		    		txtCin.setText(Integer.toString(et.getCin()));
		    		txtNom.setText(et.getNom());
		    		chkRed.setSelected(et.getEtat());
		    	}
	    	
	    	
	    }
	}

	private void vider()
	{
	    txtNom.setText("");
	    txtCin.setText("");
	    chkRed.setSelected(false);
	    btnAjouter.setEnabled(true);
	    txtCin.setEnabled(true);
	}

	public void refresh()
	{
	    cboCin.removeAllItems();
	    EtudiantDAO dao = new EtudiantDAOImpl();
	    List<Etudiant> etudiants = dao.findAll();
	    for (Etudiant etudiant : etudiants) {
	        cboCin.addItem(etudiant.getCin());
	    }
	}
	
	public static void main( String args[] )
	{
	
		FrmAffichage frm = new FrmAffichage();
		frm.setVisible(true);

	}
}