package org.localmarket.data;

import org.localmarket.util.Constants;


/**
 * 
 * @author mkurian
 *
 */
public class AdvertiseDAO {
	
//	private static Logger logger = LoggerFactory.getLogger(InterestDAO.class);
	
	
	public void create(String interest) throws Exception{
		ESClient.create(Constants.ADVERTISE_TYPE, interest);
	}
	
	
}
