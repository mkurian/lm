package org.localmarket.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * 
 * @author mkurian
 *
 */

@Path("/api/fileupload")
public interface IFileUploadService {

	@POST
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input);
}
