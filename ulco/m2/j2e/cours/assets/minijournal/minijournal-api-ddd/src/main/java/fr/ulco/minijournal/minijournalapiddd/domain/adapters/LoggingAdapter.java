package fr.ulco.minijournal.minijournalapiddd.domain.adapters;

public interface LoggingAdapter {
    void info(String message);

    void error(String message);

    void error(String message, Throwable throwable);
}
