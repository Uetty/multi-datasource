package com.uetty.mulds;

import com.uetty.mulds.util.RequestUrlUtil;
import com.uetty.mulds.util.SpringPropertiesPeeper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application extends SpringApplication {

	private static String normalizePath(String url) {
		return RequestUrlUtil.normalize(url);
	}

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		String uri = SpringPropertiesPeeper.getProperties("profile.uri");
		String port = SpringPropertiesPeeper.getProperties("server.port", "8080");
		String contextPath = SpringPropertiesPeeper.getProperties("server.servlet.context-path", "");

		String localPath = "http://localhost:" + port + normalizePath(contextPath);
		String externalPath = uri + normalizePath(contextPath);

		log.info("\n----------------------------------------------------------\n\t" +
						"Application is running! Access URLs:\n\t" +
						"Local: \t\t\t{}\n\t" +
						"External: \t\t{}\n\t" +
						"----------------------------------------------------------",
				localPath, externalPath);
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
