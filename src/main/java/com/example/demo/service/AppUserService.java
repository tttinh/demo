package com.example.demo.service;

import com.example.demo.common.ErrorCode;
import com.example.demo.configuration.security.JwtTokenUtil;
import com.example.demo.domain.entity.AppUser;
import com.example.demo.domain.request.LoginRequest;
import com.example.demo.domain.request.RegisterRequest;
import com.example.demo.domain.response.LoginResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepo;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException(ErrorCode.EXISTED_ACCOUNT);
        }

        var user = new AppUser();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepo.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            var user = (AppUser) authenticate.getPrincipal();

            var response = new LoginResponse();
            response.setAccessToken(jwtTokenUtil.generateAccessToken(user));

            return response;
        } catch (BadCredentialsException ex) {
            throw new BadRequestException(ErrorCode.INVALID_EMAIL_PASSWORD);
        }
    }
}
