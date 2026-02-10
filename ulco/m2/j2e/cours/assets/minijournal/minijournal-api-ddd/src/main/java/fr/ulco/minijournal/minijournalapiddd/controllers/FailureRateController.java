package fr.ulco.minijournal.minijournalapiddd.controllers;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.in.FailureUpdateRequestDTO;
import fr.ulco.minijournal.minijournalapiddd.domain.services.failure.FailureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/failure-rate")
public class FailureRateController {
    
    private final FailureService failureService;

    @PostMapping("")
    public void updateFailureRate(@RequestBody FailureUpdateRequestDTO request) {
        failureService.updateFailureRate(request.rate());
    }
}
