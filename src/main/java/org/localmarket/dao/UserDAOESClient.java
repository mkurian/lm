package org.localmarket.dao;

import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.localmarket.data.ESClient;
import org.localmarket.data.ElasticSearchClient;
import org.localmarket.entity.User;
import org.localmarket.util.Constants;
import org.localmarket.util.JSONDeserializer;
import org.localmarket.util.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mkurian
 *
 */
public class UserDAOESClient implements IUserDAO{
	
	private static Logger logger = LoggerFactory.getLogger(UserDAOESClient.class);
	public User getById(String id) throws Exception{
		User user = null;
		Get userGet = new Get.Builder(Constants.INDEX, id).type(Constants.TYPE_USER).build();
		JestResult result = ESClient.getInstance().execute(userGet);
		if(result.isSucceeded()) {
			user = result.getSourceAsObject(User.class); 
		}  else {
			logger.error("Exception occured while find user with id {}", result.getJsonString());
			throw new Exception(result.getJsonString());
		}
		return user;
	}
	
	public List<User> get() throws Exception{
		List<User> users = null;
		SearchSourceBuilder bldr = new SearchSourceBuilder();
		bldr.size(1000); 
		Search search = new Search.Builder(bldr.toString()).addIndex(Constants.INDEX).addType(Constants.TYPE_USER).build();
		JestResult result = ESClient.getInstance().execute(search);
		if(result.isSucceeded()) {
			users = result.getSourceAsObjectList(User.class);
		}  else {
			logger.error("Exception occured ", result.getJsonString());
			throw new Exception(result.getJsonString());
		}
		return users;
	}
	
	
	
	public User create(User user) throws Exception{
		String response = ElasticSearchClient.create(Constants.TYPE_USER, JSONSerializer.generateJSON(user));
		JSONObject result = new JSONObject(response);
		String id = result.getString("_id");
		user.setId(id);
		response = ElasticSearchClient.update(Constants.TYPE_USER, JSONSerializer.generateJSON(user), id);
		
		response = ElasticSearchClient.getById(Constants.TYPE_USER, id);
		result = new JSONObject(response);
		JSONObject userResult = result.getJSONObject("_source");
		
		User userspective = (User) JSONDeserializer.generate("User", userResult.toString());
		return userspective;
	}
	
	public User update(User user, String id) throws Exception{
		String response = ElasticSearchClient.update(Constants.TYPE_USER, JSONSerializer.generateJSON(user), id);
		
		response = ElasticSearchClient.getById(Constants.TYPE_USER, id);
		JSONObject result = new JSONObject(response);
		JSONObject userResult = result.getJSONObject("_source");
		
		User userspective = (User)JSONDeserializer.generate("User", userResult.toString());
		return userspective;
	}

	@Override
	public List<User> search(String location, String city, String category) throws Exception {
		List<User> users = new ArrayList<User>();
		SearchSourceBuilder bldr = new SearchSourceBuilder();
		BoolFilterBuilder filterBuilder = FilterBuilders.boolFilter();
		
		if(location != null){
			filterBuilder.should(FilterBuilders.prefixFilter("location", location.toLowerCase()));
		}
		if(city != null){
			filterBuilder.must(FilterBuilders.prefixFilter("city", city.toLowerCase()));
		}
		
		if(category != null){
			filterBuilder.must(FilterBuilders.prefixFilter("category", category.toLowerCase()));
		}
		
//		filterBuilder.cache(true);
		bldr.postFilter(filterBuilder);
		bldr.size(1000); 
		
		
		String response = ElasticSearchClient.search(Constants.TYPE_USER, bldr.toString());
		JSONObject result = new JSONObject(response);
		JSONObject userResult = result.getJSONObject("hits");
		JSONArray hits = userResult.getJSONArray("hits");
		for(int i = 0 ; i < hits.length() ; i++){
			JSONObject hit = (JSONObject) hits.get(i);
			String source = hit.getJSONObject("_source").toString();
			
			User user = (User)JSONDeserializer.generate("User", source.toString());
			users.add(user);
		}
		
		return users;
	}
}
