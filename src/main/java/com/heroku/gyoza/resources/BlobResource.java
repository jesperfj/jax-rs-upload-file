package com.heroku.gyoza.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/blob")
public class BlobResource {

	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/plain")
    public String handleUpload(@FormDataParam("file") InputStream in,
    						   @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {

		if(fileDetail==null || fileDetail.getFileName()==null) {
		    return "No filename";
		}
		
		File f = File.createTempFile("gyoza-", "-"+fileDetail.getFileName());
		FileOutputStream out = new FileOutputStream(f);

		byte[] buf = new byte[16384];
		int len = in.read(buf);
		while(len!=-1) {
			out.write(buf,0,len);
			len = in.read(buf);
		}
		out.close();
		System.out.println("Wrote file to "+f.getAbsolutePath());
		return "File created.";
		
    }
	
}
