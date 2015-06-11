package sonicchatv1

class HostData {
	int totalActiveHost;
	boolean systemActive;
	static constraints = {
		totalActiveHost nullable : true;
		systemActive nullable : true;
	}
}
