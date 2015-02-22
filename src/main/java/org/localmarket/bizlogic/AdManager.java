package org.localmarket.bizlogic;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.client.ClientProtocolException;
import org.localmarket.dao.AdDAOESClient;
import org.localmarket.dao.IAdDAO;
import org.localmarket.entity.Ad;
import org.localmarket.service.IAdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mkurian
 *
 */
public class AdManager implements IAdService {
	private static Logger logger = LoggerFactory.getLogger(AdManager.class);
	IAdDAO dao;

	public AdManager() {
		dao = new AdDAOESClient();
	}

	public Response getById(String id) throws Exception {
		logger.info("get id={}", id);
		return Response.status(Status.OK).entity(dao.getById(id)).build();
	}

	public Response get() throws Exception {
		List<Ad> ads = dao.get();
		return Response.status(Status.OK).entity(ads).build();
	}

	public Response search(String location, String city, String category)
			throws ClientProtocolException, IOException, Exception {
		logger.info("search location={} city={} category={}", location, city,
				category);
		List<Ad> ads = dao.search(location, city, category);

		logger.info("search results={}", ads.size());
		return Response.status(Status.OK).entity(ads).build();
	}

	public Response create(HttpServletRequest request, HttpHeaders headers,
			Ad ad) throws Exception {
		logger.info("create location={} city={} category={}", ad.getLocation(),
				ad.getCity(), ad.getCategory());
		ad = dao.create(ad);
		return Response.status(Status.OK).entity(ad).build();
	}

	public Response update(String id, Ad ad, HttpServletRequest request,
			HttpHeaders headers) throws Exception {
		logger.info("update location={} city={} category={}", ad.getLocation(),
				ad.getCity(), ad.getCategory());
		ad = dao.update(ad, id);
		return Response.status(Status.OK).entity(ad).build();
	}
}
