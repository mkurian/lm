package org.localmarket.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.localmarket.entity.Ad;

/**
 * 
 * @author mkurian
 *
 */

@Path("/api/ads")
public interface IAdService {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getById(@PathParam(value = "id") String id)
			throws Exception;

	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response update(@PathParam(value = "id") String id, Ad ad,
			@Context HttpServletRequest request, @Context HttpHeaders headers)
			throws Exception;

	@POST
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response create(@Context HttpServletRequest request,
			@Context HttpHeaders headers, Ad ad) throws Exception;

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response search(
	@QueryParam("location") String location, @QueryParam("city") String city,
	@QueryParam("category") String category, @QueryParam("buyorsell") String buyOrSell) throws Exception;
}
