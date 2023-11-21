package com.diplomado.demo.dtos.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRolActiveRequest {
    private Long rolId;
    private Boolean active;
}
