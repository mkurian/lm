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
import org.localmarket.entity.Ad;
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
public class AdDAOESClient implements IAdDAO{
	
	private static Logger logger = LoggerFactory.getLogger(AdDAOESClient.class);
	public Ad getById(String id) throws Exception{
		Ad ad = null;
		Get adGet = new Get.Builder(Constants.INDEX, id).type(Constants.TYPE_AD).build();
		JestResult result = ESClient.getInstance().execute(adGet);
		if(result.isSucceeded()) {
			ad = result.getSourceAsObject(Ad.class); 
		}  else {
			logger.error("Exception occured while find ad with id {}", result.getJsonString());
			throw new Exception(result.getJsonString());
		}
		return ad;
	}
	
	public List<Ad> get() throws Exception{
		List<Ad> ads = null;
		SearchSourceBuilder bldr = new SearchSourceBuilder();
		bldr.size(1000); 
		Search search = new Search.Builder(bldr.toString()).addIndex(Constants.INDEX).addType(Constants.TYPE_AD).build();
		JestResult result = ESClient.getInstance().execute(search);
		if(result.isSucceeded()) {
			ads = result.getSourceAsObjectList(Ad.class);
		}  else {
			logger.error("Exception occured ", result.getJsonString());
			throw new Exception(result.getJsonString());
		}
		return ads;
	}
	
	
	
	public Ad create(Ad jobAd) throws Exception{
		String response = ElasticSearchClient.create(Constants.TYPE_AD, JSONSerializer.generateJSON(jobAd));
		JSONObject result = new JSONObject(response);
		String id = result.getString("_id");
		jobAd.setId(id);
		response = ElasticSearchClient.update(Constants.TYPE_AD, JSONSerializer.generateJSON(jobAd), id);
		
		response = ElasticSearchClient.getById(Constants.TYPE_AD, id);
		result = new JSONObject(response);
		JSONObject jobAdResult = result.getJSONObject("_source");
		
		Ad jobAdspective = (Ad) JSONDeserializer.generate("Ad", jobAdResult.toString());
		return jobAdspective;
	}
	
	public Ad update(Ad jobAd, String id) throws Exception{
		String response = ElasticSearchClient.update(Constants.TYPE_AD, JSONSerializer.generateJSON(jobAd), id);
		
		response = ElasticSearchClient.getById(Constants.TYPE_AD, id);
		JSONObject result = new JSONObject(response);
		JSONObject jobAdResult = result.getJSONObject("_source");
		
		Ad jobAdspective = (Ad)JSONDeserializer.generate("Ad", jobAdResult.toString());
		return jobAdspective;
	}

	@Override
	public List<Ad> search(String location, String city, String category) throws Exception {
		List<Ad> ads = new ArrayList<Ad>();
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
		
		filterBuilder.cache(true);
		bldr.postFilter(filterBuilder);
		bldr.size(1000); 
		
		
		String response = ElasticSearchClient.search(Constants.TYPE_AD, bldr.toString());
		JSONObject result = new JSONObject(response);
		JSONObject jobAdResult = result.getJSONObject("hits");
		JSONArray hits = jobAdResult.getJSONArray("hits");
		for(int i = 0 ; i < hits.length() ; i++){
			JSONObject hit = (JSONObject) hits.get(i);
			String source = hit.getJSONObject("_source").toString();
			
			Ad jobAd = (Ad)JSONDeserializer.generate("Ad", source.toString());
			ads.add(jobAd);
		}
		
		return ads;
	}
}
