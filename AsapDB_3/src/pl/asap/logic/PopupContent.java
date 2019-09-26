package pl.asap.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import pl.asap.models.MainTableModel;
import pl.asap.models.NotesModel;
import pl.asap.note.TableBean;
import pl.asap.note.TableElement;
import pl.asap.note.TableGui;
import pl.asap.note2.NoteEditForm;
import pl.asap.note2.NotesTable;

@SuppressWarnings("serial")
public class PopupContent extends JPopupMenu implements PropertyChangeListener, ActionListener {

	private JTable lista;
	private AbstractTableModel data;
	private JFrame frame;
	private String ZZPZ;
	

	public PopupContent(JTable list, AbstractTableModel dane, String[] popupStr)	{
		super();
		lista=list;
		data = dane;
		doMassAddMenu(this, popupStr);		
		setZZPZ("");
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
	public String getZZPZ() {
		return ZZPZ;
	}
	public void setZZPZ(String zZPZ) {
		ZZPZ = zZPZ;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String u = e.getActionCommand();
		if (u.equals("modyfikacja"))	{
			int selectedRow = lista.getSelectedRow();
			int realSelectedRow = lista.convertRowIndexToModel(selectedRow);
			OpForm2.getInstance("Edycja postępowania", realSelectedRow, (MainTableModel) data);
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
//		"otwórz folder"
		
		if (u.equals("otwórz folder"))	{
			
		}
		if (u.equals("notatki"))	{			
			
//			TableBean tb = new TableBean(nm, 1);
//			TableElement te = new TableElement(nm);
//			tb.addPropertyChangeListener(te);
//			String post = data.getValueAt(realSelectedRow , 0)+", "+data.getValueAt(realSelectedRow , 1);
//			setZZPZ(post);
//			System.out.println(ZZPZ+" --------112233-------> "+this.getClass());
//			TableGui.getInstance(tb, te, idPostepowanie);
			int realSelectedRow = lista.convertRowIndexToModel(lista.getSelectedRow());
			final int idPostepowanie = ((MainTableModel) data).getId(realSelectedRow);
			System.out.println("PopupContent-idPostepowanie-"+idPostepowanie);
			NotesModel nm = new NotesModel(idPostepowanie);
			
//			TableBean tb = new TableBean(nm, 1);
//			TableElement te = new TableElement(nm);
//			tb.addPropertyChangeListener(te);
			String post = data.getValueAt(realSelectedRow , 0)+", "+data.getValueAt(realSelectedRow , 1);
			setZZPZ(post);
			System.out.println(ZZPZ+" --------112233-------> "+this.getClass());
			NotesTable.getNotesTableInstance(idPostepowanie);

		}
		if (u.equals("delete"))	{			
			NotesModel model = (NotesModel) lista.getModel();			
			int row = lista.convertRowIndexToModel(lista.getSelectedRow());
			System.out.println("----------del------->"+row);
			model.deleteNote(row);
			
		}
		if (u.equals("edit"))	{
			int realSelectedRow = lista.convertRowIndexToModel(lista.getSelectedRow());
			
			
			NotesModel model = (NotesModel) lista.getModel();
			
			System.out.println("odpalam edycję notatki"+model.getNoteId(realSelectedRow));

			NoteEditForm.getInstance(getZZPZ(), realSelectedRow, model);
			
		}
	}




}
