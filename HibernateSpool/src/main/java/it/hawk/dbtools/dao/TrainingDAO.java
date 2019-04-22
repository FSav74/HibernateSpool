package it.hawk.dbtools.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import it.hawk.dbtools.entities.AnagBrokerTraining;






public class TrainingDAO {
	public Integer sumTrainingHoursByIdAnag(Integer idAnagrafica, Integer year,  Session session) throws Exception{


		String query = "SELECT SUM(abt.oreFormazione) FROM AnagBrokerTraining abt " + 
					   "WHERE abt.anag.idAnag = :idAnagrafica " + 
					   "AND abt.annoFormazione = :year ";
		Query q = session.createQuery(query);

		q.setParameter("idAnagrafica", idAnagrafica);
		q.setParameter("year", year);
	
		Double result =  (Double) q.uniqueResult();
		if (result!=null)
			return result.intValue(); 
		else return 0;
		
	}
	
	public Integer countSearchFormazioneByIdAnagYearFile(Integer idAnagrafica, Integer year,  String file, Session session) throws Exception{

		try{
			String query = "Select count(distinct abt) FROM AnagBrokerTraining abt WHERE abt.anag.idAnag = :idAnagrafica "
					+ " AND abt.annoFormazione = :year ";
			
			query += " AND abt.attestato like :file ";
			
			
			Query q = session.createQuery(query);
	
			q.setParameter("idAnagrafica", idAnagrafica);
			q.setParameter("year", year);
			
			q.setParameter("file", "%"+file+"%");
			
			
			@SuppressWarnings("unchecked")
			Long result =  (Long) q.uniqueResult();
			
			return result.intValue();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
			

	}
	public List<AnagBrokerTraining> searchFormazioneByIdAnag(Integer idAnagrafica, Integer year, Session session) throws Exception{

		try{
			String query = " FROM AnagBrokerTraining abt WHERE abt.anag.idAnag = :idAnagrafica "
			+ " AND abt.annoFormazione = :year "
			+ " AND abt.attestato is null ";

			
			Query q = session.createQuery(query);

			q.setParameter("idAnagrafica", idAnagrafica);

			q.setParameter("year", year );

			
			@SuppressWarnings("unchecked")
			List<AnagBrokerTraining> result =  q.list();
			
			return result; 
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	public List<AnagBrokerTraining> searchFormazioneAfterFirstBatch( Integer year,String createDate, Session session) throws Exception{

		try{
			String query = " FROM AnagBrokerTraining abt WHERE  "
					+ " abt.createUser='BATCH' "+
					" AND abt.createDate =:createDate  "
			+ " AND abt.annoFormazione = :year "
			+ " AND abt.attestato is not null and abt.oreFormazione=0 ";
			//+ " and abt.anag.idAnag = 03105046 ";

			
			Query q = session.createQuery(query);
			q.setParameter("createDate", createDate );

			q.setParameter("year", year );
			q.setMaxResults(200);

			
			@SuppressWarnings("unchecked")
			List<AnagBrokerTraining> result =  q.list();
			
			return result; 
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
