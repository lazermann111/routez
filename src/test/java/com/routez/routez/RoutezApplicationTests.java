package com.routez.routez;

import com.routez.routez.entity.Request;
import com.routez.routez.enums.RequestStatus;
import com.routez.routez.repository.RequestRepository;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RoutezApplicationTests {
	RestTemplate restTemplate;

	@Getter
	@Value("${server.port}")
	int port;

	@Autowired
	public RoutezApplicationTests(RestTemplateBuilder builder) {
		this.restTemplate = builder
				.rootUri("http://localhost:"+port)
				.build();
	}

	@Test
	void contextLoads() {
		var res = restTemplate.getForEntity("/short/" + RequestStatus.STARTED,
				List.class);

		assertNotNull(res);
		assertNotNull(res.getBody());
	}

}
