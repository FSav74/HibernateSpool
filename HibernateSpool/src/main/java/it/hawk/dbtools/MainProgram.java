package it.hawk.dbtools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.hibernate.Session;

import it.hawk.dbtools.dao.AnagDAO;
import it.hawk.dbtools.dao.TrainingDAO;
import it.hawk.dbtools.entities.Anag;
import it.hawk.dbtools.entities.AnagBrokerTraining;
import it.hawk.dbtools.manager.HibernateUtil;
import it.hawk.filesearch.FileSearch;

//import com.asprise.*;
//import com.asprise.ocr.Ocr;
//import com.asprise.util.ocr.OCR;
public class MainProgram {
	
public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Logger loggerInfo= Logger.getLogger("MYLOG");
		loggerInfo.info(">>>>>>>>>>>>>>>>START FIRST PROGRAM<<<<<<<<<<");
		
		session.beginTransaction();
	
		
		//prendo dal db tutte le anagrafiche id, codice fiscale, che hanno code_registrazione
		AnagDAO anagDAO = new AnagDAO();
		TrainingDAO trainingDAO = new TrainingDAO();
		try{
			List<Anag> listaAnag = anagDAO.getAnagByCodeRegistrat(session, null);//"PNTCNZ81C60B354B"
			if (  (listaAnag==null)||(listaAnag.size()==0)  ){
				loggerInfo.info("Non ho trovato risultati.");
			}
			//con ogni codice fiscale cerco il file
			for(Anag anag: listaAnag){
				
			}
		}catch(Exception e) {
			loggerInfo.error("Error", e);
		}
}
	
	
	
/*
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
        session.beginTransaction();
//        Query query = session.createQuery("FROM OAMList where cf = :cf");
//        query.setParameter("cf", "DNRGLC74S28L182O");
//        List<OAMList> result = query.list();
//        
//        for(OAMList entity : result){
//        	System.out.println("ID:\t"+entity.getId());
//        	System.out.println("Name\t:"+entity.getNome());
//        }
        
        Query query = session.createQuery("FROM AnagBrokerTraining where attestato is not null");
        //query.setMaxResults(2);
      //query.setParameter("cf", "DNRGLC74S28L182O");
      List<AnagBrokerTraining> result = query.list();
      int count =0;
      for(AnagBrokerTraining training : result){
    	  System.out.println("id:"+training.getAnag().getIdAnag()+" - "+training.getAttestato());

    	  
    	  AttachmentData data = new AttachmentData();
    	  data.setAnag(training.getAnag());
    	  String attestato  = training.getAttestato().substring(0, Math.min(training.getAttestato().length(), 64));
    	  data.setFileName(attestato);
    	  session.saveOrUpdate(data);
    	  count++;
      }
      System.out.println("inserite righe :"+count);
      session.getTransaction().commit();
    session.close();
    HibernateUtil.getSessionFactory().close();
        
//        User user = new User();
// 
//        user.setUserId(1);
//        user.setUsername("Mukesh");
//        user.setCreatedBy("Google");
//        user.setCreatedDate(new Date());
// 
//        session.save(user);
//        session.getTransaction().commit();

	}
*/
	
