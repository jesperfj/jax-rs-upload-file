package com.heroku.gyoza.resources;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/helloworld")
public class HelloWorldResource {

	@GET
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello World";
	}
	
	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/plain")
    public String handleUpload(@FormDataParam("file") InputStream stream,
    						   @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {
		if(fileDetail==null) {
			return "Git it. (No file detail).";
		}
		else {
			return "Got file: "+fileDetail.getFileName();
		}
    }
}
