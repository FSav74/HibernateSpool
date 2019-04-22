package it.hawk.dbtools.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "list_OAM")
public class OAMList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_list_oam")
	private Integer id;
	
	@Column(name = "nome", length = 200)
	private String nome;
	
	@Column(name = "autorizzato", length = 200)
	private String autorizzato;
	
	@Column(name = "persona", length = 200)
	private String persona;
	
	@Column(name = "codice_fiscale", length = 200)
	private String cf;
	
	@Column(name = "Domicilio", length = 200)
	private String domicilio;
	
	@Column(name = "elenco", length = 200)
	private String elenco;
	
	@Column(name = "iscrizione", length = 200)
	private String iscrizione;
	
	@Column(name = "data_iscrizione", length = 200)
	private String dataIscrizione;
	
	@Column(name = "stato", length = 200)
	private String stato;
	
	@Column(name = "data_stato", length = 200)
	private String dataStato;
	
	@Column(name = "note", length = 200)
	private String note;
	
	@Column(name = "ruolo", length = 200)
	private String ruolo;
	
	@Column(name = "agente", length = 200)
	private String agente;
	
	@Column(name = "agente_alias", length = 2000)
	private String agenteAlias;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutorizzato() {
		return autorizzato;
	}

	public void setAutorizzato(String autorizzato) {
		this.autorizzato = autorizzato;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getElenco() {
		return elenco;
	}

	public void setElenco(String elenco) {
		this.elenco = elenco;
	}

	public String getIscrizione() {
		return iscrizione;
	}

	public void setIscrizione(String iscrizione) {
		this.iscrizione = iscrizione;
	}

	public String getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(String dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDataStato() {
		return dataStato;
	}

	public void setDataStato(String dataStato) {
		this.dataStato = dataStato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public String getAgenteAlias() {
		return agenteAlias;
	}

	public void setAgenteAlias(String agenteAlias) {
		this.agenteAlias = agenteAlias;
	}
	
	
}