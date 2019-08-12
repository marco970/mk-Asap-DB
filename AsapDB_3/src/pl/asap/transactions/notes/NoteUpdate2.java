package pl.asap.transactions.notes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.asap.entity.Config;
import pl.asap.models.NotesModel;

public class NoteUpdate2 implements TableModelListener {
	
	Config config;
	String hibernateConf;
	Configuration conf;
	SessionFactory factory;
	Session session;
	

	public NoteUpdate2()	{
	}
	
	public void carryOutUpdate() {
		
		config = new Config();

		conf = new Configuration();
		conf.configure(config.getHibernateXML());
		config.setAnnotatedClass(conf);

		factory = conf.buildSessionFactory();
		session = factory.getCurrentSession();

		session.beginTransaction();
	}
	public void updateNote(int noteId, String note, String dateModified)	{
		carryOutUpdate();
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
	public void updateIsOpen(int noteId, boolean isOpen, String dateModified)	{
		carryOutUpdate();
		String update = "update Notes e set "
				+ "e.dateModified=:dateModified,"
				+ "e.isOpen=:isOpen "
				+ "where e.noteId=:noteId";
		Query query = session.createQuery(update);
		query.setParameter("dateModified", dateModified);
		query.setParameter("isOpen", isOpen);
		query.setParameter("noteId", noteId);
		int recordNo = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(recordNo);
		factory.close();

	}
	@Override
	public void tableChanged(TableModelEvent e) {
		
        int column = e.getColumn();
        System.out.println("=======tableChanged==========>>");
        
    	int row = e.getFirstRow();
		NotesModel dane = (NotesModel) e.getSource();
		Object data = dane.getValueAt(row, column);
		int noteId; 
		if (row>=0) {
			noteId = dane.getNoteId(row);
		}
//		} else	{
//			noteId = dane.getNoteId(0);
//		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date(System.currentTimeMillis());
		String dateModified = formatter.format(date);

        if (column==3) {
        	updateIsOpen(noteId, (Boolean) data, dateModified);
//        	dane.updateNoFire(data, row, 3);

        }
        

		
	}
}

