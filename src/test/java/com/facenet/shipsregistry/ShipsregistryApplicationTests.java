package com.facenet.shipsregistry;

import com.facenet.shipsregistry.entity.Certificate;
import com.facenet.shipsregistry.entity.GeneralParticulars;
import com.facenet.shipsregistry.entity.Ship;
import com.facenet.shipsregistry.modal.ShipDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ShipsregistryApplicationTests {

	@Autowired
	private ModelMapper modelMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testMapping() {
		Ship ship = new Ship(1l, "Test", "73746", "dfbdsjfs", "Hai Phong",
				20000, 40000, new Date(2022, 01, 20), null);

		Certificate certificate = new Certificate(1l, "USA", "8273dgfd", new Date(2020, 01, 01),
				new Date(2023, 01, 01), null);

		GeneralParticulars generalParticulars = new GeneralParticulars(1l, ship, "43y4u323i",
				"Dinh Quoc Hung", certificate);

		ShipDTO shipDTO =  modelMapper.map(ship, ShipDTO.class);

	}

}
