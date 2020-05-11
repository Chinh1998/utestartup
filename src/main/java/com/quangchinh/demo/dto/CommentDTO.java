package com.quangchinh.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    @JsonProperty("content_cmt")
    private String content;
    private String newsId;
}
