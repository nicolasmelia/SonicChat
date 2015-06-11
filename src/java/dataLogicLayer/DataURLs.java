package dataLogicLayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class DataURLs {
	
	// private final static String SERVERURL = "http://localhost:8080/SonicChatV1/DataAccess";
	private final static String SERVERURL = "http://sonicchat.elasticbeanstalk.com/DataAccess";


	// ***** DATA FROM URL ***** 
	// ** Makes reference to DataAccessController plain text request!
	public static String getSiteNameByID(String SiteID){
		// Returns the clients URL
		return getStringData("getSiteNameByID", SiteID);
	}

	

	// ***** CONNECTION TO URL ***** 
	// Returns Data from URL (Url must be in plain text and link must be def in controller exact name)
	public static String getStringData(String link, String ID) {
		String data;
		try {
	        URL url = new URL(SERVERURL + "/" + link + "/" + ID);
	        BufferedReader in = null;
	        try {
	            in = new BufferedReader(new InputStreamReader(
	                    url.openStream()));
	            data = in.readLine();
	        } finally {
	            if (in != null) {
	                in.close();
	            }
	        }
		} catch (Exception ex) {
			data = "NO-DATA";
		}
		return 	data;
    }
	
}
