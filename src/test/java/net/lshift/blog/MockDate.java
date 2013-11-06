package net.lshift.blog;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import mockit.Mock;
import mockit.MockClass;

@MockClass(realClass = Date.class)
public class MockDate {
	
	private Calendar currentDate = null;
	
	public Date it;
	
	public MockDate(Calendar currentDate) {
		this.currentDate = currentDate;
	}
	
	@Mock
	public void $init() {
		try {
			Field field = Date.class.getDeclaredField("fastTime");
			field.setAccessible(true);
			field.set(it, currentDate.getTimeInMillis());
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error mocking date class", e);
		}
	}
}