package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.Ship;
import com.facenet.shipsregistry.modal.ShipDTO;
import com.facenet.shipsregistry.repository.ShipRepository;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Service
@Transactional
public class GeneralParticularsServiceImpl implements GeneralParticularsService{

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param requestBody
     * @return
     */
    @Override
    public ShipDTO saveNewShip(ShipInfoRequestBody requestBody) {
        Ship ship = new Ship(null, requestBody.getName(), requestBody.getImoNumber(),
                requestBody.getAbsIdentification(), requestBody.getPostOfRegistry(),
                requestBody.getGrossTons(), requestBody.getDeadweight(),
                requestBody.getDateOfBuild());

        Ship shipSave = shipRepository.save(ship);
        if (shipSave.getShip_id() > 0) {
            return modelMapper.map(shipSave, ShipDTO.class);
        }
        return null;
    }
}