package pl.asap.transactions;

import javax.persistence.Query;
import javax.swing.JOptionPane;

import pl.asap.entity.EntityBase;
import pl.asap.models.NotesModel;

public class NoteDelete extends TransBlank {
	

	public NoteDelete(EntityBase bean, int noteId) {		//to wypadałoby zuniwersalizować
		super(bean);
	
		int a = JOptionPane.showConfirmDialog(null,"czy naprawdę chcesz skasować notatkę nr "+noteId,"?", JOptionPane.YES_NO_OPTION);

		System.out.println(a);
		   // Tak a=0, nie a=1, anuluj a=2;
		if (a==0)	{
			session.beginTransaction();
			String delete = "delete Notes n where n.noteId=:noteId";
			Query query = session.createQuery(delete);
			query.setParameter("noteId", noteId);
			int rows = query.executeUpdate();
			System.out.println("ilosc usunietych notatek: " + rows);		//
			session.getTransaction().commit();
			factory.close();
//			model.deleteNote(noteId);
			JOptionPane.showMessageDialog(null, "Notatka została bezpowrotnie usunięta");	//to potem usunąć
		 }
	}
}
