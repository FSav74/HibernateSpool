package it.hawk.dbtools.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Il bean che rappresenta un tipo documento
 * 
 * @author Dario Persiani
 *
 */
@Entity
@Table(name = "document_types")
public class DocumentType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "code", length = 2)
	private String code;
	
	@Column(name = "description", length = 45)
	private String description;
	
	@Column(name = "encoding_code", length = 200)
	private String encodingCode;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the encodingCode
	 */
	public String getEncodingCode() {
		return encodingCode;
	}

	/**
	 * @param encodingCode the encodingCode to set
	 */
	public void setEncodingCode(String encodingCode) {
		this.encodingCode = encodingCode;
	}
	
}
