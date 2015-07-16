package sonicchatv1

class SecurityHash {
	String securityHashDesc
	String hashId
	String hash
    static constraints = {
		securityHashDesc nullable : true;
		hash nullable : true;
		hashId nullable : true;
    }
}
