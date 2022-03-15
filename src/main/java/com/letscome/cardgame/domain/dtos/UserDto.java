package com.letscome.cardgame.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    @JsonProperty(value = "user_name")
    private String username;
    private String password;
    private String email;
}