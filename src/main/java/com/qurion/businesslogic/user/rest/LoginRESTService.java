/**
 * 
 */
package com.qurion.businesslogic.user.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qurion.businesslogic.application.rest.AbstractRESTService;
import com.qurion.businesslogic.application.rest.data.ResponseData;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectResponse;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.data.SearchFieldData;
import com.qurion.businesslogic.businessobject.service.BusinessObjectSearchService;
import com.qurion.businesslogic.businessobject.util.BusinessObjectQueryConstants;

/**
 * @author Edward Banfa
 *
 */
@Path("/login-service")
@Stateless
public class LoginRESTService extends AbstractRESTService {
	
	public static final String USER_NAME_FIELD = "userNm";
	public static final String PASSWORD_FIELD = "password";
	
	@Inject ActivityService activityService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseData login(@QueryParam(value = "userName") String userName, 
			@QueryParam(value = "password") String password)
	{
		BusinessObjectResponse businessObjectResponse = new BusinessObjectResponse();
		try {
			SearchData userSearchData = new SearchData("SystemUser");
			// User name field
			userSearchData.addField(USER_NAME_FIELD, new SearchFieldData(
					USER_NAME_FIELD, userName, null, BusinessObjectQueryConstants.EQUALS_TO));
			// Password
			userSearchData.addField(PASSWORD_FIELD, new SearchFieldData(
					PASSWORD_FIELD, password, null, BusinessObjectQueryConstants.EQUALS_TO));
			// Perform the search
			List<BusinessObjectFieldData> entityFields = 
					activityService.getBusinessObjectEditFields(EntityUtil.SYSTEM_USER_BO_NM);
			if(!userSearchData.isEmpty())
			{
				List<BusinessObjectData> results = 
						businessObjectSearchService.find(userSearchData, entityFields);
				if(!results.isEmpty())
					businessObjectResponse.setData(results.get(0));
				else
					businessObjectResponse.setErrors(true);
			}
		} catch (ApplicationException e) {
			this.processRESTException(e, businessObjectResponse);
		}

		return businessObjectResponse;
	}

}
