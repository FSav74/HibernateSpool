package it.hawk.dbtools.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader{
	
	static public String getText(File pdfFile) throws IOException {
	    PDDocument doc = PDDocument.load(pdfFile);
	    return new PDFTextStripper().getText(doc);
	}
	
	static public String getText(String pathname) throws IOException {
		File pdfFile = new File(pathname);
	    PDDocument doc = PDDocument.load(pdfFile);
	    return new PDFTextStripper().getText(doc);
	}
	
//    public static void main(String args[]) {
//        PDFTextStripper pdfStripper = null;
//        PDDocument pdDoc = null;
//        COSDocument cosDoc = null;
//        File file = new File("C:/my.pdf");
//        try {
//            // PDFBox 2.0.8 require org.apache.pdfbox.io.RandomAccessRead 
//        	RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
//             PDFParser parser = new PDFParser(randomAccessFile);
//
////            PDFParser parser = new PDFParser(new FileInputStream(file));
//            parser.parse();
//            cosDoc = parser.getDocument();
//            pdfStripper = new PDFTextStripper();
//            pdDoc = new PDDocument(cosDoc);
//            pdfStripper.setStartPage(1);
//            pdfStripper.setEndPage(5);
//            String parsedText = pdfStripper.getText(pdDoc);
//            System.out.println(parsedText);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } 
//    }
}
