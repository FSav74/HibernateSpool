package it.hawk.dbtools.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Il bean che rappresenta una regione
 * 
 * @author Dario Persiani
 *
 */
@Entity
@Table(name = "regioni")
public class Regione implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "codice")
	private Integer codice;
	
	@Column(name = "descrizione", nullable = false, length = 50)
	private String descrizione;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country")
	private Country country;
	
	@Column(name="alias")
	private String alias;
	
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "regione")
	private Set<Provincia> province = new HashSet<Provincia>();
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * @return the codice
	 */
	public Integer getCodice() {
		return codice;
	}
	
	/**
	 * @param codice the codice to set
	 */
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	/**
	 * @return the province
	 */
	public Set<Provincia> getProvince() {
		return province;
	}
	
	/**
	 * @param province the province to set
	 */
	public void setProvince(Set<Provincia> province) {
		this.province = province;
	}
	
}
