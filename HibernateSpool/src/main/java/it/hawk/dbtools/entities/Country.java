package it.hawk.dbtools.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Il bean che rappresenta un paese
 * 
 * @author Dario Persiani
 *
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "code_alpha3", length = 3)
	private String codeAlpha3;
	
	@Column(name = "two_char_code", length = 2)
	private String twoCharCode;
	
	@Column(name = "three_char_code", length = 3)
	private String threeCharCode;
	
	@Column(name = "numeric_code", length = 3)
	private String numericCode;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	@Column(name="alias")
	private String alias;
	
	@Column(name = "black_list", nullable = false)
	private Boolean blackList;
	
	@Column(name = "gafi", nullable = false)
	private Boolean gafi;
	
	@Column(name = "equ", nullable = false)
	private Boolean equ;
	
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "country")
	private Set<Regione> regioni = new HashSet<Regione>();
	
	@Column(name = "short_code", length = 3)
	private String shortCode;
	
	@Column(name = "four_cf_code", length = 4)
	private String fourCfCode;
	
	@Column(name = "continent", length = 200)
	private String continent;
	
	/**
	 * @return the codeAlpha3
	 */
	public String getCodeAlpha3() {
		return codeAlpha3;
	}

	/**
	 * @param codeAlpha3 the codeAlpha3 to set
	 */
	public void setCodeAlpha3(String codeAlpha3) {
		this.codeAlpha3 = codeAlpha3;
	}

	/**
	 * @return the twoCharCode
	 */
	public String getTwoCharCode() {
		return twoCharCode;
	}

	/**
	 * @param twoCharCode the twoCharCode to set
	 */
	public void setTwoCharCode(String twoCharCode) {
		this.twoCharCode = twoCharCode;
	}

	/**
	 * @return the threeCharCode
	 */
	public String getThreeCharCode() {
		return threeCharCode;
	}

	/**
	 * @param threeCharCode the threeCharCode to set
	 */
	public void setThreeCharCode(String threeCharCode) {
		this.threeCharCode = threeCharCode;
	}

	/**
	 * @return the numericCode
	 */
	public String getNumericCode() {
		return numericCode;
	}

	/**
	 * @param numericCode the numericCode to set
	 */
	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the blackList
	 */
	public Boolean getBlackList() {
		return blackList;
	}
	
	/**
	 * @param blackList the blackList to set
	 */
	public void setBlackList(Boolean blackList) {
		this.blackList = blackList;
	}
	
	/**
	 * @return the gafi
	 */
	public Boolean getGafi() {
		return gafi;
	}
	
	/**
	 * @param gafi the gafi to set
	 */
	public void setGafi(Boolean gafi) {
		this.gafi = gafi;
	}
	
	/**
	 * @return the equ
	 */
	public Boolean getEqu() {
		return equ;
	}
	
	/**
	 * @param equ the equ to set
	 */
	public void setEqu(Boolean equ) {
		this.equ = equ;
	}
	
	/**
	 * @return the regioni
	 */
	public Set<Regione> getRegioni() {
		return regioni;
	}
	
	/**
	 * @param regioni the regioni to set
	 */
	public void setRegioni(Set<Regione> regioni) {
		this.regioni = regioni;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFourCfCode() {
		return fourCfCode;
	}

	public void setFourCfCode(String fourCfCode) {
		this.fourCfCode = fourCfCode;
	}

	/**
	 * @return the continent
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * @param continent the continent to set
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}
	
}
