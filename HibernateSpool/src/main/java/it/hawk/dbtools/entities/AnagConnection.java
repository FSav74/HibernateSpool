package it.hawk.dbtools.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Il bean che rappresenta un collegamento anagrafico
 * 
 * @author Dario Persiani
 *
 */
@Entity
@Table(name = "anags_connections")
public class AnagConnection implements Serializable, Copyable<AnagConnection> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="anags_connections_seq_gen")
	@SequenceGenerator(name="anags_connections_seq_gen", sequenceName="ANAGS_CONNECTIONS_ID_ANAG_CONN")
	@Column(name = "id_anag_connection")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rapp")
	private Rapp rapp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anag_primary", nullable = false)
	private Anag anagPrimary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anag_secondary", nullable = false)
	private Anag anagSecondary;
	
	@Column(name = "opening_date", nullable = false)
	private Date openingDate;
	
	@Column(name = "closing_date")
	private Date closingDate;
	
	@Column(name = "col_type", length = 2)
	private String colType;
	
	@Column(name = "col_type_description", length = 45)
	private String colTypeDescription;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "who_entered", length = 45)
//	private EnteredType whoEntered;
	
	@Column(name = "relation", length = 5)
	private String relation;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the rapp
	 */
	public Rapp getRapp() {
		return rapp;
	}
	
	/**
	 * @param rapp the rapp to set
	 */
	public void setRapp(Rapp rapp) {
		this.rapp = rapp;
	}
	
	/**
	 * @return the anagPrimary
	 */
	public Anag getAnagPrimary() {
		return anagPrimary;
	}

	/**
	 * @param anagPrimary the anagPrimary to set
	 */
	public void setAnagPrimary(Anag anagPrimary) {
		this.anagPrimary = anagPrimary;
	}

	/**
	 * @return the anagSecondary
	 */
	public Anag getAnagSecondary() {
		return anagSecondary;
	}

	/**
	 * @param anagSecondary the anagSecondary to set
	 */
	public void setAnagSecondary(Anag anagSecondary) {
		this.anagSecondary = anagSecondary;
	}

	/**
	 * @return the openingDate
	 */
	public Date getOpeningDate() {
		return openingDate;
	}
	
	/**
	 * @param openingDate the openingDate to set
	 */
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	
	/**
	 * @return the closingDate
	 */
	public Date getClosingDate() {
		return closingDate;
	}
	
	/**
	 * @param closingDate the closingDate to set
	 */
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * @return the colType
	 */
	public String getColType() {
		return colType;
	}

	/**
	 * @param colType the colType to set
	 */
	public void setColType(String colType) {
		this.colType = colType;
	}

	/**
	 * @return the colTypeDescription
	 */
	public String getColTypeDescription() {
		return colTypeDescription;
	}

	/**
	 * @param colTypeDescription the colTypeDescription to set
	 */
	public void setColTypeDescription(String colTypeDescription) {
		this.colTypeDescription = colTypeDescription;
	}

	/**
	 * @return the whoEntered
	 */
//	public EnteredType getWhoEntered() {
//		return whoEntered;
//	}
//
//	/**
//	 * @param whoEntered the whoEntered to set
//	 */
//	public void setWhoEntered(EnteredType whoEntered) {
//		this.whoEntered = whoEntered;
//	}
	
	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Override
	public AnagConnection clone() {
		try {
			return (AnagConnection) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
