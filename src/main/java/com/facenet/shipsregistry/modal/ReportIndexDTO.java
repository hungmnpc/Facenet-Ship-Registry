package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.FormTM1;
import com.facenet.shipsregistry.entity.GeneralParticulars;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportIndexDTO {

    private Long id;

    private Integer partIndex;

    private String item;

    private String reportNo;

    private List<FormDTO> formList;
}