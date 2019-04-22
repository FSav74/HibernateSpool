package it.hawk.dbtools.entities;

import java.io.Serializable;
import java.util.Date;
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
 * Il bean che rappresenta una provincia
 * 
 * @author Dario Persiani
 *
 */

@Entity
@Table(name = "province")
public class Provincia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "sigla", length = 2, nullable = false)
	private String sigla;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@Column(name = "codice", length = 3, nullable = false)
	private String codice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regione")
	private Regione regione;
	
	@Column(name = "date_validity_end")
	private Date dateValidityEnd;
	
	@Column(name="alias")
	private String alias;
	
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "provincia")
	private Set<Comune> comuni = new HashSet<Comune>();
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the dateValidityEnd
	 */
	public Date getDateValidityEnd() {
		return dateValidityEnd;
	}

	/**
	 * @param dateValidityEnd the dateValidityEnd to set
	 */
	public void setDateValidityEnd(Date dateValidityEnd) {
		this.dateValidityEnd = dateValidityEnd;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}
	
	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}
	
	/**
	 * @return the comuni
	 */
	public Set<Comune> getComuni() {
		return comuni;
	}
	
	/**
	 * @param comuni the comuni to set
	 */
	public void setComuni(Set<Comune> comuni) {
		this.comuni = comuni;
	}
	
}
