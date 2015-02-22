package org.localmarket.bizlogic;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.client.ClientProtocolException;
import org.localmarket.dao.IUserDAO;
import org.localmarket.dao.UserDAOESClient;
import org.localmarket.entity.User;
import org.localmarket.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mkurian
 *
 */
public class UserManager implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserManager.class);
	IUserDAO dao;

	public UserManager() {
		dao = new UserDAOESClient();
	}

	public Response getById(String id) throws Exception {
		logger.info("id {}", id);
		return Response.status(Status.OK).entity(dao.getById(id)).build();
	}

	public Response get() throws Exception {
		List<User> users = dao.get();
		return Response.status(Status.OK).entity(users).build();
	}

	public Response search(String location, String city, String category)
			throws ClientProtocolException, IOException, Exception {
		logger.info("search location={} city={} category={}", location, city,
				category);
		List<User> users = dao.search(location, city, category);

		logger.info("search results=", users.size());
		return Response.status(Status.OK).entity(users).build();
	}

	public Response create(HttpServletRequest request, HttpHeaders headers,
			User user) throws Exception {
		logger.info("create location={} city={} category={}",
				user.getLocation(), user.getCity(), user.getCategory());
		user = dao.create(user);
		return Response.status(Status.OK).entity(user).build();
	}

	public Response update(String id, User user, HttpServletRequest request,
			HttpHeaders headers) throws Exception {
		logger.info("update location={} city={} category={}",
				user.getLocation(), user.getCity(), user.getCategory());
		user = dao.update(user, id);
		return Response.status(Status.OK).entity(user).build();
	}
}
