package com.owoidoho.bassey.swapigateway;

import static org.assertj.core.api.Assertions.assertThat;

import com.owoidoho.bassey.swapigateway.interfaces.api.SWAPIController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SwapiGatewayApplicationTests {

	@Autowired
	private SWAPIController swapiController;

	@Test
	void contextLoads() {
		assertThat(swapiController).isNotNull();
	}

}
