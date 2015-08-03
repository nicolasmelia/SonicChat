package sonicchatv1

class Tiles {
	
	String tileDisplayName
	String tileID
	String tileScriptCode 
	String tileHtmlCode
	String tileViewController 
	

    static constraints = {
		tileDisplayName nullable : true;
		tileID nullable : true;
		tileScriptCode nullable : true;
		tileHtmlCode nullable : true;
    }
}
