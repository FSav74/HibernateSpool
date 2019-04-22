package it.hawk.filesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.HashAttributeSet;

public class HourFileSearch {
	
	private String[] expressions= new String[5];
	
	/**
	 * importante l'ordine di ricerca:
	 *  es:. certificati riportano piu' ore con una espressione ma le ore totali sono con le prime espressioni
	 */
	public HourFileSearch(){
		expressions[0]="(?i)(?<=Numero ore totali formazione:)([ ]{1,})\\d*";
		//expressions[1]="(?i)(?<=ore )\\d*";
		expressions[1]="(?i)(?<=durata di)([ ]{1,})\\d*";
		expressions[2]="(?i)(?<=ore )([\\d]{1,})"; //'ore ' seguito da almeno un numero
		
		//expressions[3]="(?i)\\d*(?= ore)"; 
		expressions[3]="(?i)([\\d]{1,})(?= ore)";
		//expressions[4]="(?i)\\d*(?=h )";
		expressions[4]="(?i)([\\d]{1,})(?=h )";
	}
	
	public Double find(String testo){
		Pattern pattern = null;
		Double ore = null;
        for(int i=0; i< expressions.length; i++){
        	
        	String expression = expressions[i];
        	pattern = Pattern.compile(expression);
        	Matcher matcher = pattern.matcher(testo);
        	if (matcher.find()) {
            	String result =  matcher.group();
            	String resultTrim= result.trim();
            	if ("".equals(resultTrim)) continue;
            	
            	ore = Double.parseDouble(resultTrim);
//            	System.out.println("\n RESULT:" + m.group());
            	break;
            	
            }
        	matcher.reset();
        }
        return ore;
	}

	
}
