package it.hawk.dbtools.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "anag_broker_training")
public class AnagBrokerTraining implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="anags_broker_training_seq_gen")
	@SequenceGenerator(name="anags_broker_training_seq_gen", sequenceName="ANAG_BROKER_TRAINING_ID")
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anag", nullable = false)
	private Anag anag;
	
	@Column(name = "data_validita")
	private Date dataValidita;
	
	@Column(name = "anno_formazione", nullable = false)
	private Integer annoFormazione;
	
	@Column(name = "ore_formazione")
	private Double oreFormazione;
	
	@Column(name = "descrizione_corso")
	private String descrizioneCorso;
	
	@Column(name = "modalita_erogazione")
	private String modalitaErogazione;
	
	@Column(name = "rilasciato_da")
	private String rilasciatoDa;
	
	@Column(name = "attestato")
	private String attestato;
	
	@Column(name = "last_update_date", length = 10)
	private String lastUpdateDate;
	
	@Column(name = "last_update_user", length = 200)
	private String lastUpdateUser;
	
	
	@Column(name = "create_date", length = 10)
	private String createDate;
	
	@Column(name = "create_user", length = 200)
	private String createUser;
	
	
	
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

	public Anag getAnag() {
		return anag;
	}

	public void setAnag(Anag anag) {
		this.anag = anag;
	}

	public Date getDataValidita() {
		return dataValidita;
	}

	public void setDataValidita(Date dataValidita) {
		this.dataValidita = dataValidita;
	}

	public Integer getAnnoFormazione() {
		return annoFormazione;
	}

	public void setAnnoFormazione(Integer annoFormazione) {
		this.annoFormazione = annoFormazione;
	}

	public Double getOreFormazione() {
		return oreFormazione;
	}

	public void setOreFormazione(Double oreFormazione) {
		this.oreFormazione = oreFormazione;
	}

	public String getDescrizioneCorso() {
		return descrizioneCorso;
	}

	public void setDescrizioneCorso(String descrizioneCorso) {
		this.descrizioneCorso = descrizioneCorso;
	}

	public String getModalitaErogazione() {
		return modalitaErogazione;
	}

	public void setModalitaErogazione(String modalitaErogazione) {
		this.modalitaErogazione = modalitaErogazione;
	}

	public String getRilasciatoDa() {
		return rilasciatoDa;
	}

	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}

	public String getAttestato() {
		return attestato;
	}

	public void setAttestato(String attestato) {
		this.attestato = attestato;
	}
	

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	public AnagBrokerTraining clone() {
		try {
			return (AnagBrokerTraining) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
