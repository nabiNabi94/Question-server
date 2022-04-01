package ru.digitalliague.questionsserver.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.digitalliague.questionsserver.payload.request.LoginRequest;
import ru.digitalliague.questionsserver.payload.request.SignupRequest;
import ru.digitalliague.questionsserver.payload.response.JWTTokenSuccessResponse;
import ru.digitalliague.questionsserver.payload.response.MassageResponse;
import ru.digitalliague.questionsserver.security.JWTTokenProvider;
import ru.digitalliague.questionsserver.security.SecurityConstants;
import ru.digitalliague.questionsserver.service.UserService;
import ru.digitalliague.questionsserver.validations.ResponseErrorValidation;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {


    private JWTTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private ResponseErrorValidation errorValidation;



    @PostMapping("/signin")
    public ResponseEntity<Object> authenticationUser(@RequestBody LoginRequest loginRequest,
                                                     BindingResult bindingResult){
        ResponseEntity<Object> errors = errorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.SECRET + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true,jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registryUser(@Valid @RequestBody SignupRequest request,
                                                      BindingResult bindingResult){

        ResponseEntity<Object> errors = errorValidation.mapValidationService(bindingResult);
        if (ObjectUtils.isEmpty(errors)) return errors;
        userService.createUser(request);

      return ResponseEntity.ok(new MassageResponse("User registered successfully"));
    }



}
