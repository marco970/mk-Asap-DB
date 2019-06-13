package pl.test.notes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.asap.entity.Config;
import pl.asap.entity.Lista;
import pl.asap.entity.Notes;
import pl.asap.models.NotesModel;

public class NoteUpdateTest implements TableModelListener {
	
//	private int noteId;

	public NoteUpdateTest()	{
		
	}
	
	public void carryOutUpdate(int noteId, String note) {
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");  
		Date date = new Date(System.currentTimeMillis());  
		String dateModified = date.toString();
		System.out.println("---------------- "+dateModified);
		dateModified = "abc";
		
		Config config = new Config();
		String hibernateConf = config.getHibernateXML();
		Configuration conf = new Configuration();
		conf.configure(hibernateConf);
		conf.addAnnotatedClass(Lista.class);
		conf.addAnnotatedClass(Notes.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
//		int noteId = 19;
//		String note = "półgębkiem nie łykniesz ogórków";
		session.beginTransaction();
		
		String update = "update Notes e set "
				+ "e.dateModified=:dateModified,"
				+ "e.note=:note "
				+ "where e.noteId=:noteId";
		Query query = session.createQuery(update);
		query.setParameter("dateModified", dateModified);
		query.setParameter("note", note);
		query.setParameter("noteId", noteId);
		int recordNo = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(recordNo);
		factory.close();

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        NotesModel model = (NotesModel)e.getSource();
        Object data = model.getValueAt(row, column);
        int noteId = model.getNoteId(row);
//        System.out.println("słucham tabeli: "+noteId);
        
        carryOutUpdate(noteId, data.toString());
		
	}

}
