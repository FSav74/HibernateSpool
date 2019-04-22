package it.hawk.dbtools.dao;

import java.util.List;

import org.hibernate.HibernateException;
import  org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;


//import it.bas.hawk.dao.DAOException;
import it.hawk.dbtools.entities.Anag;


public class AnagDAO {
	
	public Anag getAnagAsDto(String codiceFiscale,  Session session) throws Exception {
		Query query = session.createQuery(
				 
				" FROM Anag anag "
				+ " WHERE anag.taxCodeD17 = :codiceFiscale");
		query.setParameter("codiceFiscale", codiceFiscale);

		
		return (Anag) query.uniqueResult();
	}
	
	public List<Anag> getAnagByCodeRegistrat( Session session, String codiceFiscale) throws Exception {
		String queryString = 
				 
				" FROM Anag anag "
				+ " WHERE anag.codiceRui is not null ";
						
			if (codiceFiscale!=null)	
				queryString += " AND anag.taxCodeD17 = :codiceFiscale ";
				
		Query query = session.createQuery(queryString);		
		if (codiceFiscale!=null)	
			query.setParameter("codiceFiscale", codiceFiscale);
				
		return (List<Anag>) query.list();
	}

}
