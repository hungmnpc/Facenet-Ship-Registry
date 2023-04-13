package com.facenet.shipsregistry.utils;

import com.facenet.shipsregistry.entity.Ship;
import com.facenet.shipsregistry.modal.ShipDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@Component
public class MapperUtils {

    @Autowired
    private ModelMapper modelMapper;
    public ShipDTO shipMapper(Ship ship) {
        return modelMapper.map(ship, ShipDTO.class);
    }

}