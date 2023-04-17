package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.MeasurementTM1;
import com.facenet.shipsregistry.entity.ReportIndex;
import jakarta.persistence.*;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public class FormTM1DTO implements FormDTO{

    private Long id;

    private String description;

    private String name;

    private String strakePosition;

    private List<MeasurementTM1DTO> measurementTM1DTOList;
}