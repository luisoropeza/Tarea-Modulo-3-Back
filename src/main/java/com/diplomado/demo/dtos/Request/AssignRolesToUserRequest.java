package com.diplomado.demo.dtos.Request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRolesToUserRequest {
    private List<Long> rolesId;
}
