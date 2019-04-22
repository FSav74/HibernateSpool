package it.hawk.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

  private String fileNameToSearch;
  private String yearToSearch;
  private List<String> result = new ArrayList<String>();
  private List<String> resultOnlyName = new ArrayList<String>();
	
  public String getFileNameToSearch() {
	return fileNameToSearch;
  }

  public void setFileNameToSearch(String fileNameToSearch) {
	this.fileNameToSearch = fileNameToSearch;
  }

  public List<String> getResult() {
	return result;
  }



  public List<String> getResultOnlyName() {
	return resultOnlyName;
}

public void setResultOnlyName(List<String> resultOnlyName) {
	this.resultOnlyName = resultOnlyName;
}

public String getYearToSearch() {
	return yearToSearch;
}

public void setYearToSearch(String yearToSearch) {
	this.yearToSearch = yearToSearch;
}

public void searchDirectory(File directory, String fileNameToSearch, String yearToSearch) {

	setFileNameToSearch(fileNameToSearch);
	setYearToSearch(yearToSearch);

	if (directory.isDirectory()) {
	    search(directory);
	} else {
	    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
	}

  }

  private void search(File file) {

	if (file.isDirectory()) {
//	  System.out.println("Searching directory ... " + file.getAbsoluteFile());
		
            //do you have permission to read this directory?	
	    if (file.canRead()) {
		for (File temp : file.listFiles()) {
		    if (temp.isDirectory()) {
			search(temp);
		    } else {
			//if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {	
		    	if ( temp.getName().toUpperCase().contains(getFileNameToSearch().toUpperCase())) {
		    		if ( temp.getName().toUpperCase().contains(getYearToSearch().toUpperCase())) {
		    				result.add(temp.getAbsoluteFile().toString());
		    				String path = temp.getAbsoluteFile().toString();
		    				if(path!=null){
		    					int index = path.lastIndexOf("\\");
		    					if(index!=-1){
			    					path = path.substring(index+1);
			    					
			    					resultOnlyName.add(path);
		    					}
		    				}
		    				
		    		}
		    }

		}
	    }

	 } else {
		System.out.println(file.getAbsoluteFile() + "Permission Denied");
	 }
      }

  }

}

