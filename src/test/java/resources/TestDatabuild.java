package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.locdetails;

public class TestDatabuild {
	
	
	public Addplace addplacePayLoad(String name, String language, String address)
	{
		Addplace ap = new Addplace();
		ap.setAccuracy(50);
//		ap.setAddress("29, side layout, cohen 09");
//		ap.setLanguage("French-IN");
//		ap.setName("Frontline house1");
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		List<String> mylist= new ArrayList<String>();
		mylist.add("shoe park1");
		mylist.add("shop");
		ap.setTypes(mylist);

		locdetails lc= new locdetails();
		lc.setLat(-38.383494);
		lc.setLat(33.427362);
		ap.setLocation(lc);
		return ap;
	}
	
	public String deletePayLoad(String placeid)
	{
		return "{\"place_id\":\""+  placeid + "\"}" ;
	}

}
