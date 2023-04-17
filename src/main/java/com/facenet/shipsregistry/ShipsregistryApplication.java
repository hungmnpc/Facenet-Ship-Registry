package com.facenet.shipsregistry;

import com.facenet.shipsregistry.request.CertificateRequestBody;
import com.facenet.shipsregistry.request.GeneralParticularRequestBody;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;

import java.text.ParseException;
import java.time.LocalDate;

@SpringBootApplication
@Slf4j
public class ShipsregistryApplication {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(ShipsregistryApplication.class, args);
	}

	@Bean
	CommandLineRunner run(GeneralParticularsService generalParticularsService) {
		return args -> {
			generalParticularsService.saveNewCertificate(
					new CertificateRequestBody("AMERICAN BUREAU OF SHIPPING",
							"22-5280779-A",
							LocalDate.parse("2004-01-01"),
							LocalDate.parse("2005-01-01")));

			generalParticularsService.saveNewGeneralParticulars(new GeneralParticularRequestBody(new ShipInfoRequestBody(
					"M/T \"TM HAI HA 568\"",
					"9274082",
					"04131940",
					"HAI PHONG",
					25400,
					40058,
					LocalDate.parse("2015-01-01")
			),
					"VMC.UTM.22.093/5400433",
					"MANOP SRISUNGVAL",
					"22-5280779-A",
					"DM5E DA-501(Serri DM5EG2210350); OLYMPUS 27MG (Serri 150239211)"
			) );
			try {
				generalParticularsService.saveNewGeneralParticulars(new GeneralParticularRequestBody(new ShipInfoRequestBody(
						"M/T \"TM HAI HA 569\"",
						"9274083",
						"041319421",
						"HAI PHONG",
						25400,
						40058,
						LocalDate.parse("2015-01-01")
				),
						"VMC.UTM.22.093/5400434",
						"MANOP SRISUNGVAL",
						"22-5280779-A",
						"DM5E DA-501(Serri DM5EG2210350); OLYMPUS 27MG (Serri 150239211)"
				) );
			} catch (Exception exception) {
				log.debug(exception.getMessage());
			}

		};
	}
}
