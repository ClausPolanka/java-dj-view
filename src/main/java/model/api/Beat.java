package model.api;

public interface Beat {
    void initialize();
    void on();
    void off();
    void set_beats_per_minute(int beats_per_minute);
    int get_beats_per_minute();
    void register_observer(Beat_Observer o);
    void remove_observer(Beat_Observer o);
    void register_observer(Beats_Per_Minute_Observer o);
    void remove_observer(Beats_Per_Minute_Observer o);
}
