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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import pl.asap.entity.Notes;
import pl.asap.junk.NotesScreenTable;
import pl.asap.models.MainTableModel;
import pl.asap.models.NotesModel;
import pl.asap.transactions.NoteDelete;
import pl.asap.transactions.ReadNotes;
import pl.test.notes.NotesScreen;
import pl.test.notes.NotesView;
import pl.test.table.TableBean;
import pl.test.table.TableElement;
import pl.test.table.TableGui;

@SuppressWarnings("serial")
public class PopupContent extends JPopupMenu implements PropertyChangeListener, ActionListener {

	private JTable lista;
	private AbstractTableModel data;
	private JFrame frame;
	public PopupContent(JTable list, AbstractTableModel dane, String[] popupStr)	{
		super();
		lista=list;
		data = dane;
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
			new OpForm2("Edycja postępowania", realSelectedRow, (MainTableModel) data);
		}
		if (u.equals("zakończ postępowanie"))	{
			int selectedRow = lista.getSelectedRow();
			int realSelectedRow = lista.convertRowIndexToModel(selectedRow);
			if (data.getValueAt(realSelectedRow, 2)==null || "".equals(data.getValueAt(realSelectedRow, 2)))	{
				JOptionPane.showMessageDialog(frame, "Nie można zakończyć tego postępowania"); //tu zrobić ostrzeżenie i tak/nie
			}
			else {
				((MainTableModel) data).cellUpdate("zakonczone", realSelectedRow, 4);
				new Zapis((MainTableModel) data);
				new FolderCreator().moveFolder(getFolder(realSelectedRow), true);
			}
		
		}
		if (u.equals("zmień daty"))	{
			new DateChangeForm2((MainTableModel) data, lista.convertRowIndexToModel(lista.getSelectedRow()));
		}
		if (u.equals("zawieś postepowanie"))	{
			int selectedRow = lista.getSelectedRow();
			int realSelectedRow = lista.convertRowIndexToModel(selectedRow);
			if(data.getValueAt(realSelectedRow, 4).equals("aktywne"))	{
				((MainTableModel) data).cellUpdate("zawieszone", realSelectedRow, 4);
				new Zapis((MainTableModel) data);
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
				((MainTableModel) data).cellUpdate("aktywne", realSelectedRow, 4);
				new Zapis((MainTableModel) data);
				
			}
		}
		if (u.equals("notatki"))	{
			
			int realSelectedRow = lista.convertRowIndexToModel(lista.getSelectedRow());
			final int idPostepowanie = ((MainTableModel) data).getId(realSelectedRow);

			NotesModel nm = new NotesModel(idPostepowanie);

			System.out.println("---------------> "+this.getClass());
			TableBean tb = new TableBean(nm, 1);
			TableElement te = new TableElement(nm);
			tb.addPropertyChangeListener(te);
	
			String ZZPZ = data.getValueAt(realSelectedRow , 0)+", "+data.getValueAt(realSelectedRow , 1);
//			new NotesScreenTable(nm, ZZPZ);
			
			new TableGui(tb, te, idPostepowanie);
			
//			new NotesScreen(ZZPZ, nm, nv, idPostepowanie);

		}
		if (u.equals("delete"))	{
			
			NotesModel model = (NotesModel) lista.getModel();
			
			int row = lista.convertRowIndexToModel(lista.getSelectedRow());
			System.out.println("----------del------->"+row);
			model.deleteNote(row);
//			new NoteDelete(model.getNote(), model.getNoteId(row));
			
		}
	}

}
