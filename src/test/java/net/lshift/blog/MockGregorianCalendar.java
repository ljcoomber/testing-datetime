package net.lshift.blog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import mockit.Mock;
import mockit.MockClass;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarSystem;

/**
 * Mocks the GregorianCalendar instantiation so that the default date can be set by a test.
 * 
 * TODO LJC: setField() and invokeMethod() should be part of ReflectionUtil
 * however, currently that class is not accessible because this one is loaded on
 * the boot class path.
 * 
 * 
 */
@MockClass(realClass = GregorianCalendar.class)
public class MockGregorianCalendar {

	private Calendar currentDate = null;

	public GregorianCalendar it;

	public MockGregorianCalendar(Calendar currentDate) {
		this.currentDate = currentDate;
	}

	@Mock
	public void $init(TimeZone zone, Locale aLocale) {
		setField(Calendar.class, it, "fields", new int[GregorianCalendar.FIELD_COUNT]);
		setField(Calendar.class, it, "isSet", new boolean[GregorianCalendar.FIELD_COUNT]);
		setField(Calendar.class, it, "stamp", new int[GregorianCalendar.FIELD_COUNT]);
		setField(Calendar.class, it, "zone", zone);

		invokeMethod(Calendar.class, it, "setWeekCountData", aLocale);
		setField(GregorianCalendar.class, it, "gdate", (BaseCalendar.Date) CalendarSystem.getGregorianCalendar()
				.newCalendarDate(zone));

		it.setTimeInMillis(currentDate.getTimeInMillis());
	}

	private static void setField(Class<? extends Calendar> clazz, Object obj, String fieldName, Object fieldValue) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(obj, fieldValue);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error setting field " + fieldName + " on " + obj, e);
		}
	}

	private static void invokeMethod(Class<? extends Calendar> clazz, Object obj, String methodName, Object param) {
		try {
			Method method = clazz.getDeclaredMethod(methodName, param.getClass());
			method.setAccessible(true);
			method.invoke(obj, param);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error invoking method " + methodName + " on " + obj, e);
		}
	}
}