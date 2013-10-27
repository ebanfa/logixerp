/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * REST service to build the DB entries that do the
 * following:
 * 1. Correct the SQL for the {@code Module} and {@code EntityData}.
 * 2. Correct the SQL for the {@code Activity} and {@code ActivityGroup}.
 * 3. Build the {@code UiComponent} and {@code UiComponentType} model
 * 3. Build the {@code UiComponentAttribute} and {@code UiComponentAttributeType} model
 * 
 * Fetch all the modules
 * Fetch all the entities
 * Fetch all the entity field
 * Fetch all the entity field data type
 * 
 * @author Edward Banfa
 *
 */
@Path("/dbBuilder")
@Stateless
public class DBBuilderService extends AbstractRESTService {

}
