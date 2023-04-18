package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.FormTM7;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FrameNumberRequestBody {

    private String name;

    private FormTM7 formTM7;
}