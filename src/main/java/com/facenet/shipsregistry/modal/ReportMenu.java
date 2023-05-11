package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.ReportIndex;
import lombok.*;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportMenu {

    private String reportNo;

    private List<Part> parts;

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class Part {

        Long id;

        Integer partIndex;

        String item;

        List<Form> forms;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Form {
        Long formID;

        Integer index;

        String name;
    }
}



