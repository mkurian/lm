package org.localmarket.dao;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.localmarket.entity.Ad;

public interface IAdDAO {

	public Object getById(String id) throws ClientProtocolException, IOException, Exception;
	
	public List<Ad> get() throws ClientProtocolException, IOException, Exception;
	
	public Ad create(Ad jobAd) throws ClientProtocolException, IOException, Exception;
	
	public Ad update(Ad jobAd, String id) throws ClientProtocolException, IOException, Exception;

	public List<Ad> search(String location, String city, String category, String buyOrSell) throws Exception;
}
