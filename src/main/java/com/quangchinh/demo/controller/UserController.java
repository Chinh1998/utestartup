package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.Position;
import com.quangchinh.demo.dto.JwtResponse;
import com.quangchinh.demo.dto.UserDTO;
import com.quangchinh.demo.helper.AuthenticationHelper;
import com.quangchinh.demo.model.LoginForm;
import com.quangchinh.demo.service.PositionService;
import com.quangchinh.demo.service.UserService;
import com.quangchinh.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.quangchinh.demo.dao.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationHelper authenticationHelper;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PositionService positionService;

    @Autowired
    UserController(UserService userService, AuthenticationManager authenticationManager,
                   AuthenticationHelper authenticationHelper, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, PositionService positionService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.authenticationHelper = authenticationHelper;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.positionService = positionService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public User createUser(@RequestBody UserDTO userDTO) {
        Optional<Position> position = positionService.getById(userDTO.getPositionId());
        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPosition(position.get());
        return userService.create(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginForm loginForm) throws Exception {

        authenticate(loginForm.getUsername(), loginForm.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginForm.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final String username = loginForm.getUsername();
        User user = userService.getByUsername(username);
        return ResponseEntity.ok(new JwtResponse(token, user));
    }

    @GetMapping("/islogin")
    public boolean getUserLogin(){
        User user = authenticationHelper.getLoggedInUser();
        if(user==null){
            return false;
        }
        return true;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
