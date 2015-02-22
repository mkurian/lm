package org.localmarket.bizlogic;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.localmarket.dao.IUserDAO;
import org.localmarket.dao.UserDAOESClient;
import org.localmarket.entity.User;
import org.localmarket.service.ILoginService;
import org.localmarket.util.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginManager implements ILoginService {

	private static Logger logger = LoggerFactory.getLogger(UserManager.class);
	IUserDAO dao;

	public LoginManager() {
		dao = new UserDAOESClient();
	}

	@Override
	public Response create(String email, String password,
			HttpServletRequest request, HttpHeaders headers) {
		try {
			if (email != null && password != null) {
				User user = new User();
				user.setEmailId(email);
				user.setPasskey(password);

				logger.info("create user ");
				user = dao.create(user);
				return Response.status(Status.OK).entity(user).build();
			}
		} catch (Exception ex) {
			logger.info("User creation failed {}", email);
		}
		return Response.status(Status.BAD_REQUEST)
				.entity("User creation failed").build();
	}

	@Override
	public Response signin(String email, String password,
			HttpServletRequest request, HttpHeaders headers) {
		try {
			if (email != null && password != null) {
				User user = new User();
				user.setEmailId(email);
				user.setPasskey(password);

				logger.info("login");
				String token = generateToken(user);
				TokenManager.addUser(email, token);
				return Response.status(Status.OK).entity(token).build();
			}
		} catch (Exception ex) {
			logger.info("login failed {}", email);
		}
		return Response.status(Status.BAD_REQUEST).entity("Login failed.")
				.build();
	}

	
	private String generateToken(User user) throws Exception{
		
		String token = null;
		try{
			User userFound = dao.searchUser(user.getEmailId(), user.getPasskey());
			if(userFound == null) {
				throw new Exception("Incorrect login");
			}
			token = UUID.randomUUID().toString();
			TokenManager.addUser(userFound.getId(), token);
		}catch(Exception ex){
			throw ex;
		}
		return token;
	}
	
	@Override
	public Response signout(String userId,
			HttpServletRequest request, HttpHeaders headers) throws Exception {
		try {
				TokenManager.removeUser(userId);
				return Response.status(Status.OK).entity("success").build();
			
		} catch (Exception ex) {
			logger.info("logout failed {}", userId);
		}
		return Response.status(Status.BAD_REQUEST).entity("Logout failed.")
				.build();
	}

}
