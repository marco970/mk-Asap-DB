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
@Table(name = "time_sheet")
public class TimeSheetEntity extends EntityBase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name = "entry_id")
	private Integer entryId;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "id_postepowanie")
	private Lista lista;
	
	@Column(name = "sap_nr")
	private String sapNr;
	
	@Column(name = "date_entry")
	private String dateEntry;
	
	@Column(name = "time_passed")
	private Integer timePassed;

	public TimeSheetEntity(Integer entryId, String sapNr, String dateEntry, Integer timePassed) {
		this.entryId = entryId;
		this.sapNr = sapNr;
		this.dateEntry = dateEntry;
		this.timePassed = timePassed;
	}

	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public String getSapNr() {
		return sapNr;
	}

	public void setSapNr(String sapNr) {
		this.sapNr = sapNr;
	}

	public String getDateEntry() {
		return dateEntry;
	}

	public void setDateEntry(String dateEntry) {
		this.dateEntry = dateEntry;
	}

	public Integer getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(Integer timePassed) {
		this.timePassed = timePassed;
	}

	@Override
	public String toString() {
		return "TimeSheetEntity [entryId=" + entryId + ", sapNr=" + sapNr + ", dateEntry=" + dateEntry + ", timePassed="
				+ timePassed + "]";
	}
	
	
}
