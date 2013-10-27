/**
 * 
 */
package com.qurion.businesslogic.application.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.qurion.businesslogic.application.model.EntityField;

/**
 * @author Edward Banfa 
 *
 */
public class PropertyUtil {
	
	
	
	/**
	 * @param fromObj
	 * @param toObj
	 * @param excludedFields
	 */
	public static void copyProperties(Object fromObj, Object toObj, List<EntityField> includedFields) {
	    Class<? extends Object> fromClass = fromObj.getClass();
	    Class<? extends Object> toClass = toObj.getClass();
	    try {
	        BeanInfo fromBean = Introspector.getBeanInfo(fromClass);
	        BeanInfo toBean = Introspector.getBeanInfo(toClass);

	        PropertyDescriptor[] toPd = toBean.getPropertyDescriptors();
	        List<PropertyDescriptor> fromPd = Arrays.asList(fromBean
	                .getPropertyDescriptors());
	        
	        for (PropertyDescriptor toPropertyDescriptor : toPd) 
	        {
		        for (PropertyDescriptor fromPropertyDescriptor : fromPd) 
		        {
		            if (fromPropertyDescriptor.getName().equals(toPropertyDescriptor.getName()) && 
		            		!fromPropertyDescriptor.getName().equals("class")) 
		            {
		        	copyProperty(toObj, fromObj, toPropertyDescriptor, fromPropertyDescriptor, includedFields);
		            }
		        }
	        }
	    } catch (IntrospectionException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * @param fromObj
	 * @param toObj
	 * @param excludedFields
	 */
	public static void copyProperties(Object fromObj, Object toObj) {
	    Class<? extends Object> fromClass = fromObj.getClass();
	    Class<? extends Object> toClass = toObj.getClass();
	    try {
	        BeanInfo fromBean = Introspector.getBeanInfo(fromClass);
	        BeanInfo toBean = Introspector.getBeanInfo(toClass);

	        PropertyDescriptor[] toPd = toBean.getPropertyDescriptors();
	        List<PropertyDescriptor> fromPd = Arrays.asList(fromBean
	                .getPropertyDescriptors());
	        
	        for (PropertyDescriptor toPropertyDescriptor : toPd) 
	        {
		        for (PropertyDescriptor fromPropertyDescriptor : fromPd) 
		        {
		            if (fromPropertyDescriptor.getName().equals(toPropertyDescriptor.getName()) && 
		            		!fromPropertyDescriptor.getName().equals("class")) 
		            {
		        	copyProperty(toObj, fromObj, toPropertyDescriptor, fromPropertyDescriptor);
		            }
		        }
	        }
	    } catch (IntrospectionException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * @param toObject
	 * @param fromObject
	 * @param toPropertyDescriptor
	 * @param fromPropertyDescriptor
	 * @param includedFields
	 */
	private static void copyProperty(Object toObject, Object fromObject, 
			PropertyDescriptor toPropertyDescriptor, PropertyDescriptor fromPropertyDescriptor, List<EntityField> includedFields)
	{
             try 
             {
				if(toPropertyDescriptor.getWriteMethod() != null) 
				{  
					boolean excludeField = true;
					for(EntityField includedField: includedFields)
						if (fromPropertyDescriptor.getName().equals(includedField.getName()))
							excludeField = false;
					// Copy only if field is on include list
					if(!excludeField)
				        toPropertyDescriptor.getWriteMethod().invoke(toObject, 
				        		fromPropertyDescriptor.getReadMethod().invoke(fromObject, null));
				 }
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	}
	/**
	 * @param toObject
	 * @param fromObject
	 * @param toPropertyDescriptor
	 * @param fromPropertyDescriptor
	 * @param includedFields
	 */
	private static void copyProperty(Object toObject, Object fromObject, 
			PropertyDescriptor toPropertyDescriptor, PropertyDescriptor fromPropertyDescriptor)
	{
             try 
             {
				if(toPropertyDescriptor.getWriteMethod() != null) 
				{  
					toPropertyDescriptor.getWriteMethod().invoke(toObject, 
				        		fromPropertyDescriptor.getReadMethod().invoke(fromObject, null));
				 }
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	}
	
	public static Object stringToActualType(Class propertyClass, String propertyValue) {

		if(propertyValue == null)
			return propertyValue;
		// Deal with String properties
		if(propertyClass.getName().equals("java.lang.String"))
			return propertyValue;
		// Deal with the Boolean case
		if(propertyClass.getName().equals("java.lang.Character"))
		{
			return Character.valueOf(propertyValue.charAt(0));
		}
		// Deal with Integer properties
		if(propertyClass.getName().equals("java.lang.Integer"))
		{
			try {
				return Integer.parseInt(propertyValue);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Deal with Date properties
		if(propertyClass.getName().equals("java.util.Date"))
		{
			try {
				return DateUtil.convertStringToJavaDate(propertyValue);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Deal with the Boolean case
		if(propertyClass.getName().equals("java.lang.Boolean"))
		{
			return Boolean.parseBoolean(propertyValue);
		}
		// Deal with the related properties
		if(propertyClass.getName().contains(EntityUtil.BASE_PACKAGE_NM))
		{
			
		}
		return propertyValue;
	}
	
	public static <T> List<T> copyIterator(Iterator<T> iter) {
	    List<T> copy = new ArrayList<T>();
	    while (iter.hasNext())
	        copy.add(iter.next());
	    return copy;
	}

}
