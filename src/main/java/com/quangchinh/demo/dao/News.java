package com.quangchinh.demo.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "news_Table")
@Indexed
public class News {

    @Id
    @GeneratedValue(generator ="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Field
    private String title;
    private String image;
    @Field
    private String content;
    private int view;
    private boolean approved;
    Date createDate;
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name ="majors_id")
    private Majors majors;
}
