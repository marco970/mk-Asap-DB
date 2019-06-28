package pl.asap.transactions;

import javax.persistence.Query;

import pl.asap.entity.EntityBase;
import pl.asap.models.NotesModel;

public class NoteDelete extends TransBlank {
	

	public NoteDelete(EntityBase bean, int noteId) {
		super(bean);
		
		session.beginTransaction();
		
		String delete = "delete Notes n where n.noteId=:noteId";
		Query query = session.createQuery(delete);
		query.setParameter("noteId", noteId);
		int rows = query.executeUpdate();

		System.out.println("ilosc usunietych notatek: " + rows);

		
		session.getTransaction().commit();

	
		// zamknięcie obiektu SessionFactory
		factory.close();	

	}

	public static void main(String[] args) {
		NotesModel nm = new NotesModel(46);
		System.out.println(nm.toString());
		new NoteDelete(nm.getNote(), 27);
		
		

	}

}
