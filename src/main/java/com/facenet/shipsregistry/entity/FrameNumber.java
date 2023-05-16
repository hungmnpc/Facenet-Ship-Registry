package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FrameNumberRequestBody;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FRAME_NUMBER")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FrameNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_formTM7")
    private FormTM7 formTM7;

    @OneToMany(mappedBy = "frameNumber", cascade = {CascadeType.ALL})
    private List<MeasurementTM7> measurementTM7List;

    public FrameNumber(String name) {
        this.name = name;
    }
    public void update(FrameNumberRequestBody requestBody) {
        this.setName(requestBody.getName());
    }
}
