package ru.digitalliague.questionsserver.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalliague.questionsserver.modelDTO.ProfileDto;
import ru.digitalliague.questionsserver.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private ProfileService profileService;


    public ResponseEntity<List<ProfileDto>> getAllProfile(){
        List<ProfileDto> allProfile = profileService.getAllProfile();
        return ResponseEntity.ok().body(allProfile);
    }
}
