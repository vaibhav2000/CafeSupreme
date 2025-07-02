package com.vab.CafeSupreme.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private String mobileNumber;
}
