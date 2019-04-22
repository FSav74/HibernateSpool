package it.hawk.dbtools;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.hibernate.Query;
import org.hibernate.Session;

import it.hawk.dbtools.dao.AnagDAO;
import it.hawk.dbtools.dao.TrainingDAO;
import it.hawk.dbtools.entities.Anag;
import it.hawk.dbtools.entities.AnagBrokerTraining;
import it.hawk.dbtools.entities.AnagConnection;
import it.hawk.dbtools.entities.AttachmentData;
import it.hawk.dbtools.entities.OAMList;
import it.hawk.dbtools.manager.HibernateUtil;
import it.hawk.dbtools.parser.OCRParser;
import it.hawk.dbtools.parser.PdfReader;
import it.hawk.filesearch.FileSearch;
import it.hawk.filesearch.HourFileSearch;
import net.sourceforge.tess4j.util.ImageIOHelper;

import static org.bytedeco.javacpp.lept.*;
import org.bytedeco.javacpp.*;

//import com.asprise.*;
//import com.asprise.ocr.Ocr;
//import com.asprise.util.ocr.OCR;
public class ImageMainProgramOLD {
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
	/*
	 * KIRON DDMDNC80H14E532U   ok                                                       '30 ore'
	 * CHEBANCA MF141 CSCMHL73A18F205C OK                                                '30 ore'
	 * AUXILIA_ACCETTURO CELESTINO_CCTCST81C08D643X_2018   OK                          ---alcuni pdf leggibili, altre immagini   '15h'
	 * 24 finance è come auxilia                                                       ---     '15h'  
	 * EUROANSA sono pdf : bisogna trovare                                            pdf     'della durata di 30 ore completato'
	 * NEXUS è come auxilia e 24 finance                                               ---     '15h'
	 * PER TE                                                                         PDF  'della durata di 30 ore'
	 * PIU MUTUI                                                                       ---     '15h
	 * PRESTITOSI                  pochi e diversi . uno nuovo 'formazione: 30'
	 *    Reti complete Blu orange                                                                       ---     '15h
	 *    
	 *    
	 *    WE_UNIT_SPA_BIONDINI_DANIELE_BNDDNL75C07H501O_2018
	 *    
	 *    MOL_PINTUS CINZIA_PNTCNZ81C60B354B_2018
	 *    
	 *    
	 *    CASO NGLLDR73D10H501K : solo pdf
	 *    
	 *    NDRSDR51D09L639J
	 */
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Logger loggerInfo= Logger.getLogger("MYLOG");
		loggerInfo.info(">>>>>>>>>>>>>>>>START ");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateString = format.format( new Date()   );
		
		loggerInfo.info(">>>>>>>>>>>>>>>>START ELABORAZIONI CERTIFICATI:"+dateString);
		
		session.beginTransaction();
		
		TessBaseAPI api = new TessBaseAPI();
		
		
		
