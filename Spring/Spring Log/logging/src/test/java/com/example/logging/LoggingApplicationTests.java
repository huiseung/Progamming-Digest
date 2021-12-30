package com.example.logging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoggingApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	void contextLoads() {
	}

	@Test
	public void log(){
		logger.trace("trace {}", 1);
		logger.debug("debug {}", 1);
		logger.info("info {}", 1);
		logger.warn("warn {}", 1);
		logger.error("error {}", 1);
	}



}
