package controller.api;

public interface Controller {
    void set_beats_per_minute(int beats_per_minute);

    void increase_beats_per_minute();

    void decrease_beats_per_minute();

    void start();

    void stop();
}
