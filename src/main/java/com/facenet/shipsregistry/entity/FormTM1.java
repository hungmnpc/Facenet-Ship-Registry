package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FormTM1RequestBody;
import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */
@Entity
@Table(name = "form_TM1")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormTM1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "strake_position")
    private String strakePosition;

    @Column(name = "form_index")
    private Integer formIndex;

    @OneToMany(mappedBy = "formTM1", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<MeasurementTM1> measurementTM1List;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    @Override
    public String toString() {
        return "FormTM1{" +
                "id=" + id +
                ", strakePosition='" + strakePosition + '\'' +
                ", reportIndex=" + reportIndex +
                '}';
    }

    public void update(FormTM1RequestBody requestBody) {
        this.setStrakePosition(requestBody.getStrakePosition());
        this.setCode(requestBody.getCode());
    }
}
