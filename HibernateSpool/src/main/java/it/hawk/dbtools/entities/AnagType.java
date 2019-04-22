package it.hawk.dbtools.entities;

import javax.xml.bind.annotation.XmlType;

/**
 * L'enumerazione per il tipo anagrafica
 * 
 * @author Dario Persiani
 *
 */
@XmlType(name = "anagTypeEnum")
public enum AnagType {
	
	PF("personaFisica"), NPF("personaGiuridica"), ALL("all");//, SCO("cointestazioni");

	/**
	 * Il codice che identifica il valore
	 */
	private String code;

	/**
	 * Crea il valore
	 * 
	 * @param code Il codice che identifica il valore
	 */
	private AnagType(String code) {
		this.code = code;
	}

	/**
	 * Restituisce il codice che identifica il valore
	 * 
	 * @return Il codice che identifica il valore
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Recupera il valore associato ad un codice
	 * 
	 * @param code Il codice
	 * @return Il valore associato, o null se non esiste
	 */
	public static AnagType parseCode(String code) {
		for (AnagType p : values()) {
			if (p.getCode().equals(code)) {
				return p;
			}
		}
		return null;
	}
	
}
