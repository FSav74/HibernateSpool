package it.hawk.dbtools.entities;

/**
 * L'enumerazione per il gender di una anagrafica
 * 
 * @author Dario Persiani
 *
 */
public enum Gender {
	
	M("M"), F("F");

	/**
	 * Il codice che identifica il valore
	 */
	private String code;

	/**
	 * Crea il valore
	 * 
	 * @param code Il codice che identifica il valore
	 */
	private Gender(String code) {
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
	public static Gender parseCode(String code) {
		for (Gender p : values()) {
			if (p.getCode().equals(code)) {
				return p;
			}
		}
		return null;
	}
	
}
