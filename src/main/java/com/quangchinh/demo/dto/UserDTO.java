package com.quangchinh.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String password;
    private int phone;
    private String email;
    private String positionId;
}
