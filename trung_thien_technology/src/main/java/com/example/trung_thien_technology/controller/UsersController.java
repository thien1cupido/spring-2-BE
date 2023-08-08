package com.example.trung_thien_technology.controller;


import com.example.trung_thien_technology.config.JwtTokenUtil;
import com.example.trung_thien_technology.config.JwtUserDetails;
import com.example.trung_thien_technology.model.Users;
import com.example.trung_thien_technology.request_and_response.JwtRequest;
import com.example.trung_thien_technology.request_and_response.JwtResponse;
import com.example.trung_thien_technology.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UsersController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UsersService usersService;

    class ErrorInfo {
        private String error;
        private Long id;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> loginAuthentication(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            JwtUserDetails principal = (JwtUserDetails) authentication.getPrincipal();
            GrantedAuthority authority = principal.getAuthorities().stream().findFirst().orElse(null);
            final String token = jwtTokenUtil.generateToken(principal.getUsername());

            return ResponseEntity.ok(new JwtResponse(token, principal.getUsername(), authority != null ? authority.getAuthority() : null));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đăng nhập thất bại");
        }
    }


    @PostMapping("/checkCode")
    public ResponseEntity<?> checkCode(@RequestBody Users user) {
        Users users = usersService.findById(user.getId());
        if (users.getVerificationCode().toString().equals(user.getVerificationCode().toString())) {
            return ResponseEntity.ok(users.getId());
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xác nhận mã thất bại!!");
        }
    }

}