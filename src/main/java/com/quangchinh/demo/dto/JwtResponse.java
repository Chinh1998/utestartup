package com.quangchinh.demo.dto;

import com.quangchinh.demo.dao.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse  implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final User user;

    public JwtResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
