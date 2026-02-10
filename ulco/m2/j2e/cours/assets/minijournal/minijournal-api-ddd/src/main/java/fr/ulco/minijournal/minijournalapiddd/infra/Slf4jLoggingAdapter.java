package fr.ulco.minijournal.minijournalapiddd.infra;

import fr.ulco.minijournal.minijournalapiddd.domain.adapters.LoggingAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jLoggingAdapter implements LoggingAdapter {

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        log.error(message, throwable);
    }
}
