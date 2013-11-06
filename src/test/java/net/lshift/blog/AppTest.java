package net.lshift.blog;


import static java.util.Calendar.JANUARY;
import static mockit.Mockit.restoreOriginalDefinition;
import static mockit.Mockit.setUpMocks;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.lshift.blog.App;

import net.lshift.blog.MockDate;
import net.lshift.blog.MockGregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
	private static Calendar EXPECTED_DATE = new GregorianCalendar(2000, JANUARY, 5);


    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    protected void setupMocks() {
	setUpMocks(new MockDate(EXPECTED_DATE), new MockGregorianCalendar(EXPECTED_DATE));
    }
	


    public void testApp() {
	setupMocks();
	//System.out.println("XXX: " + new MockDate(EXPECTED_DATE));
	//System.out.println("YYY: " + new java.util.Date());
	assertEquals("Wed Jan 05 00:00:00 GMT 2000", App.getDate().toString());
    }



	protected void restore() throws InterruptedException {
		restoreOriginalDefinition(Date.class);
		restoreOriginalDefinition(GregorianCalendar.class);
		
		// Check restoration is complete
		Date date1 = new Date();
		Thread.sleep(100);
		Date date2 = new Date();
		assert date1.getTime() != date2.getTime();
	}
	

}
