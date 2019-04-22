package it.hawk.dbtools.parser;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import java.awt.image.PixelGrabber;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.lept.PIXA;
import org.bytedeco.javacpp.lept.PIXCMAP;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.ochafik.lang.jnaerator.runtime.NativeSizeByReference;
import com.sun.jna.Structure;

import net.sourceforge.lept4j.Leptonica;
import net.sourceforge.lept4j.Leptonica1;
import net.sourceforge.lept4j.Pix;
import net.sourceforge.lept4j.Pixa;

public class OCRParser {
	
	private TessBaseAPI api = null;
	private PIX image = null;
	private BytePointer outText;
	public OCRParser(){
		api = new TessBaseAPI();
		// Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init("C:\\DOCUMENTI\\tessdata", "ita") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
	}
	
	
	public String parse(String matchedFile) throws Exception{
		// Open input image with leptonica library matched
        //PIX image = pixRead(args.length > 0 ? args[0] : "/usr/src/tesseract/testing/phototest.tif");
		
        try{
        	
        	
        	
        	image = pixRead(matchedFile);
        	//Pixa pixa =  Leptonica1.pixaReadMultipageTiff(matchedFile);
        	//
        	//Structure[] array = pixa.toArray(dimensione);
//        	Leptonica leptInstance = Leptonica.INSTANCE;
//        	//Pixa pixa =  leptInstance.pixaReadMultipageTiff(matchedFile);
//        	
//        	Pix pix =  leptInstance.pixReadFromMultipageTiff(matchedFile,
//        			new NativeSizeByReference());
//        	
//        	pix.g
//        		api.SetImage(pix);
        	

        	
        	api.SetImage(image);
	        // Get OCR result
	        outText = api.GetUTF8Text();
	        System.out.println("OCR output:\n" + outText.getString());
	        
	        
	        //ore = fourFileSearch.find(outText.getString());
	        
	        api.End();
	        outText.deallocate();
	        pixDestroy(image);
	        
	        return outText.getString();
        }catch (Exception e){
        	throw e;
        }
	}
	
	public void close(){
		 api.End();
        outText.deallocate();
        pixDestroy(image);
	}
}
