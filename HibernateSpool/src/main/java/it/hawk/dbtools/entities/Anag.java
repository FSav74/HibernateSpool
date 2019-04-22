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
 * Il bean che rappresenta una anagrafica
 *
 */
@Entity
@Table(name = "anags")
public class Anag implements Serializable, Copyable<Anag> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="anags_seq_gen")
	@SequenceGenerator(name="anags_seq_gen", sequenceName="ANAGS_ID_ANAG_SEQ")
	@Column(name = "id_anag")
	private Integer idAnag;
	
	@Column(name = "c11", nullable = false, length = 16)
	private String c11;
	
	@Column(name = "d11_name", nullable = false, length = 200)
	private String nameD11;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "anag_type", nullable = false, length = 3)
	private AnagType anagType;
	
	@Column(name = "d15_address", length = 65)
	private String addressD15;
	
	@Column(name = "d16_postal_code", length = 5)
	private String postalCodeD16;
	
	@Column(name = "d17_tax_code", length = 16)
	private String taxCodeD17;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d13_country")
	private Country country;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d41_document_type")
	private DocumentType documentTypeD41;
	
	@Column(name = "d14a_cab", length = 6)
	private String cabD14a;
	
	@Column(name = "d14b_comune", length = 45)
	private String comuneD14b;
	
	@Column(name = "d14c_provincia", length = 2)
	private String provinciaD14c;
	
	@Column(name = "d17b_vat", length = 11)
	private String vatD17b;
	
	@Column(name = "d18_birth_date")
	private Date birthDateD18;
	
	@Column(name = "d19_birth_comune", length = 45)
	private String birthComuneD19;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "d21_sae")
//	private TabSae saeD21;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "d22_rae")
//	private TabRae raeD22;
	
	@Column(name = "d23_set_sint", length = 3)
	private String settSintD23;
	
	@Column(name = "d42_document_number", length = 15)
	private String documentNumberD42;
	
	@Column(name = "d43_document_release_date")
	private Date documentReleaseDateD43;
	
	@Column(name = "d44_document_authority_locale", length = 30)
	private String documentAuthorityLocaleD44;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "intermediary", nullable = false)
//	private Intermediary intermediary;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "subject_type")
//	private AnagSubjectType subjectType;
	
	@Column(name = "document_expiry_date")
	private Date documentExpiryDate;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "profession")
//	private RiskProfileParameter profession;
	
	@Column(name = "phone_number", length = 45)
	private String phoneNumber;
	
	@Column(name = "mail_address", length = 200)
	private String mailAddress;
	
	/**
	 * Il NOME dell'anagrafica
	 */
	@Column(name = "first_name", length = 35)
	private String firstName;
	
	/**
	 * Il COGNOME dell'anagrafica
	 */
	@Column(name = "name", length = 35)
	private String name;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "pep")
//	private RiskProfileParameter pep;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "legal_nature_011")
//	private RiskProfileParameter legalNature;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "identification_type")
//	private RiskProfileParameter identificationType;
	
	@Column(name = "street_toponym", length = 20)
	private String streetToponym;
	
	@Column(name = "street_name", length = 50)
	private String streetName;
	
	@Column(name = "street_number", length = 10)
	private String streetNumber;
	
	@Column(name = "rea_code", length = 20)
	private String reaCode;
	
	@Column(name = "rae_code", length = 3)
	private String raeCode;
	
	@Column(name = "filiale", length = 8)
	private String filiale;

	@Column(name = "d44a_document_rel_country", length = 50)
	private String documentReleaseCountryD44a;
	
	@Column(name = "d44b_document_rel_municipal", length = 50)
	private String documentReleaseMunicipalityD44b;
	
	@Column(name = "d44c_document_rel_province", length = 3)
	private String documentReleaseProvinceD44c;
	
	@Column(name = "d44d_document_rel_authority", length = 50)
	private String documentReleaseAuthorityD44d;
	
	@Column(name = "header_output_stream", length = 45)
	private String headerOutputStream;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_of_birth")
	private Country paeseNascita;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_of_municipality")
	private Country paeseCittadinanza;
	
	@Column(name = "ndg_collocator", length = 16)
	private String ndgCollocatore;
	
	@Column(name = "alternative_ndg", length = 16)
	private String ndgAlternativo;
	
