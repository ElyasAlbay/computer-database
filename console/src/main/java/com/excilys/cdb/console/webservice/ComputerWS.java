package com.excilys.cdb.console.webservice;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.excilys.cdb.binding.ComputerMapper;
import com.excilys.cdb.model.dto.ComputerDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComputerWS {
	private static final String BASE_URL = "http://localhost:8080/cdb/computer";

	public static void getPage(int pageNumber) {
		ObjectMapper mapper = new ObjectMapper();
		
		String page = "/page/";
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(BASE_URL + page + pageNumber);
		
		Response response = target.request().get();

		String value = response.readEntity(String.class);
		
		try {
			List<ComputerDto> list = mapper.readValue(value, new TypeReference<List<ComputerDto>>(){});
			System.out.println(ComputerMapper.createObject(list.get(0)).toString());
		} catch (IOException e) {
			
			System.out.println("Could not read JSon value.");
		}
		response.close();

	}
}
