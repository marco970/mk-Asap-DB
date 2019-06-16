package pl.asap.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
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
	private NotesModel notesModel;

	public NoteUpdateTest(NotesModel notesModel)	{
		this.notesModel= notesModel;
	}
	
	public void carryOutUpdate(int noteId, String note, String dateModified) {
		
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
		
		System.out.println(this.getClass()+" fr "+e.getFirstRow()+" lr "+e.getLastRow()+" col "+e.getColumn()+" src "+e.getSource());
		
        int column = e.getColumn();
        
          
		
//		System.out.println("---------------- "+dateModified);
//        System.out.println("słucham tabeli: "+noteId);
        
        if (column==2) {
        	int row = e.getFirstRow();
			NotesModel dane = (NotesModel) e.getSource();
			Object data = dane.getValueAt(row, column);
			int noteId = dane.getNoteId(row);
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			Date date = new Date(System.currentTimeMillis());
			String dateModified = formatter.format(date);
			carryOutUpdate(noteId, data.toString(), dateModified);
			dane.updateNoFire(dateModified, row, 1);
		}
		
	}

}
