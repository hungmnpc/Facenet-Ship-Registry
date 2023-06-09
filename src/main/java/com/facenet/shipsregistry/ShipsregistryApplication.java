package com.facenet.shipsregistry;

import com.facenet.shipsregistry.entity.AuditorAwareImpl;
import com.facenet.shipsregistry.entity.User;
import com.facenet.shipsregistry.modal.ParamValueDTO;
import com.facenet.shipsregistry.modal.UserDTO;
import com.facenet.shipsregistry.request.*;
import com.facenet.shipsregistry.service.AuthService;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import com.facenet.shipsregistry.service.ParamValueService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.text.ParseException;
import java.time.LocalDate;

@SecurityScheme(
		name = "bearerAuth",
		scheme = "bearer",
		bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP,
		in = SecuritySchemeIn.HEADER
)
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.facenet.shipsregistry.**"})
@ComponentScan(basePackages = {"com.facenet.shipsregistry.**"})
public class ShipsregistryApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
		System.setProperty("file.encoding","UTF-8");
		ApplicationContext context = SpringApplication.run(ShipsregistryApplication.class, args);

	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new 	AuditorAwareImpl();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(AuthService authService) {
		return args -> {
			if (!authService.isExists("admin")) {
				UserDTO admin = authService.saveNewUser(new NewUserRequest(
						"admin",
						"admin",
						"admin",
						"",
						"000000000"
				));
				authService.setRoleForUser("admin", "ROLE_ADMIN");
			}
		};
	}
}
