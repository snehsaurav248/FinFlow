package com.finance.dashboard.dto.user;

import com.finance.dashboard.enums.RoleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private RoleType role;
    private Boolean isActive;
}