package com.hackathon.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.PropertyUtils;

public class ObjectUtils {

	public static Object create(Class<?> clazz) {
		return create(clazz);
	}

	public static void setValue(Object obj, String propertyName, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setSimpleProperty(obj, propertyName, value);
	}

	public static Object getValue(Object obj, String propertyName) {
		try {
			return PropertyUtils.getSimpleProperty(obj, propertyName);
		} catch (Exception ex) {
			Logger.getLogger(ObjectUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static void destroyObject(Object object) {
		if (object != null) {
			if (object instanceof Collection<?>) {
				((Collection) object).clear();
			} else if (object instanceof Map<?, ?>) {
				((Map) object).clear();
			}
			object = null;
		}
	}

	public static void destroyObject(Object[] objects) {
		if (objects != null) {
			for (Object object : objects) {
				destroyObject(object);
			}
			objects = null;
		}
	}

	public static Map<String, String> getMap() {
		return new HashMap<String, String>();
	}
}