		//prendo dal db tutte le anagrafiche id, codifce fsiclae, che hanno code_registrazione
		AnagDAO anagDAO = new AnagDAO();
		TrainingDAO trainingDAO = new TrainingDAO();
		//PMC  SPA_ALBERTO  PATRIZIA_LBRPRZ73S63L219O_2018
		try{
			//TODO: prendo tutta la formazione inserita con l'ultimo caricamento : modalità -- data creazione:20190122
			//TODO togliere il codice fiscale MPS_FIORITO ALESSANDRO_FRTLSN69R05C351J_2018
			//SLVMRZ75S16H264Z  multi 
			//CSCMHL73A18F205C
			
			List<Anag> listaAnag = anagDAO.getAnagByCodeRegistrat(session,"LGGNDR71C11E281Z");
			//con ogni codice fiscale cerco il file
			
			
			if (listaAnag==null){
				loggerInfo.error("Nessun dato dal db.");
				return;
			}
			
			loggerInfo.error("Totale righe lette dal db:"+listaAnag.size());
			
			int righeAggiornate =0;
			
			for(Anag anag: listaAnag){
				
				FileSearch fileSearch = new FileSearch();
				
				String codiceFiscale = anag.getTaxCodeD17();
				Integer idAnag = anag.getIdAnag();
				
	
				
				String year ="2018";
				
				if (codiceFiscale==null) continue;
				
				loggerInfo.info("----------------------------------------------------------------------");
				loggerInfo.info("Elaboro id_ang:" + anag.getIdAnag() + " codiceFiscale:"+codiceFiscale+" - "+year);
				
				//TODO: deve essere la cartelle dove ho tutti i certificati : sia i pdf che i .tiff
				fileSearch.searchDirectory(new File("C:\\Users\\f.letterese\\Documents\\VERIFICA2018PROVA"), codiceFiscale, year);
				int count = fileSearch.getResult().size();
				if(count ==0){
				    //System.out.println("No result found!");
				}else{
//				    loggerInfo.info("Found " + count + " result!");
//				    //for (String matched : fileSearch.getResult()){
//				    for (String matched : fileSearch.getResultOnlyName()){
//				    	loggerInfo.info("Found : " + matched);	
//				    }
				    
				    Double ore = null;
				    
				    
				    
				    
				    //verifcio che quel file non è già sul db
				    List<String> filedacaricare = new ArrayList<String>();
				    List<String> filedaElaborare = new ArrayList<String>();
				    
				    HourFileSearch fourFileSearch = new HourFileSearch();
				    
				    loggerInfo.info("\ttrovati nei path :"+fileSearch.getResultOnlyName().size());
				    for (String matched : fileSearch.getResult()){
				    	

				    	int trovato = trainingDAO.countSearchFormazioneByIdAnagYearFile(anag.getIdAnag(), Integer.parseInt(year), matched, session);
				    	if (trovato==0) {
				    		loggerInfo.info("\tFile : " + matched+" è nuovo! devo inserire training.");
				    		filedaElaborare.add(matched);
				    	}else{
				    		//System.out.println("File : " + matched+" è già  sul db!");
				    	}
				    	
//				    	BytePointer outText;
//						
//				        // Initialize tesseract-ocr with English, without specifying tessdata path
//				        if (api.Init("C:\\DOCUMENTI\\tessdata", "ita") != 0) {
//				            System.err.println("Could not initialize tesseract.");
//				            loggerInfo.error("ERROR: Could not initialize tesseract.");
//				            System.exit(1);
//				        }
//
//				        
//				        
//				        // Open input image with leptonica library matched
//				        //PIX image = pixRead(args.length > 0 ? args[0] : "/usr/src/tesseract/testing/phototest.tif");
//				        try{
//				        	PIX image = pixRead(args.length > 0 ? args[0] : matched);
//					        api.SetImage(image);
//					        // Get OCR result
//					        outText = api.GetUTF8Text();
//					        System.out.println("OCR output:\n" + outText.getString());
//					        
//					        ore = fourFileSearch.find(outText.getString());
//					        
//					        api.End();
//					        outText.deallocate();
//					        pixDestroy(image);
//				        }catch (Exception e){
//				        	loggerInfo.error("error:",e);
//				        }
				    	
				    	
				    	
				    	
				    	if(matched.endsWith(".tiff")){
				    		File nuovo = new File(matched);
				    		List<File> lista = ImageIOHelper.createTiffFiles(nuovo,-1);		
				    		double somma = 0;
				    		System.out.println("NUMERO :"+lista.size());
				    		
							for(File file: lista){
				    		    String fileName = file.getAbsolutePath();
						    	String outputParsed = null;
						    	try{
						    		OCRParser ocrParser = new OCRParser();
						    		outputParsed = ocrParser.parse(fileName);
						    		somma += fourFileSearch.find(outputParsed);
						    		
						    	}catch(Exception e){
						    		loggerInfo.error("Errore OCR PARSER!", e);
						    	}
							}
							System.out.println("SOMMA :"+somma);
							if (somma!=0){
								ore = somma;
				    			filedacaricare.add(matched);
							}
				    	}
				    	if(matched.endsWith(".pdf")){
				    		loggerInfo.info("\t\tTODO: Elaboro pdf");
				    		String testo = PdfReader.getText(matched);
				    		System.out.println("PDF:"+testo);
				    		ore = fourFileSearch.find(testo);
				    		if (ore!=null)
				    			filedacaricare.add(matched);
				    	}
				        if (ore!=null) break;   //se ho trovato le ore: non proseguo
				    	
				    }//fine ciclo dei file
					
					if (ore==null) {
						loggerInfo.warn("\t\t-------------------------------------------------VERIFICA MANUALE");
						loggerInfo.info("\t\tANAGRAFICA:" + anag.getIdAnag() + " ANNO:  "+year+" - ORE: "+ore);
					}
					else{
						if ((ore <8 )||(ore >30)) {
							loggerInfo.warn("\t\t-------------------------------------------------VERIFICA MANUALE ore minori di 8 oppure maggiori di 30");
						}
						loggerInfo.info("\t\tSUCCESS: ORE TROVATE NEL FILE:"+ore);
						
						for(String nomeFile: filedacaricare){
							loggerInfo.info("\t\tUPDATE training ANAGRAFICA:" + anag.getIdAnag() + " file:"+nomeFile+" - ANNO: "+year+" - ORE: "+ore);
							
							righeAggiornate++;
							//avrò id della tabella
							
							//entity.setOreFormazione(ore);
							//entity.setUpdateUser("BATCH_UPDATE_ORE");
							//entity.setUpdateDate(ore);
							
//							AnagBrokerTraining entity = new AnagBrokerTraining();
//							entity.setAnag(anag);
//							entity.setAnnoFormazione( Integer.parseInt(year));
//							entity.setAttestato(nomeFile);
//							entity.setOreFormazione(0d);
//							entity.setModalitaErogazione("AUTOMATICA");
//							
//							session.saveOrUpdate(entity);
						}
						
						
					}
					 
				    
				}//ho trovato almento un file
				
				
			}
			session.getTransaction().commit();
		    session.close();
		    loggerInfo.info("Righe totali aggiornate:"+righeAggiornate);
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
