package org.localmarket;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mkurian
 *
 */

@Path("api/inquiry")
public class InquiryService {

	private static final Logger logger = LoggerFactory
			.getLogger(InquiryService.class);

	@GET
	@Path("/buy")
	public String buy(@QueryParam("data") String buy, 
			@QueryParam("callback") String callback) {
		logger.info("Buy {}", buy);
		if(callback != null && !callback.isEmpty()){
			buy = String.format("%1$s(%2$s)", callback, buy);
		}
		return buy;
	}

	@GET
	@Path("/sell")
	public String sell(@QueryParam("data") String sell,
			@QueryParam("callback") String callback) {
		logger.info("Sell {}", sell);
		if(callback != null && !callback.isEmpty()){
			sell = String.format("%1$s(%2$s)", callback, sell);
		}
		return sell;
	}
}
