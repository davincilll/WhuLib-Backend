package com.dfcold.whulibbackend.domain.dto.req;

import lombok.Data;

@Data
public class AuthReq {
    private String username;
    private String password;
}
