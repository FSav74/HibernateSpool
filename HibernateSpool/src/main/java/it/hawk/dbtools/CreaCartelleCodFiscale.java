package it.hawk.dbtools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.hibernate.Session;

import it.hawk.dbtools.dao.AnagDAO;
import it.hawk.dbtools.dao.TrainingDAO;
import it.hawk.dbtools.entities.Anag;
import it.hawk.dbtools.manager.HibernateUtil;
import it.hawk.filesearch.FileSearch;

public class CreaCartelleCodFiscale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Logger loggerInfo= Logger.getLogger("MYLOG");
		loggerInfo.info(">>>>>>>>>>>>>>>>CREA CARTELLE con CODICE FISCALE");
		

		
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
				fileSearch.searchDirectory(new File("C:\\VERIFICA_COD_FISCALE"), codiceFiscale, year);
				
				int count = fileSearch.getResult().size();
				if(count ==0){
					loggerInfo.info("Nessun file presente nella folder");
				}else{

					//crea la folder con il codice fiscale
					Boolean success = (new File("C:\\VERIFICA_COD_FISCALE\\"+codiceFiscale)).mkdirs();
					if (!success) {
					    // Directory creation failed
						loggerInfo.error("Fallita creazione folder "+codiceFiscale);
						continue;
					}
					
					//sposta i file

					for (String matched : fileSearch.getResultOnlyName()){
						Files.move(Paths.get("C:\\VERIFICA_COD_FISCALE\\"+matched), Paths.get("C:\\VERIFICA_COD_FISCALE\\"+codiceFiscale+"\\"+matched), StandardCopyOption.REPLACE_EXISTING);
				    }
				}//else
			}//for
			
		}catch(Exception e){
			loggerInfo.error(" ERRORE :"+e.toString(),e);

		    session.close();
		}
		HibernateUtil.getSessionFactory().close();   		
	}//class

}
