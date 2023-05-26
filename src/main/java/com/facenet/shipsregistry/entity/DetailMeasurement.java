package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.DetailMeasurementRequestBody;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "detail_measurement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DetailMeasurement {

    public DetailMeasurement(Double originalThickness, Double maxAlwbDim, Double gaugedP, Double gaugedS, String percent) {
        this.originalThickness = originalThickness;
        this.maxAlwbDim = maxAlwbDim;
        this.gaugedP = gaugedP;
        this.gaugedS = gaugedS;
        this.percent = percent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_thickness", columnDefinition = "Decimal(10,2)")
    private Double originalThickness;

    @Column(name = "max_alwb_dim", columnDefinition = "Decimal(10,2)")
    private Double maxAlwbDim;

    @Column(name = "gauged_P", columnDefinition = "Decimal(10,2)")
    private Double gaugedP;

    @Column(name = "gauged_S", columnDefinition = "Decimal(10,2)")
    private Double gaugedS;

    @Column(name = "percent", nullable = false)
    private String percent;

    @OneToOne(mappedBy = "forwardReadingMeasurementDetail",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM1 measurementTM1ForwardList;

    @OneToOne(mappedBy = "afterReadingMeasurementDetail",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM1 measurementTM1AfterList;

    @OneToOne(mappedBy = "measurementDetail",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM5 measurementTM5;

    @OneToOne(mappedBy = "detailMeasurement",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM6 measurementTM6;

    @OneToOne(mappedBy = "upperPart",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM7 measurementTM7UpperPart;

    @OneToOne(mappedBy = "midPart",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM7 measurementTM7MidPart;

    @OneToOne(mappedBy = "lowerPart",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM7 measurementTM7LowerPart;

    @OneToOne(mappedBy = "firstTransverseSectionMeasurementDetailTM3",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM3 measurementTM3FirstTranList;

    @OneToOne(mappedBy = "secondTransverseSectionMeasurementDetailTM3",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM3 measurementTM3SecondTranList;

    @OneToOne(mappedBy = "thirdTransverseSectionMeasurementDetailTM3",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM3 measurementTM3ThirdTranList;

    @OneToOne(mappedBy = "detailMeasurement",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM4 measurementTM4;

    /**
     *
     * @param requestBody
     */
    public void update(DetailMeasurementRequestBody requestBody) {
        this.setOriginalThickness(requestBody.getOriginalThickness());
        this.setGaugedP(requestBody.getGaugedP());
        this.setGaugedS(requestBody.getGaugedS());
        this.setMaxAlwbDim(requestBody.getMaxAlwbDim());
        this.setPercent(requestBody.getPercent());
    }
}