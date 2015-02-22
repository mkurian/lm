package org.localmarket.dao;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.localmarket.entity.User;

public interface IUserDAO {

	public Object getById(String id) throws ClientProtocolException, IOException, Exception;
	
	public List<User> get() throws ClientProtocolException, IOException, Exception;
	
	public List<User> search(String location, String city, String category) throws ClientProtocolException, IOException, Exception;
	
	public User create(User user) throws ClientProtocolException, IOException, Exception;
	
	public User update(User user, String id) throws ClientProtocolException, IOException, Exception;
}
