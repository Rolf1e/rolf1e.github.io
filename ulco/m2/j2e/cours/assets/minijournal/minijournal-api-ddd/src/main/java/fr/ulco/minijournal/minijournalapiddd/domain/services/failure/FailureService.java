package fr.ulco.minijournal.minijournalapiddd.domain.services.failure;

public interface FailureService {
    boolean isFailure();

    void updateFailureRate(float failureRate);
}
