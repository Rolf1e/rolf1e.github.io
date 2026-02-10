package fr.ulco.minijournal.minijournalapiddd.domain.services.failure;

import fr.ulco.minijournal.minijournalapiddd.domain.adapters.LoggingAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class DomainFailureService implements FailureService {

    private final LoggingAdapter log;

    private final Random random = new Random();

    private float failureRate = 0.01f;

    @Override
    public boolean isFailure() {
        return random.nextFloat(0, 1) < this.failureRate;
    }

    @Override
    public void updateFailureRate(float failureRate) {
        this.failureRate = failureRate;
        log.info("Failure rate updated to " + failureRate);
    }
}
