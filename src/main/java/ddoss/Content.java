package ddoss; 

import java.util.HashMap;
import java.util.LinkedList;

public class Content {

	private LinkedList<String> registrationID;
	private HashMap<String, String> data;

	public Content() {
		registrationID = new LinkedList<>();
		data = new HashMap<>();
	}

	public void addRegId(String regID) {
		registrationID.add(regID);
	}

	public void addContent(String title, String msg) {
		data.put(title, msg);
	}
}
