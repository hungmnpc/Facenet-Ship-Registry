package com.facenet.shipsregistry;

import com.facenet.shipsregistry.entity.ParamType;
import com.facenet.shipsregistry.entity.ParamValue;
import com.facenet.shipsregistry.modal.ParamValueDTO;
import com.facenet.shipsregistry.request.CertificateRequestBody;
import com.facenet.shipsregistry.request.GeneralParticularRequestBody;
import com.facenet.shipsregistry.request.ParamValueRequestBody;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import com.facenet.shipsregistry.service.ParamValueService;
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
	CommandLineRunner run(GeneralParticularsService generalParticularsService, ParamValueService paramValueService) {
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
					LocalDate.parse("2015-01-01"),
					"AMERICAN BUREAU OF SHIPPING"
			),
					"VMC.UTM.22.093/5400433",
					"MANOP SRISUNGVAL",
					"22-5280779-A",
					"DM5E DA-501(Serri DM5EG2210350); OLYMPUS 27MG (Serri 150239211)",
					"SPECIAL PERIODICAL SURVEY - HULL 3",
					"NOSCO SHIPYARD - QUANG NINH - VIET NAM",
					LocalDate.parse("2022-08-07"),
					LocalDate.parse("2022-08-31"),
					"NGUYEN VAN LUYEN"
			) );
			try {
				generalParticularsService.saveNewGeneralParticulars(new GeneralParticularRequestBody(new ShipInfoRequestBody(
						"M/T \"TM HAI HA 569\"",
						"9274083",
						"041319421",
						"HAI PHONG",
						25400,
						40058,
						LocalDate.parse("2015-01-01"),
						"AMERICAN BUREAU OF SHIPPING"
				),
						"VMC.UTM.22.093/5400434",
						"MANOP SRISUNGVAL",
						"22-5280779-A",
						"DM5E DA-501(Serri DM5EG2210350); OLYMPUS 27MG (Serri 150239211)",
						"SPECIAL PERIODICAL SURVEY - HULL 3",
						"NOSCO SHIPYARD - QUANG NINH - VIET NAM",
						LocalDate.parse("2022-08-07"),
						LocalDate.parse("2022-08-31"),
						"NGUYEN VAN LUYEN"
				) );
			} catch (Exception exception) {
				log.error(exception.getMessage());
			}

			ParamValueDTO paramValueDTO = paramValueService.saveNewParamValue(
					new ParamValueRequestBody("DM5E DA-501", "DM5EG2210350", 3)
			);
			ParamValueDTO paramValueDTO2 = paramValueService.saveNewParamValue(
					new ParamValueRequestBody("OLYMPUS 27MG", "150239211",3)
			);
			ParamValueDTO paramValueDTO3 = paramValueService.saveNewParamValue(
					new ParamValueRequestBody("Surveyor", "MANOP SRISUNGVAL",2)
			);
		};
	}
}
