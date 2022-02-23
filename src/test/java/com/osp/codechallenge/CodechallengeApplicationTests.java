package com.osp.codechallenge;

import com.osp.codechallenge.controller.ShipmentsController;
import com.osp.codechallenge.repositories.ShipmentsRepository;
import com.osp.codechallenge.service.ShipmentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class CodechallengeApplicationTests {


	@Autowired
	private ShipmentsController controller;

	@MockBean
	private ShipmentsService shipmentsService;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
