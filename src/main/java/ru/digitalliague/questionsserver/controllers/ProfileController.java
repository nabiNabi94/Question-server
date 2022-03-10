package ru.digitalliague.questionsserver.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalliague.questionsserver.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileService profileService;


}
