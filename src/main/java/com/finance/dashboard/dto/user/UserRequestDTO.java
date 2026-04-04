package com.finance.dashboard.dto.user;

import com.finance.dashboard.enums.RoleType;
import lombok.Data;

@Data
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private RoleType role;  
}