package it.hawk.dbtools.entities;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Il bean che rappresenta un rapporto
 * 
 * @author Dario Persiani
 *
 */
@Entity
@Table(name = "rapps")
public class Rapp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="rapps_seq_gen")
	@SequenceGenerator(name="rapps_seq_gen", sequenceName="RAPPS_ID_RAPP_SEQ")
	@Column(name = "id_rapp")
	private Integer idRapp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anag", nullable = false)
	private Anag anag;
	
	@Column(name = "a41", nullable = false, length = 45)
	private String a41;
	
	@Column(name = "opening_date", nullable = false)
	private Date openingDate;
	
	@Column(name = "closing_date")
	private Date closingDate;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "product")
//	private Product product;
	
	@Column(name = "branch", length = 6)
	private String branch;
	
	@Column(name = "risk_profile")
	private Integer riskProfile;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "typology")
//	private TipoRilevazione typology;
//	
//	@OneToMany(fetch = FetchType.LAZY , mappedBy = "rapp")
//	private Set<Adv> advs = new HashSet<Adv>(0);
	
	@Column(name = "a41_temporary", length = 25)
	private String a41Provvisorio;
	
	@Column(name = "alternative_encoding", length = 25)
	private String codificaAlternativa;
	
	@Column(name = "value_1", length = 11)
	private Integer valore1;
	
	@Column(name = "value_2", length = 11)
	private Integer valore2;
	
	@Column(name = "value_3", length = 11)
	private Integer valore3;
	
	@Column(name = "value_4", length = 11)
	private Integer valore4;
	
	@Column(name = "value_5", length = 11)
	private Integer valore5;
	
	@Column(name = "alpha_1", length = 5)
	private String alpha1;

	@Column(name = "type_rapp_ade", length = 2)
	private String tipoRapportoAdE;
	
	@Column(name = "description_type_rapp_ade", length = 24)
	private String DescrizioneTipoRapportoAdE;
	
	@Column(name = "rapp_master")
	private Integer rappMaster;
	
	/**
	 * @return the idRapp
	 */
	public Integer getIdRapp() {
		return idRapp;
	}
	
	/**
	 * @param idRapp the idRapp to set
	 */
	public void setIdRapp(Integer idRapp) {
		this.idRapp = idRapp;
	}
	
	/**
	 * @return the anag
	 */
	public Anag getAnag() {
		return anag;
	}
	
	/**
	 * @param anag the anag to set
	 */
	public void setAnag(Anag anag) {
		this.anag = anag;
	}

	/**
	 * @return the a41
	 */
	public String getA41() {
		return a41;
	}
	
	/**
	 * @param a41 the a41 to set
	 */
	public void setA41(String a41) {
		this.a41 = a41;
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
	 * @return the product
	 */
//	public Product getProduct() {
//		return product;
//	}
//
//	/**
//	 * @param product the product to set
//	 */
//	public void setProduct(Product product) {
//		this.product = product;
//	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return the idRiskProfile
	 */
	public Integer getRiskProfile() {
		return riskProfile;
	}

	/**
	 * @param idRiskProfile the idRiskProfile to set
	 */
	public void setRiskProfile(Integer riskProfile) {
		this.riskProfile = riskProfile;
	}

	/**
	 * @return the typology
	 */
//	public TipoRilevazione getTypology() {
//		return typology;
//	}
//
//	/**
//	 * @param typology the typology to set
//	 */
//	public void setTypology(TipoRilevazione typology) {
//		this.typology = typology;
//	}

	/**
	 * @return the advs
	 */
//	public Set<Adv> getAdvs() {
//		return advs;
//	}
//	
//	/**
//	 * @param advses the advs to set
//	 */
//	public void setAdvs(Set<Adv> advs) {
//		this.advs = advs;
//	}

	/**
	 * @return the a41Provvisorio
	 */
	public String getA41Provvisorio() {
		return a41Provvisorio;
	}

	/**
	 * @param a41Provvisorio the a41Provvisorio to set
	 */
	public void setA41Provvisorio(String a41Provvisorio) {
		this.a41Provvisorio = a41Provvisorio;
	}

	/**
	 * @return the codificaAlternativa
	 */
	public String getCodificaAlternativa() {
		return codificaAlternativa;
	}

	/**
	 * @param codificaAlternativa the codificaAlternativa to set
	 */
	public void setCodificaAlternativa(String codificaAlternativa) {
		this.codificaAlternativa = codificaAlternativa;
	}

	/**
	 * @return the valore1
	 */
	public Integer getValore1() {
		return valore1;
	}

	/**
	 * @param valore1 the valore1 to set
	 */
	public void setValore1(Integer valore1) {
		this.valore1 = valore1;
	}

	/**
	 * @return the valore2
	 */
	public Integer getValore2() {
		return valore2;
	}

	/**
	 * @param valore2 the valore2 to set
	 */
	public void setValore2(Integer valore2) {
		this.valore2 = valore2;
	}

	/**
	 * @return the valore3
	 */
	public Integer getValore3() {
		return valore3;
	}

	/**
	 * @param valore3 the valore3 to set
	 */
	public void setValore3(Integer valore3) {
		this.valore3 = valore3;
	}

	/**
	 * @return the valore4
	 */
	public Integer getValore4() {
		return valore4;
	}

	/**
	 * @param valore4 the valore4 to set
	 */
	public void setValore4(Integer valore4) {
		this.valore4 = valore4;
	}

	/**
	 * @return the valore5
	 */
	public Integer getValore5() {
		return valore5;
	}

	/**
	 * @param valore5 the valore5 to set
	 */
	public void setValore5(Integer valore5) {
		this.valore5 = valore5;
	}

	/**
	 * @return the alpha1
	 */
	public String getAlpha1() {
		return alpha1;
	}

	/**
	 * @param alpha1 the alpha1 to set
	 */
	public void setAlpha1(String alpha1) {
		this.alpha1 = alpha1;
	}

	public String getTipoRapportoAdE() {
		return tipoRapportoAdE;
	}

	public void setTipoRapportoAdE(String tipoRapportoAdE) {
		this.tipoRapportoAdE = tipoRapportoAdE;
	}

	public String getDescrizioneTipoRapportoAdE() {
		return DescrizioneTipoRapportoAdE;
	}

	public void setDescrizioneTipoRapportoAdE(String descrizioneTipoRapportoAdE) {
		DescrizioneTipoRapportoAdE = descrizioneTipoRapportoAdE;
	}

	public Integer getRappMaster() {
		return rappMaster;
	}

	public void setRappMaster(Integer rappMaster) {
		this.rappMaster = rappMaster;
	}

}
