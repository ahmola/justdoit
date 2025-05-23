package dev.practice.contentcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ContentCalendarApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context
				= SpringApplication.run(ContentCalendarApplication.class, args);
		Object bean = (RestTemplate)context.getBean("restTemplate");
		System.out.println(bean);
	}

}
