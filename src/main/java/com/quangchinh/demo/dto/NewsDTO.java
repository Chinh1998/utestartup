package com.quangchinh.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class NewsDTO {
    private String title;
    private String image;
    private String content;
    private int view;
    private boolean approved;
    private Date dateTime;
    @JsonProperty("majors_id")
    private String majorsId;
}
