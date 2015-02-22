//package org.localmarket;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.Context;
//
//import org.localmarket.data.InterestDAO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 
// * @author mkurian
// *
// */
//
//@Path("api/interest")
//public class InterestService {
//
//	private static final Logger logger = LoggerFactory
//			.getLogger(InterestService.class);
//
//	InterestDAO dao = null;
//
//	public InterestService() {
//		dao = new InterestDAO();
//	}
//
//	@POST
//	public String interestSubmission(@Context HttpServletRequest request) {
//
//		try {
//			String data = request.getReader().readLine();
//			logger.info("Interest {}", data);
//			if (data != null) {
//				// Interest i = new Interest(data);
//				dao.create(data);
//			}
//			return data.toString();
//		} catch (Exception ex) {
//			logger.error(ex.getMessage());
//			return "error " + ex.getMessage();
//		}
//	}
//}
