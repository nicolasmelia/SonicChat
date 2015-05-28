package dataLogicLayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class DataURLs {
	
	// private final static String SERVERURL = "http://localhost:8080/SonicChatV1/DataAccess";
	private final static String SERVERURL = "http://default-environment-pbx8hvmjyf.elasticbeanstalk.com//DataAccess";


	// ***** DATA FROM URL ***** 
	// ** Makes reference to DataAccessController plain text request!
	public static String getSiteNameByID(String SiteID){
		// Returns the clients URL
		return getStringData("getSiteNameByID", SiteID);
	}

	
	
	

	// ***** CONNECTION TO URL ***** 
	// Returns Data from URL (Url must be in plain text)
	public static String getStringData(String link, String ID) {
		String data;
		try {
	        URL whatismyip = new URL(SERVERURL + "/" + link + "/" + ID);
	        BufferedReader in = null;
	        try {
	            in = new BufferedReader(new InputStreamReader(
	                    whatismyip.openStream()));
	            data = in.readLine();
	        } finally {
	            if (in != null) {
	                in.close();
	            }
	        }
		} catch (Exception ex) {
			data = "NONE";
		}
		return 	data;
    }
	
}
