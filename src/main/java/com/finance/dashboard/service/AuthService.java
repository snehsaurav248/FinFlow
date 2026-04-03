package com.finance.dashboard.service;

import com.finance.dashboard.dto.auth.LoginRequest;
import com.finance.dashboard.dto.auth.RegisterRequest;
import com.finance.dashboard.dto.auth.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}