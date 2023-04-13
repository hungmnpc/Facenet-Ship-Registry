package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.GeneralParticulars;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public class CertificateDTO {

    private Long id;

    private String certificateOrganization;

    private String certificateNo;

    private Date validStartDate;

    private Date validEndDate;
}