package com.root.app.gpt;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GPTEx {
	
	String api = "";
	
	String url = "https://api.openai.com/v1/chat/completions";
	
	public void ex2() throws Exception {
		
		WebClient webClient = WebClient.builder()
							 .baseUrl(url)
							 .defaultHeader("Content-Type", "application/json")
							 .defaultHeader("Authorization", "Bearer "+api)
							 .build();
		
		Map<String, Object> msg = new HashMap<>();
		msg.put("role", "user");
		msg.put("content", "hi");
		
		List<Map<String, Object>> li = new ArrayList<>();
		li.add(msg);
		
		Map<String, Object> map = new HashMap<>();
		map.put("model", "gpt-3.5-turbo");
		map.put("messages", li);
		map.put("max_tokens", 50);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String body = mapper.writeValueAsString(map);
		
		Mono<String> result = webClient.post()
				 					   .bodyValue(body)
				 					   .retrieve()
				 					   .bodyToMono(String.class);
		
		log.info("{}", result.block());
		
	}
	
	public void ex1() throws Exception {
		
		
		HttpClient client = HttpClient.newHttpClient();
		
		Map<String, Object> msg = new HashMap<>();
		msg.put("role", "user");
		msg.put("content", "hi");
		
		List<Map<String, Object>> li = new ArrayList<>();
		li.add(msg);
		
		Map<String, Object> map = new HashMap<>();
		map.put("model", "gpt-3.5-turbo");
		map.put("messages", li);
		map.put("max_tokens", 50);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String body = mapper.writeValueAsString(map);
		
		HttpRequest request = HttpRequest.newBuilder()
										 .uri(URI.create(url))
										 .header("Content-Type", "application/json")
										 .header("Authorization", "Bearer "+api)
										 .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
										 .build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		log.info("{}", response.body());
	}

}