// FUNZIONANTE	
//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		 System.out.println(">>>>>>>>>>>>>>>>Start");
//       
//       Query query = session.createQuery("SELECT ac.id as id, ac.id as id FROM AnagConnection ac INNER JOIN ac.anagSecondary a "
//       		+ " WHERE ac.anagPrimary.idAnag = :anagPrimary ");
//       query.setParameter("anagPrimary", 3108497);
//       List<Object[]> result = query.list();
//       
//
//      if (result ==null)   System.out.println(">>>>>NULL");
//      else System.out.println(">>>>>NOT NULL "+result.size());
//      
//      
//      for(Object[] training : result){
//    	  Integer i= (Integer) training[0];
//    	  System.out.println("id:"+i);
//
//      }
//     
//	   HibernateUtil.getSessionFactory().close();        
//
//	}
	
	
	//CARICAMENTO training 
	public static void mainOLD(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Logger loggerInfo= Logger.getLogger("MYLOG");
		loggerInfo.info(">>>>>>>>>>>>>>>>START INSERT TRAINING SENZA ORE");
		
		session.beginTransaction();
		
		TessBaseAPI api = new TessBaseAPI();
		
		//prendo dal db tutte le anagrafiche id, codice fiscale, che hanno code_registrazione
		AnagDAO anagDAO = new AnagDAO();
		TrainingDAO trainingDAO = new TrainingDAO();
		try{
			List<Anag> listaAnag = anagDAO.getAnagByCodeRegistrat(session, null);//"PNTCNZ81C60B354B"
			//con ogni codice fiscale cerco il file
			for(Anag anag: listaAnag){
				
				FileSearch fileSearch = new FileSearch();
				
				String codiceFiscale = anag.getTaxCodeD17();
				Integer idAnag = anag.getIdAnag();
				String codiceRegistrazione = anag.getCodiceRui();
				
				String year ="2018";
				
				if (codiceFiscale==null) continue;
				
				loggerInfo.info("----------------------------------------------------------------------");
				loggerInfo.info("Elaboro id_ang:" + anag.getIdAnag() + " codiceFiscale:"+codiceFiscale+" - "+year);
				
				//cerco nella folder e subfolder
				fileSearch.searchDirectory(new File("C:\\Users\\f.letterese\\Documents\\VERIFICA2018"), codiceFiscale, year);
				
				int count = fileSearch.getResult().size();
				if(count ==0){
					loggerInfo.info("Nessun file presente nella folder");
				}else{
//				    loggerInfo.info("Found " + count + " result!");
//				    //for (String matched : fileSearch.getResult()){
//				    for (String matched : fileSearch.getResultOnlyName()){
//				    	loggerInfo.info("\tFound : " + matched);	
//				    }
				    
				    //verifcio che quel file non è già sul db
				    List<String> filedacaricare = new ArrayList<String>();
					for (String matched : fileSearch.getResultOnlyName()){
				    	loggerInfo.info("\tTrovati nei path :"+fileSearch.getResultOnlyName().size());
				    	int trovato = trainingDAO.countSearchFormazioneByIdAnagYearFile(anag.getIdAnag(), Integer.parseInt(year), matched, session);
				    	if (trovato==0) {
				    		loggerInfo.info("\tFile : " + matched+" è nuovo! devo inserire training.");
				    		filedacaricare.add(matched);
				    	}else{
				    		//System.out.println("File : " + matched+" è già  sul db!");
				    	}	
				    }
					
						
					for(String nomeFile: filedacaricare){
						loggerInfo.info("\tInserisco training id_ang:" + anag.getIdAnag() + " file:"+nomeFile+" - "+year);
						AnagBrokerTraining entity = new AnagBrokerTraining();
						entity.setAnag(anag);
						entity.setAnnoFormazione( Integer.parseInt(year));
						entity.setAttestato(nomeFile);
						entity.setOreFormazione(0d);
						entity.setModalitaErogazione("--");
						entity.setCreateUser("BATCH");
						
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

						String dateString = format.format( new Date()   );
						
						entity.setCreateDate(dateString);
						session.saveOrUpdate(entity);
					}
				    
				    
				    
				}
				
				
			}
			session.getTransaction().commit();
		    session.close();
			loggerInfo.info("<<<<<<<<<<<<<<<<<<END");
			//lo trovo -> inserisco training con codice fisclae, nome file, e anno (parsato nel file come _17 _18 oppure 2017 2018)  
		
		}catch(Exception e){
			loggerInfo.error(" ERRORE :"+e.toString(),e);
			session.getTransaction().rollback();
		    session.close();
		}
		HibernateUtil.getSessionFactory().close();    
	}
	
	public static void main2(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Logger loggerInfo= Logger.getLogger("MYLOG");
		loggerInfo.info(">>>>>>>>>>>>>>>>START");
		System.out.println(">>>>>>>>>>>>>>>>START");
		String path = "C:\\DOCUMENTI\\CheBanca\\FILE-CARICAMENTI\\chebanca\\14Gennaio2018\\ORGINALI\\VERIFICHE.xlsx";
		System.out.println("FILE>>>>>:" + path);

		File file = new File(path);
		Workbook workbook;
		int i=1;
		AnagDAO anagDAO = new AnagDAO();
		TrainingDAO trainingDAO = new TrainingDAO();
		try {
			workbook = WorkbookFactory.create(file);

			Sheet sheet = workbook.getSheetAt(0);

			Row headerRow = sheet.getRow(0); //header
			
			String codiceFiscale = null;
			String annoFormazione = null;
			String oreFormazione = null;
			for (i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row.getCell(6) != null) {
					codiceFiscale = row.getCell(6).getStringCellValue();				
					if ( (codiceFiscale==null)||(codiceFiscale.length()!=16) ) continue;	
				}
				
				if (row.getCell(18) != null) {
					annoFormazione = ""+row.getCell(18).getNumericCellValue();
				}
				if (row.getCell(19) != null) {
					oreFormazione = ""+row.getCell(19).getNumericCellValue();
				}
				double annoExcel = -1;
				double oreExcel = -1;
				try{
					annoExcel = Double.parseDouble(annoFormazione);
					oreExcel = Double.parseDouble(oreFormazione);
				}catch(NumberFormatException d){ d.printStackTrace();continue;}
				Anag anag = anagDAO.getAnagAsDto(codiceFiscale, session);
				
				if (anag==null){
					loggerInfo.error(" CODICE FISCLAE NON TROVATO :"+codiceFiscale);
					continue;
				}
				int sumOre = trainingDAO.sumTrainingHoursByIdAnag(anag.getIdAnag(), (int)annoExcel, session);
				System.out.println("---------------------------------------------------------");
				System.out.println(">>>>>>" +anag.getIdAnag()+ " - "+ sumOre+" - "+codiceFiscale +" - "+annoFormazione+" - "+oreFormazione);
				
				
				if (oreExcel > sumOre){
					//inserisco training
					
					//NOTA loggare un warning se la differenza è > 30: potrebbero essere piu' corsi Ma non potrò mai saperlo
					loggerInfo.info("--->INSERT TRAINING "
							+ "anno:" +annoFormazione+" - "
									+ "idAnag :"+anag.getIdAnag()+ " - "
											+ "cod.fiscale:"+codiceFiscale 
									+ " ore : "+(oreExcel - sumOre));
					
				}
				
				//cerco i file
				FileSearch fileSearch = new FileSearch();
				
				String yearToSearch="";
				if ((int)annoExcel==2017) {
					yearToSearch = "_17";
					fileSearch.searchDirectory(new File("C:\\Hawk_Chebanca\\Caricamenti\\ALLEGATI"), codiceFiscale,yearToSearch);
				}
//				int count = fileSearch.getResult().size();
//				if(count ==0){
//				    System.out.println("No result found!");
//				}else{
//				    System.out.println("\nFound " + count + " result!\n");
//				    for (String matched : fileSearch.getResult()){
//					System.out.println("Found : " + matched);
//				    }
//				}
				if ((int)annoExcel==2017) yearToSearch = "2017";
				if ((int)annoExcel==2018) yearToSearch = "2018";
				
				fileSearch.searchDirectory(new File("C:\\Users\\f.letterese\\Documents\\VERIFICA2018"), codiceFiscale,yearToSearch);
				
				int count = fileSearch.getResult().size();
				if(count ==0){
				    //System.out.println("No result found!");
				}else{
				    System.out.println("Found " + count + " result!");
				    //for (String matched : fileSearch.getResult()){
				    for (String matched : fileSearch.getResult()){
				    	System.out.println("Found : " + matched);
				    	
				  /*  	Ocr.setUp(); // one time setup
						Ocr ocr = new Ocr(); // create a new OCR engine
						ocr.startEngine("spa", Ocr.SPEED_SLOW); // English
						String s = ocr.recognize(new File[] {new File(matched)}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
						System.out.println("Result: " + s);
						// ocr more images here ...
						ocr.stopEngine();
				    */	
				    }
				}
				



/*				
				//TODO: transazione con test
				//TODO: log4j
				
				//TODO:
				//prendo listaA dei file ovvero fileSearch.getResult() : tolgo il path e mi tengo solo il nome del file
				//C:\Hawk_Chebanca\Caricamenti\ALLEGATI\PCSMRC81T21B354T\MOL_PICASSO MARCO_PCSMRC81T21B354T_17.pdf
				
				//cerco nel db quei nomi di file: se presenti li scarto
				//listaA vuota? continue
				List<String> filedacaricare = new ArrayList<String>();
				for (String matched : fileSearch.getResultOnlyName()){
			    	loggerInfo.info("trovati nei path :"+fileSearch.getResultOnlyName().size());
			    	int trovato = trainingDAO.countSearchFormazioneByIdAnagYearFile(anag.getIdAnag(), (int)annoExcel, matched, session);
			    	if (trovato==0) {
			    		System.out.println("File : " + matched+" NON TROVATO  sul db!");
			    		filedacaricare.add(matched);
			    	}else{
			    		System.out.println("File : " + matched+" è già  sul db!");
			    	}
			    	
			    }
				
				
				if (filedacaricare.size()==0) {
					System.out.println("Non ho certificati da asccoiar e. passo");
					continue;
				}
				loggerInfo.info("File non già associati:"+filedacaricare.size());
				
				List<AnagBrokerTraining> searchFormazioneByIdAnag = trainingDAO.searchFormazioneByIdAnag(anag.getIdAnag(), (int)annoExcel, session);
				//prendo dal db lista training id-anag,anno corrente con file NULL
				
				if (searchFormazioneByIdAnag == null || searchFormazioneByIdAnag.size()==0) {
					System.out.println("Non corsi a cui associare certificati. passo");
					continue;
				}
				
				
				if (searchFormazioneByIdAnag.size()>1)
					loggerInfo.warn(" ----------------->WARNING! Ho piu' di un corso nell'anno: l'associazione potrebbe non essere corretta!");
				
				if (filedacaricare.size() > searchFormazioneByIdAnag.size()){
					loggerInfo.warn(" ----------------->WARNING! Ho piu' certificati che corsi creati: verificare!");
				}
				
				//NOTA LOG:se ha piu' di un elemento-> warning potrei toppare l'associazione
				//NOTA LOG: se i certificati sono in numero maggiore -> loggare un warning: potrei avere piu' corsi mentre io ne ho creato uno
				
				//e la ciclo : al primo elemento associo il primo  della lista A
				// se listaA terminata -> esco 
				//altrimenti continuo il ciclo
*/				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error AnagBrokerTraining retriving anagrafica id :"+i);
			e.printStackTrace();
		}
		
		
		System.out.println(">>>>>>>>>>>>>>>>END");
		HibernateUtil.getSessionFactory().close();     
	}
	
	
	
	

}
