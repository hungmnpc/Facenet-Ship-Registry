package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.GeneralParticulars;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CertificateRequestBody {

    private String certificateOrganization;

    private String certificateNo;

    private LocalDate validStartDate;

    private LocalDate validEndDate;
}