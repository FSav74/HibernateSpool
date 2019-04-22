package it.hawk.dbtools.entities;

/**
 * Interfaccia che ridefinisce il metodo clone per renderlo disponibile alle classi
 * esterne
 * 
 * http://download.oracle.com/javase/1.5.0/docs/api/java/lang/Cloneable.html
 * 
 * @author Dario Persiani
 *
 */
public interface Copyable<T extends Object> extends java.lang.Cloneable {

	/**
	 * Effettua la copia dell'oggetto campo per campo
	 * 
	 * @return L'oggetto clonato campo per campo
	 */
	public T clone();
	
}
