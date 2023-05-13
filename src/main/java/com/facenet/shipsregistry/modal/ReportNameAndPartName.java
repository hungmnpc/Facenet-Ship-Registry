package com.facenet.shipsregistry.modal;

import lombok.*;

import java.util.Objects;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportNameAndPartName {

    private String reportNo;

    private String reportItem;

    public String createDir() {
        return  String.format("/%s/%s/", reportNo, reportItem);
    }

    public void init(Object[] value) {
        setReportNo(String.valueOf(value[0]).replace("/", "_"));
        setReportItem(String.valueOf(value[1]));
    }
}