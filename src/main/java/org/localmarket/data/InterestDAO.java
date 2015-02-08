package org.localmarket.data;

import org.localmarket.util.Constants;


/**
 * 
 * @author mkurian
 *
 */
public class InterestDAO {
	
//	private static Logger logger = LoggerFactory.getLogger(InterestDAO.class);
	
	
	public void create(String interest) throws Exception{
		ESClient.create(Constants.INTEREST_TYPE, interest);
	}
	
	
}
