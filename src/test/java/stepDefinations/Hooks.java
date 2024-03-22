package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deleteplace")
	public void beforescenario()
	{
		System.out.println("***************i am going to start Delete ******************");
	}

}