//	@OneToMany(fetch = FetchType.LAZY , mappedBy = "anag")
//	private Set<Rapp> rapps = new HashSet<Rapp>(0);
//	
//	@OneToMany(fetch = FetchType.LAZY , mappedBy = "anag")
//	private Set<AttachmentData> attachmentsData = new HashSet<AttachmentData>(0);
//	
//	@OneToMany(fetch = FetchType.LAZY , mappedBy = "anag")
//	private Set<AdditionalInfo> additionalsInfo = new HashSet<AdditionalInfo>(0);
//	
//	@OneToMany(fetch = FetchType.LAZY , mappedBy = "anagSecondary")
//	private Set<AnagConnection> delegateConnections = new HashSet<AnagConnection>(0);
//	
//	@OneToMany(fetch = FetchType.LAZY , mappedBy = "idAnag")
//	private Set<RiskProfileAssigned> riskProfileAssigned = new HashSet<RiskProfileAssigned>(0);	
//	
	@Column(name = "ateco2007_code", length = 6)
	private String ateco2007;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_of_birth")
	private Provincia provinciaNascita;
	
	@Column(name = "regione_of_birth")
	private Integer regioneNascita;
	
	@Column(name = "regione_residenza")
	private Integer regioneResidenza;
	
	@Column(name = "regione_rilascio_documento", length = 50)
	private String regioneRilascioDocumento;
	
	@Column(name = "univoca_smac", length = 18)
	private String univocaSmac;
	
	@Column(name = "process_batch")
	private Integer processBatch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "second_citizenship")
	private Country secondCitizenship;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domicile_country")
	private Country domicileCountry;
	
	@Column(name = "domicile_region", length = 11)
	private Integer domicileRegion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domicile_province")
	private Provincia domicileProvince;
	
	@Column(name = "domicile_municipality", length = 50)
	private String domicileMunicipality;
	
	@Column(name = "domicile_cab", length = 6)
	private String domicileCab;
	
	@Column(name = "domicile_toponymy_particle", length = 20)
	private String domicileToponymyParticle;
	
	@Column(name = "domicile_street_name", length = 50)
	private String domicileStreetName;
	
	@Column(name = "domicile_street_number", length = 10)
	private String domicileStreetNumber;
	
	@Column(name = "domicile_postal_code", length = 5)
	private String domicilePostalCode;
	
	@Column(name = "is_domicile_same_residence")
	private Boolean isDomicileSameResidence;

	@Column(name = "code_advisor")
	private String matricolaGestore;
	
	@Column(name = "code_oam")
	private String codiceOam;
	
	@Column(name = "code_rui")
	private String codiceRui;
	
	@Column(name = "note_ocf")
	private String noteOcf;	
	@Column(name = "data_iscrizione")
	private String dataIscrizione;
	@Column(name = "data_invio_raccomandata")
	private String dataInvioRaccomandata;
	@Column(name = "data_accoglimento_ivass")
	private String dataAccoglimentoIvass;
	@Column(name = "codice_intermediario")
	private String intermediaryCode;
	@Column(name = "casa_madre")
	private String casaMadre;
	@Column(name = "anagscol")
	private String anagscol;
	
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "anag")
	private Set<AnagBrokerTraining> anagBrokerTrainings = new HashSet<AnagBrokerTraining>(0);

	/**
	 * @return the idAnag
	 */
	public Integer getIdAnag() {
		return idAnag;
	}
	
	/**
	 * @param idAnag the idAnag to set
	 */
	public void setIdAnag(Integer idAnag) {
		this.idAnag = idAnag;
	}
	
	/**
	 * @return the c11
	 */
	public String getC11() {
		return c11;
	}

	/**
	 * @param c11 the c11 to set
	 */
	public void setC11(String c11) {
		this.c11 = c11;
	}

	/**
	 * @return the nameD11
	 */
	public String getNameD11() {
		return nameD11;
	}
	
	/**
	 * @param nameD11 the nameD11 to set
	 */
	public void setNameD11(String nameD11) {
		this.nameD11 = nameD11;
	}
	
	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the anagType
	 */
	public AnagType getAnagType() {
		return anagType;
	}
	
	/**
	 * @param anagType the anagType to set
	 */
	public void setAnagType(AnagType anagType) {
		this.anagType = anagType;
	}
	
	/**
	 * @return the addressD15
	 */
	public String getAddressD15() {
		return addressD15;
	}
	
	/**
	 * @param addressD15 the addressD15 to set
	 */
	public void setAddressD15(String addressD15) {
		this.addressD15 = addressD15;
	}
	
	/**
	 * @return the postalCodeD16
	 */
	public String getPostalCodeD16() {
		return postalCodeD16;
	}
	
	/**
	 * @param postalCodeD16 the postalCodeD16 to set
	 */
	public void setPostalCodeD16(String postalCodeD16) {
		this.postalCodeD16 = postalCodeD16;
	}
	
	/**
	 * @return the taxCodeD17
	 */
	public String getTaxCodeD17() {
		return taxCodeD17;
	}
	
	/**
	 * @param taxCodeD17 the taxCodeD17 to set
	 */
	public void setTaxCodeD17(String taxCodeD17) {
		this.taxCodeD17 = taxCodeD17;
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
	 * @return the documentTypeD41
	 */
	public DocumentType getDocumentTypeD41() {
		return documentTypeD41;
	}
	
	/**
	 * @param documentTypeD41 the documentTypeD41 to set
	 */
	public void setDocumentTypeD41(DocumentType documentTypeD41) {
		this.documentTypeD41 = documentTypeD41;
	}
	
	/**
	 * @return the cabD14a
	 */
	public String getCabD14a() {
		return cabD14a;
	}
	
	/**
	 * @param cabD14a the cabD14a to set
	 */
	public void setCabD14a(String cabD14a) {
		this.cabD14a = cabD14a;
	}
	
	/**
	 * @return the comuneD14b
	 */
	public String getComuneD14b() {
		return comuneD14b;
	}

	/**
	 * @param comuneD14b the comuneD14b to set
	 */
	public void setComuneD14b(String comuneD14b) {
		this.comuneD14b = comuneD14b;
	}

	/**
	 * @return the provinciaD14c
	 */
	public String getProvinciaD14c() {
		return provinciaD14c;
	}

	/**
	 * @param provinciaD14c the provinciaD14c to set
	 */
	public void setProvinciaD14c(String provinciaD14c) {
		this.provinciaD14c = provinciaD14c;
	}

	/**
	 * @return the vatD17b
	 */
	public String getVatD17b() {
		return vatD17b;
	}
	
	/**
	 * @param vatD17b the vatD17b to set
	 */
	public void setVatD17b(String vatD17b) {
		this.vatD17b = vatD17b;
	}
	
	/**
	 * @return the birthDateD18
	 */
	public Date getBirthDateD18() {
		return birthDateD18;
	}
	
	/**
	 * @param birthDateD18 the birthDateD18 to set
	 */
	public void setBirthDateD18(Date birthDateD18) {
		this.birthDateD18 = birthDateD18;
	}
	
	/**
	 * @return the birthCabD19
	 */
	public String getBirthComuneD19() {
		return birthComuneD19;
	}
	
	/**
	 * @param birthCabD19 the birthCabD19 to set
	 */
	public void setBirthComuneD19(String birthComuneD19) {
		this.birthComuneD19 = birthComuneD19;
	}
	
//	/**
//	 * @return the saeD21
//	 */
//	public TabSae getSaeD21() {
//		return saeD21;
//	}
//
//	/**
//	 * @param saeD21 the saeD21 to set
//	 */
//	public void setSaeD21(TabSae saeD21) {
//		this.saeD21 = saeD21;
//	}
//
//	/**
//	 * @return the raeD22
//	 */
//	public TabRae getRaeD22() {
//		return raeD22;
//	}
//
//	/**
//	 * @param raeD22 the raeD22 to set
//	 */
//	public void setRaeD22(TabRae raeD22) {
//		this.raeD22 = raeD22;
//	}

	/**
	 * @return the settSintD23
	 */
	public String getSettSintD23() {
		return settSintD23;
	}

	/**
	 * @param settSintD23 the settSintD23 to set
	 */
	public void setSettSintD23(String settSintD23) {
		this.settSintD23 = settSintD23;
	}

	/**
	 * @return the documentNumberD42
	 */
	public String getDocumentNumberD42() {
		return documentNumberD42;
	}
	
	/**
	 * @param documentNumberD42 the documentNumberD42 to set
	 */
	public void setDocumentNumberD42(String documentNumberD42) {
		this.documentNumberD42 = documentNumberD42;
	}
	
	/**
	 * @return the documentReleaseDateD43
	 */
	public Date getDocumentReleaseDateD43() {
		return documentReleaseDateD43;
	}
	
	/**
	 * @param documentReleaseDateD43 the documentReleaseDateD43 to set
	 */
	public void setDocumentReleaseDateD43(Date documentReleaseDateD43) {
		this.documentReleaseDateD43 = documentReleaseDateD43;
	}
	
	/**
	 * @return the documentAuthorityLocale
	 */
	public String getDocumentAuthorityLocaleD44() {
		return documentAuthorityLocaleD44;
	}
	
	/**
	 * @param documentAuthorityLocaleD44 the documentAuthorityLocaleD44 to set
	 */
	public void setDocumentAuthorityLocaleD44(String documentAuthorityLocaleD44) {
		this.documentAuthorityLocaleD44 = documentAuthorityLocaleD44;
	}
	
	/**
	 * @return the intermediary
	 */
//	public Intermediary getIntermediary() {
//		return intermediary;
//	}
//
//	/**
//	 * @param intermediary the intermediary to set
//	 */
//	public void setIntermediary(Intermediary intermediary) {
//		this.intermediary = intermediary;
//	}
//
//	/**
//	 * @return the subjectType
//	 */
//	public AnagSubjectType getSubjectType() {
//		return subjectType;
//	}
//
//	/**
//	 * @param subjectType the subjectType to set
//	 */
//	public void setSubjectType(AnagSubjectType subjectType) {
//		this.subjectType = subjectType;
//	}

	/**
	 * @return the documentExpiryDate
	 */
	public Date getDocumentExpiryDate() {
		return documentExpiryDate;
	}

	/**
	 * @param documentExpiryDate the documentExpiryDate to set
	 */
	public void setDocumentExpiryDate(Date documentExpiryDate) {
		this.documentExpiryDate = documentExpiryDate;
	}

	/**
	 * @return the profession
	 */
//	public RiskProfileParameter getProfession() {
//		return profession;
//	}
//
//	/**
//	 * @param profession the profession to set
//	 */
//	public void setProfession(RiskProfileParameter profession) {
//		this.profession = profession;
//	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * @return the pep
	 */
//	public RiskProfileParameter getPep() {
//		return pep;
//	}
//
//	/**
//	 * @param pep the pep to set
//	 */
//	public void setPep(RiskProfileParameter pep) {
//		this.pep = pep;
//	}
//
//	/**
//	 * @return the legalNature
//	 */
//	public RiskProfileParameter getLegalNature() {
//		return legalNature;
//	}
//
//	/**
//	 * @param legalNature the legalNature to set
//	 */
//	public void setLegalNature(RiskProfileParameter legalNature) {
//		this.legalNature = legalNature;
//	}

	/**
	 * @return the identificationType
	 */
//	public RiskProfileParameter getIdentificationType() {
//		return identificationType;
//	}
//
//	/**
//	 * @param identificationType the identificationType to set
//	 */
//	public void setIdentificationType(RiskProfileParameter identificationType) {
//		this.identificationType = identificationType;
//	}
//
//	/**
//	 * @return the rapps
//	 */
//	public Set<Rapp> getRapps() {
//		return rapps;
//	}
	
//	/**
//	 * @param rapps the rapps to set
//	 */
//	public void setRapps(Set<Rapp> rapps) {
//		this.rapps = rapps;
//	}
//	
//	/**
//	 * @return the riskProfileAssigned
//	 */
//	public Set<RiskProfileAssigned> getRiskProfileAssigned() {
//		return riskProfileAssigned;
//	}
//
//	/**
//	 * @param riskProfileAssigned the riskProfileAssigned to set
//	 */
//	public void setRiskProfileAssigned(
//			Set<RiskProfileAssigned> riskProfileAssigned) {
//		this.riskProfileAssigned = riskProfileAssigned;
//	}
//
//	/**
//	 * @return the attachmentsData
//	 */
//	public Set<AttachmentData> getAttachmentsData() {
//		return attachmentsData;
//	}
//
//	/**
//	 * @param attachmentsData the attachmentsData to set
//	 */
//	public void setAttachmentsData(Set<AttachmentData> attachmentsData) {
//		this.attachmentsData = attachmentsData;
//	}
//
//	/**
//	 * @return the additionalsInfo
//	 */
//	public Set<AdditionalInfo> getAdditionalsInfo() {
//		return additionalsInfo;
//	}
//
//	/**
//	 * @param additionalsInfo the additionalsInfo to set
//	 */
//	public void setAdditionalsInfo(Set<AdditionalInfo> additionalsInfo) {
//		this.additionalsInfo = additionalsInfo;
//	}

	/**
	 * @return the delegateConnections
	 */
//	public Set<AnagConnection> getDelegateConnections() {
//		return delegateConnections;
//	}
//
//	/**
//	 * @param delegateConnections the delegateConnections to set
//	 */
//	public void setDelegateConnections(Set<AnagConnection> delegateConnections) {
//		this.delegateConnections = delegateConnections;
//	}
	
	/**
	 * @return the streetToponym
	 */
	public String getStreetToponym() {
		return streetToponym;
	}

	/**
	 * @param streetToponym the streetToponym to set
	 */
	public void setStreetToponym(String streetToponym) {
		this.streetToponym = streetToponym;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the reaCode
	 */
	public String getReaCode() {
		return reaCode;
	}

	/**
	 * @param reaCode the reaCode to set
	 */
	public void setReaCode(String reaCode) {
		this.reaCode = reaCode;
	}
	
	/**
	 * @return the raeCode
	 */
	public String getRaeCode() {
		return raeCode;
	}

	/**
	 * @param raeCode the raeCode to set
	 */
	public void setRaeCode(String raeCode) {
		this.raeCode = raeCode;
	}

	/**
	 * @return the filiale
	 */
	public String getFiliale() {
		return filiale;
	}

	/**
	 * @param filiale the filiale to set
	 */
	public void setFiliale(String filiale) {
		this.filiale = filiale;
	}

	public String getDocumentReleaseCountryD44a() {
		return documentReleaseCountryD44a;
	}

	public void setDocumentReleaseCountryD44a(String documentReleaseCountryD44a) {
		this.documentReleaseCountryD44a = documentReleaseCountryD44a;
	}

	public String getDocumentReleaseMunicipalityD44b() {
		return documentReleaseMunicipalityD44b;
	}

	public void setDocumentReleaseMunicipalityD44b(String documentReleaseMunicipalityD44b) {
		this.documentReleaseMunicipalityD44b = documentReleaseMunicipalityD44b;
	}

	public String getDocumentReleaseProvinceD44c() {
		return documentReleaseProvinceD44c;
	}

	public void setDocumentReleaseProvinceD44c(String documentReleaseProvinceD44c) {
		this.documentReleaseProvinceD44c = documentReleaseProvinceD44c;
	}

	public String getDocumentReleaseAuthorityD44d() {
		return documentReleaseAuthorityD44d;
	}

	public void setDocumentReleaseAuthorityD44d(String documentReleaseAuthorityD44d) {
		this.documentReleaseAuthorityD44d = documentReleaseAuthorityD44d;
	}
	
	public String getAteco2007() {
		return ateco2007;
	}

	public void setAteco2007(String ateco2007) {
		this.ateco2007 = ateco2007;
	}

	@Override
	public Anag clone() {
		try {
			return (Anag) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * @return the headerOutputStream
	 */
	public String getHeaderOutputStream() {
		return headerOutputStream;
	}

	/**
	 * @param headerOutputStream the headerOutputStream to set
	 */
	public void setHeaderOutputStream(String headerOutputStream) {
		this.headerOutputStream = headerOutputStream;
	}

	/**
	 * @return the paeseNascita
	 */
	public Country getPaeseNascita() {
		return paeseNascita;
	}

	/**
	 * @param paeseNascita the paeseNascita to set
	 */
	public void setPaeseNascita(Country paeseNascita) {
		this.paeseNascita = paeseNascita;
	}

	/**
	 * @return the paeseCittadinanza
	 */
	public Country getPaeseCittadinanza() {
		return paeseCittadinanza;
	}

	/**
	 * @param paeseCittadinanza the paeseCittadinanza to set
	 */
	public void setPaeseCittadinanza(Country paeseCittadinanza) {
		this.paeseCittadinanza = paeseCittadinanza;
	}

	/**
	 * @return the ndgCollocatore
	 */
	public String getNdgCollocatore() {
		return ndgCollocatore;
	}

	/**
	 * @param ndgCollocatore the ndgCollocatore to set
	 */
	public void setNdgCollocatore(String ndgCollocatore) {
		this.ndgCollocatore = ndgCollocatore;
	}

	/**
	 * @return the ndgAlternativo
	 */
	public String getNdgAlternativo() {
		return ndgAlternativo;
	}

	/**
	 * @param ndgAlternativo the ndgAlternativo to set
	 */
	public void setNdgAlternativo(String ndgAlternativo) {
		this.ndgAlternativo = ndgAlternativo;
	}

	/**
	 * @return the provinciaNascita
	 */
	public Provincia getProvinciaNascita() {
		return provinciaNascita;
	}

	/**
	 * @param provinciaNascita the provinciaNascita to set
	 */
	public void setProvinciaNascita(Provincia provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	/**
	 * @return the regioneNascita
	 */
	public Integer getRegioneNascita() {
		return regioneNascita;
	}

	/**
	 * @param regioneNascita the regioneNascita to set
	 */
	public void setRegioneNascita(Integer regioneNascita) {
		this.regioneNascita = regioneNascita;
	}

	/**
	 * @return the regioneResidenza
	 */
	public Integer getRegioneResidenza() {
		return regioneResidenza;
	}

	/**
	 * @param regioneResidenza the regioneResidenza to set
	 */
	public void setRegioneResidenza(Integer regioneResidenza) {
		this.regioneResidenza = regioneResidenza;
	}

	/**
	 * @return the regioneRilascioDocumento
	 */
	public String getRegioneRilascioDocumento() {
		return regioneRilascioDocumento;
	}

	/**
	 * @param regioneRilascioDocumento the regioneRilascioDocumento to set
	 */
	public void setRegioneRilascioDocumento(String regioneRilascioDocumento) {
		this.regioneRilascioDocumento = regioneRilascioDocumento;
	}
	
	/**
	 * 
	 * @return the univocaSmac
	 */
	public String getUnivocaSmac() {
		return univocaSmac;
	}
	/**
	 * 
	 * @param univocaSmac the univocaSmac to set
	 */
	public void setUnivocaSmac(String univocaSmac) {
		this.univocaSmac = univocaSmac;
	}
	
	/**
	 * @return the processBatch
	 */
	public Integer getProcessBatch() {
		return processBatch;
	}

	/**
	 * @param processBatch the processBatch to set
	 */
	public void setProcessBatch(Integer processBatch) {
		this.processBatch = processBatch;
	}
	
	public String getMatricolaGestore() {
		return matricolaGestore;
	}

	public void setMatricolaGestore(String matricolaGestore) {
		this.matricolaGestore = matricolaGestore;
	}

	public Country getSecondCitizenship() {
		return secondCitizenship;
	}

	public void setSecondCitizenship(Country secondCitizenship) {
		this.secondCitizenship = secondCitizenship;
	}

	public Country getDomicileCountry() {
		return domicileCountry;
	}

	public void setDomicileCountry(Country domicileCountry) {
		this.domicileCountry = domicileCountry;
	}

	public Integer getDomicileRegion() {
		return domicileRegion;
	}

	public void setDomicileRegion(Integer domicileRegion) {
		this.domicileRegion = domicileRegion;
	}

	public Provincia getDomicileProvince() {
		return domicileProvince;
	}

	public void setDomicileProvince(Provincia domicileProvince) {
		this.domicileProvince = domicileProvince;
	}

	public String getDomicileMunicipality() {
		return domicileMunicipality;
	}

	public void setDomicileMunicipality(String domicileMunicipality) {
		this.domicileMunicipality = domicileMunicipality;
	}

	public String getDomicileCab() {
		return domicileCab;
	}

	public void setDomicileCab(String domicileCab) {
		this.domicileCab = domicileCab;
	}

	public String getDomicileToponymyParticle() {
		return domicileToponymyParticle;
	}

	public void setDomicileToponymyParticle(String domicileToponymyParticle) {
		this.domicileToponymyParticle = domicileToponymyParticle;
	}

	public String getDomicileStreetName() {
		return domicileStreetName;
	}

	public void setDomicileStreetName(String domicileStreetName) {
		this.domicileStreetName = domicileStreetName;
	}

	public String getDomicileStreetNumber() {
		return domicileStreetNumber;
	}

	public void setDomicileStreetNumber(String domicileStreetNumber) {
		this.domicileStreetNumber = domicileStreetNumber;
	}

	public String getDomicilePostalCode() {
		return domicilePostalCode;
	}

	public void setDomicilePostalCode(String domicilePostalCode) {
		this.domicilePostalCode = domicilePostalCode;
	}

	public Boolean getIsDomicileSameResidence() {
		return isDomicileSameResidence;
	}

	public void setIsDomicileSameResidence(Boolean isDomicileSameResidence) {
		this.isDomicileSameResidence = isDomicileSameResidence;
	}

	public String getCodiceOam() {
		return codiceOam;
	}

	public void setCodiceOam(String codiceOam) {
		this.codiceOam = codiceOam;
	}

	public String getCodiceRui() {
		return codiceRui;
	}

	public void setCodiceRui(String codiceRui) {
		this.codiceRui = codiceRui;
	}

	public String getNoteOcf() {
		return noteOcf;
	}

	public void setNoteOcf(String noteOcf) {
		this.noteOcf = noteOcf;
	}

	public String getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(String dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public String getDataInvioRaccomandata() {
		return dataInvioRaccomandata;
	}

	public void setDataInvioRaccomandata(String dataInvioRaccomandata) {
		this.dataInvioRaccomandata = dataInvioRaccomandata;
	}

	public String getDataAccoglimentoIvass() {
		return dataAccoglimentoIvass;
	}

	public void setDataAccoglimentoIvass(String dataAccoglimentoIvass) {
		this.dataAccoglimentoIvass = dataAccoglimentoIvass;
	}



	public String getIntermediaryCode() {
		return intermediaryCode;
	}

	public void setIntermediaryCode(String intermediaryCode) {
		this.intermediaryCode = intermediaryCode;
	}

	public String getCasaMadre() {
		return casaMadre;
	}

	public void setCasaMadre(String casaMadre) {
		this.casaMadre = casaMadre;
	}

	public Set<AnagBrokerTraining> getAnagBrokerTrainings() {
		return anagBrokerTrainings;
	}

	public void setAnagBrokerTrainings(Set<AnagBrokerTraining> anagBrokerTrainings) {
		this.anagBrokerTrainings = anagBrokerTrainings;
	}

	public String getAnagscol() {
		return anagscol;
	}

	public void setAnagscol(String anagscol) {
		this.anagscol = anagscol;
	}
	
}