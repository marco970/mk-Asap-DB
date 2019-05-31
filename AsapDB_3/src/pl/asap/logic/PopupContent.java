package pl.asap.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import pl.asap.entity.Notes;
import pl.asap.models.MainTableModel;
import pl.asap.models.NotesModel;
import pl.test.notes.NotesScreen;
import pl.test.notes.NotesView;
import pl.test.notes.ReadNotes;

@SuppressWarnings("serial")
public class PopupContent extends JPopupMenu implements PropertyChangeListener, ActionListener {

	private JTable lista;
	private MainTableModel data;
	private JFrame frame;
	public PopupContent(JTable list, MainTableModel dane, JFrame fram, String[] popupStr)	{
		super();
		lista=list;
		data = dane;
		frame = fram;

		//String[] popupStr = {"modyfikacja", "zmień daty", "zakończ postępowanie", "zawieś postepowanie"};
		doMassAddMenu(this, popupStr);		
	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.removeAll();
		String[] a = (String[]) evt.getNewValue();
		doMassAddMenu(this, a);
		
	}
	public void doMassAddMenu(JPopupMenu popup, String...args)	{
		for (int i =0; i<=args.length-1; i++)	{
			JMenuItem menuItem = mi(args[i]);	
			popup.add(menuItem);
		}
	}
	
	public JMenuItem mi(String str)	{
		JMenuItem mi = new JMenuItem(str);
		mi.addActionListener(this);	
		mi.setActionCommand(str);
		return mi;
	}
	public String getFolder(int rowNr)	{
		String path = "";
		String numerZZ = data.getValueAt(rowNr, 0).toString().substring(6);
		FolderCreator folder = new FolderCreator();
		String myPath = folder.getDefaultPath() + folder.getAktywne();
		
		File[] directories = new File(myPath).listFiles(File::isDirectory);
		
		if (myPath.length() > 0) {		//
			for (int i = 0; i <= directories.length - 1; i++) {
				if (directories[i].toString().substring(myPath.length(), +myPath.length() + 7).equals(numerZZ))	{
					path = directories[i].toString().substring(myPath.length());
				}
			}
		}
		
		return path;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String u = e.getActionCommand();
	
		if (u.equals("modyfikacja"))	{
			int selectedRow = lista.getSelectedRow();
			int realSelectedRow = lista.convertRowIndexToModel(selectedRow);
			new OpForm2("Edycja postępowania", realSelectedRow, data);
		}
		if (u.equals("zakończ postępowanie"))	{
			int selectedRow = lista.getSelectedRow();
			int realSelectedRow = lista.convertRowIndexToModel(selectedRow);
			if (data.getValueAt(realSelectedRow, 2)==null || "".equals(data.getValueAt(realSelectedRow, 2)))	{
				JOptionPane.showMessageDialog(frame, "Nie można zakończyć tego postępowania"); //tu zrobić ostrzeżenie i tak/nie
			}
			else {
				data.cellUpdate("zakonczone", realSelectedRow, 4);
				new Zapis(data);
				new FolderCreator().moveFolder(getFolder(realSelectedRow), true);
			}
		
		}
		if (u.equals("zmień daty"))	{
			new DateChangeForm2(data, lista.convertRowIndexToModel(lista.getSelectedRow()));
		}
		if (u.equals("zawieś postepowanie"))	{
			int selectedRow = lista.getSelectedRow();
			int realSelectedRow = lista.convertRowIndexToModel(selectedRow);
			if(data.getValueAt(realSelectedRow, 4).equals("aktywne"))	{
				data.cellUpdate("zawieszone", realSelectedRow, 4);
				new Zapis(data);
			}
			else if (data.getValueAt(realSelectedRow, 4).equals("zakonczone")) {
				JOptionPane.showMessageDialog(frame, "Nie można zawiesić zamkniętego postępowania"); 
			}
			else if (data.getValueAt(realSelectedRow, 4).equals("zakonczone")) {
				JOptionPane.showMessageDialog(frame, "Postępowanie już jest zawieszone");
			}
		}
		if (u.equals("odwieś postępowanie"))	{
			int realSelectedRow = lista.convertRowIndexToModel(lista.getSelectedRow());
			if (data.getValueAt(realSelectedRow, 4).equals("zakonczone")) {
				data.cellUpdate("aktywne", realSelectedRow, 4);
				new Zapis(data);
				
			}
		}
		if (u.equals("notatki"))	{
			//private String[] popupStr = {"modyfikacja", "zmień daty", "zakończ postępowanie", "zawieś postepowanie", "otwórz folder","notatki"};
			int realSelectedRow = lista.convertRowIndexToModel(lista.getSelectedRow());
			final int idPostepowanie = data.getId(realSelectedRow);
//			System.out.println("row= "+realSelectedRow+" id= "+idPostepowanie);
//			--> to pełni rolę Main -> wywyłujemy obiekt bean i view
			
			
			ReadNotes rn = new ReadNotes(idPostepowanie); //to do modelu
			ArrayList<Notes> notes = rn.getNotes(); //to tez?
			
			NotesModel nm = new NotesModel(notes, idPostepowanie);
			NotesView nv = new NotesView(nm, idPostepowanie);
			nm.addPropertyChangeListener(nv);
			
			
			String ZZPZ = data.getValueAt(realSelectedRow , 0)+", "+data.getValueAt(realSelectedRow , 1);
			
			new NotesScreen(ZZPZ, nm, nv, idPostepowanie);
			

			
		}
	}

}
