package com.hb.springpersistence.repositories;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hb.springpersistence.dto.TagDTO;

@Component
public class TagProxy {

	public List<TagDTO> getTags() {

		RestTemplate template = new RestTemplate();

		ResponseEntity<List<TagDTO>> response = template.exchange(
				"http://localhost:9001/api/tag", 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<TagDTO>>() {}
				);
		return response.getBody();
	}

}
