package it.hawk.dbtools.entities;

import java.io.Serializable;
import java.sql.Blob;
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
 * Il bean che rappresenta un file allegato
 * 
 * @author Giovanni
 *
 */
@Entity
@Table(name = "attachments_data")
public class AttachmentData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="attachments_data_seq_gen")
	@SequenceGenerator(name="attachments_data_seq_gen", sequenceName="ATTACHMENTS_DATA_ID_ATTACHMENT")
	@Column(name = "id_attachment_data")
	private Integer idAttachmentData;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anag", nullable = false)
	private Anag anag;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "rapp")
//	private Rapp rapp;
	
	@Column(name = "attachment_date")
	private Date date;
	
	@Column(name = "attachment_param")
	private Integer attachmentParam;
	
	@Column(name = "description_param", length = 65)
	private String descriptionParam;
	
	@Column(name = "file_name", length = 65)
	private String fileName;
	
	@Column(name = "attachment")
	private Blob attachment;
	
	/**
	 * @return the idAttachmentData
	 */
	public Integer getIdAttachmentData() {
		return idAttachmentData;
	}
	
	/**
	 * @param idAttachmentData the idAttachmentData to set
	 */
	public void setIdAttachmentData(Integer idAttachmentData) {
		this.idAttachmentData = idAttachmentData;
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
	 * @return the rapp
	 */
//	public Rapp getRapp() {
//		return rapp;
//	}
//
//	/**
//	 * @param rapp the rapp to set
//	 */
//	public void setRapp(Rapp rapp) {
//		this.rapp = rapp;
//	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the attachmentParam
	 */
	public Integer getAttachmentParam() {
		return attachmentParam;
	}

	/**
	 * @param attachmentParam the attachmentParam to set
	 */
	public void setAttachmentParam(Integer attachmentParam) {
		this.attachmentParam = attachmentParam;
	}

	/**
	 * @return the descriptionParam
	 */
	public String getDescriptionParam() {
		return descriptionParam;
	}
	
	/**
	 * @param descriptionParam the descriptionParam to set
	 */
	public void setDescriptionParam(String descriptionParam) {
		this.descriptionParam = descriptionParam;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the attachment
	 */
	public Blob getAttachment() {
		return attachment;
	}
	
	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}

}
