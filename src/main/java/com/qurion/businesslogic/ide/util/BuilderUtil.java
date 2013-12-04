/**
 * 
 */
package com.qurion.businesslogic.ide.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.EntityFieldType;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.util.StringUtil;

/**
 * @author Edward Banfa
 *
 */
public class BuilderUtil {

    private static Logger logger = LoggerFactory.getLogger(BuilderUtil.class);
    
	/**
	 * Find out if an entity belongs to a module.
	 * 
	 * @param entity the entity
	 * @param module the module
	 * @return true if the entity belongs to the module, false otherwise
	 */
	public static boolean doesEntityBelongToModule(EntityData entity, Module module)
	{
		String[] codeSubstrings = 
				entity.getCode().split(StringUtil.UNDERSCORE);
		if(codeSubstrings.length > 1)
			if (module.getCode().equals(codeSubstrings[0]))
				return true;
		return false;
	}
	
	/**
	 * Find out if a field belongs to the provided entity.
	 * 
	 * @param entityField the field
	 * @param entity the entity
	 * @return true if the field belongs to the entity, false otherwise
	 */
	public static boolean doesFieldBelongToEntity(EntityField entityField, EntityData entity) 
	{
		// Extract the entity name from the entity code
		String entityName = StringUtil.EMPTY_STRING;
		String[] entityCodeSubstrings = entity.getCode().split(StringUtil.UNDERSCORE);
		if(entityCodeSubstrings.length > 1)
			entityName = entityCodeSubstrings[1];
		// Extract the entity name from field code
		String entityNameFromString = StringUtil.EMPTY_STRING;
		String[] fieldCodeSubstrings = entityField.getCode().split(StringUtil.UNDERSCORE);
		if(fieldCodeSubstrings.length > 1)
			entityNameFromString = fieldCodeSubstrings[0];
		//Validate the results
		if(StringUtil.isValidString(entityName)  
				&& StringUtil.isValidString(entityNameFromString))
			// Compare the results
			if(entityName.equals(entityNameFromString))
				return true;
		return false;
	}

	/**
	 * Test if a {@code EntityField} is of a specific {@code EntityFieldType}
	 * @param field
	 * @param fieldType
	 * @return
	 */
	public static boolean doesFieldBelongToFieldType(
			EntityField field,	EntityFieldType fieldType) 
	{
		String fieldTypeCode = StringUtil.EMPTY_STRING;
		String[] codeSubstrings = 
				field.getCode().split(StringUtil.UNDERSCORE);
		if(codeSubstrings.length > 0 )
			fieldTypeCode = codeSubstrings[codeSubstrings.length - 1];
	    if (fieldTypeCode.equals(fieldType.getCode()))
	    	return true;
		return false;
	}
	/**
	 * Utility method to get the attributes of a node.
	 * 
	 * @param componentNode The node we are getting its attributes.
	 * @return A map of attribute names to attribute values.
	 */
	public static Map<String, String> getNodeAttributes(Node componentNode) {
		// Get the attributes of the component
		Map<String, String> attributesMap = new HashMap<String, String>();
		NamedNodeMap nodeAttributeMap = componentNode.getAttributes();
		for(int index = 0; index < nodeAttributeMap.getLength(); index++) 
		{
			Node attribute = nodeAttributeMap.item(index);
			if(attribute != null)
				attributesMap.put(
						attribute.getNodeName(), attribute.getNodeValue());
		}		
		logger.debug("Loaded node attributes: {}", attributesMap);
		return attributesMap;
	}

}
