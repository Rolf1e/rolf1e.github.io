package fr.ulco.minijournal.minijournalapi.domain.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class FailureService {

    private final Random random = new Random();

    private float failureRate = 0.01f;

    public boolean isFailure() {
        return random.nextFloat(0, 1) < this.failureRate;
    }

    public void updateFailureRate(float failureRate) {
        this.failureRate = failureRate;
        log.info("Failure rate updated to {}", failureRate);
    }
}
