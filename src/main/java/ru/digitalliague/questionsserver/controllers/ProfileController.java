package ru.digitalliague.questionsserver.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalliague.questionsserver.modelDTO.ProfileDto;
import ru.digitalliague.questionsserver.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private ProfileService profileService;


    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileDto>> getAllProfile(){
        List<ProfileDto> allProfile = profileService.getAllProfile();
        return ResponseEntity.ok().body(allProfile);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProfile(@RequestBody ProfileDto profileDto){
        profileService.addProfile(profileDto);
        return ResponseEntity.ok().body("Added Profile successfully");
    }





}
