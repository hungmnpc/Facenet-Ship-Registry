package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.ShipDTO;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface GeneralParticularsService {

    /**
     *
     * @param requestBody
     * @return
     */
    public ShipDTO saveNewShip(ShipInfoRequestBody requestBody);
}
