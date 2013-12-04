/**
 * 
 */
package com.qurion.businesslogic.ide.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.ide.service.IDEBuilderService;

/**
 * @author Edward Banfa
 *
 */
@Path("/idebuilder")
@Stateless
public class IDEBuilder {
	
	@Inject IDEBuilderService builderService;
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public void build(){
		try {
			builderService.build("/home/edward/Projects/businesslogic/configuration/ide-config.xml");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
