package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.MeasurementTM1RequestBody;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "measurement_TM1")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_position")
    private String platePosition;

    @Column(name = "no_or_letter")
    private String noOrLetter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "form_TM1_id")
    private FormTM1 formTM1;

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "forward_reading_measurement_detail_id")
    private DetailMeasurement forwardReadingMeasurementDetail;


    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "after_reading_measurement_detail_id")
    private DetailMeasurement afterReadingMeasurementDetail;

    /**
     *
     * @param requestBody
     */
    public void update(MeasurementTM1RequestBody requestBody) {
        this.setPlatePosition(requestBody.getPlatePosition());
        this.setNoOrLetter(requestBody.getNoOrLetter());
    }
}
