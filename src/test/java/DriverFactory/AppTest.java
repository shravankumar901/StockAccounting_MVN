package DriverFactory;

import org.testng.annotations.Test;

import DriverFactory.DriverScript2;

public class AppTest {

	@Test
	public void startTest() throws Throwable{
		 DriverScript2 d= new DriverScript2();
		 d.startTest();
	}
}
