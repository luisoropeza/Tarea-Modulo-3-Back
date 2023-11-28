package com.diplomado.demo.dtos.Request;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserWithRolesRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate birthDay;
    private List<Long> rolesId;
}
