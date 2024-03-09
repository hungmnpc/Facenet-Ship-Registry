//package com.facenet.shipsregistry;
//
//import com.facenet.shipsregistry.entity.*;
//import com.facenet.shipsregistry.modal.*;
//import com.facenet.shipsregistry.repository.ShipRepository;
//import com.facenet.shipsregistry.request.ShipInfoRequestBody;
//import com.facenet.shipsregistry.service.GeneralParticularsService;
//import com.facenet.shipsregistry.utils.MapperUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.xmlunit.util.Mapper;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//@SpringBootTest
//@Slf4j
//class ShipsregistryApplicationTests {
//
//	@Autowired
//	private MapperUtils mapperUtils;
//
//	@Autowired
//	private GeneralParticularsService generalParticularsService;
//
//	@Autowired
//	private ShipRepository shipRepository;
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void testMapping() {
////		Ship ship = new Ship(1l, "Test", "73746", "dfbdsjfs", "Hai Phong",
////				20000, 40000, new Date(2022, 01, 20), null);
////		ShipInfoRequestBody requestBody = new ShipInfoRequestBody("dfmsa", "dfndf", "dfndfn",
////				"nam dinh", 20000, 30000, new Date(2022, 01, 01));
////		ShipDTO shipDTO = generalParticularsService.saveNewShip(requestBody);
////
////		Ship shipSaved = shipRepository.findById(shipDTO.getShip_id()).orElse(null);
////
////		assert Objects.requireNonNull(shipSaved).getShip_id() != null;
//
////		List<Ship> shipList = shipRepository.search("", "");
////		System.out.println(shipList);
////		assert shipList.size() > 0;
//
//	}
//
//}
