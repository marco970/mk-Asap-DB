package pl.asap.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notes")
public class Notes {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="note_id")
	private Integer noteId;
	
	@Column(name="note")
	private String note;
	
	 
	//@Column(name="id_postepowanie")
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_postepowanie")
	private Integer idPostepowanie;
	
	@Column(name="date_open")
	private String dateOpen;
	
	@Column(name="date_modified")
	private String dateModified;
	
	@Column(name="is_open")
	private Integer isOpen;		//1 - open, 0 - closed
	
	public Notes()	{
		
	}

	public Notes(String note, Integer idPostepowanie, String dateOpen, String dateModified, Integer isOpen) {
		this.note = note;
		this.idPostepowanie = idPostepowanie;
		this.dateOpen = dateOpen;
		this.dateModified = dateModified;
		this.isOpen = isOpen;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIdPostepowanie() {
		return idPostepowanie;
	}

	public void setIdPostepowanie(Integer idPostepowanie) {
		this.idPostepowanie = idPostepowanie;
	}

	public String getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(String dateOpen) {
		this.dateOpen = dateOpen;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	@Override
	public String toString() {
		return "Notes [noteId=" + noteId + ", note=" + note + ", idPostepowanie=" + idPostepowanie + ", dateOpen="
				+ dateOpen + ", dateModified=" + dateModified + ", isOpen=" + isOpen + "]";
	}
	
	

}
