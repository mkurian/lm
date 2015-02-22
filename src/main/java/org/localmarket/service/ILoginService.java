package org.localmarket.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author mkurian
 *
 */

@Path("/api/login")
public interface ILoginService {


	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response create(String email, String password,
			@Context HttpServletRequest request, @Context HttpHeaders headers)
			throws Exception;

	@POST
	@Path("/signin")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response signin(String email, String password, @Context HttpServletRequest request,
			@Context HttpHeaders headers) throws Exception;

	
	@POST
	@Path("/signout")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response signout(String userId, HttpServletRequest request,
			HttpHeaders headers) throws Exception;

}
