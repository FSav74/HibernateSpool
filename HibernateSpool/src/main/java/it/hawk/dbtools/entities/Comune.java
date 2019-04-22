package it.hawk.dbtools.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Il bean che rappresenta un comune
 * 
 * @author Dario Persiani
 *
 */
@Entity
@Table(name = "comuni")
public class Comune implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cab", length = 6)
	private String cab;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "cap", nullable = true, length = 5)
	private String cap;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provincia")
	private Provincia provincia;
	
	@Column(name = "catasto", nullable = true, length = 5)
	private String catasto;

	@Column(name = "visibileInSearch", nullable = false, length = 1)
	private Boolean visibileInSearch;

	@Column(name = "cod_istat", nullable = true, length = 6)
	private String istat;
	
	@Column(name = "alias")
	private String alias;

	@Column(name = "prefisso", nullable = true, length = 6)
	private String prefisso;
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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
	 * @return the cab
	 */
	public String getCab() {
		return cab;
	}
	
	/**
	 * @param cab the cab to set
	 */
	public void setCab(String cab) {
		this.cab = cab;
	}
	
	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	
	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	/**
	 * @return the provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}
	
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCatasto() {
		return catasto;
	}

	public void setCatasto(String catasto) {
		this.catasto = catasto;
	}

	public Boolean getVisibileInSearch() {
		return visibileInSearch;
	}

	public void setVisibileInSearch(Boolean visibileInSearch) {
		this.visibileInSearch = visibileInSearch;
	}

	public String getIstat() {
		return istat;
	}

	public void setIstat(String istat) {
		this.istat = istat;
	}

	public String getPrefisso() {
		return prefisso;
	}

	public void setPrefisso(String prefisso) {
		this.prefisso = prefisso;
	}
	
}
