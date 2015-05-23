package model.api;

public interface Heart {
    int get_heart_rate();

    void register_observer(Beat_Observer o);

    void remove_observer(Beat_Observer o);

    void register_observer(Beats_Per_Minute_Observer o);

    void remove_observer(Beats_Per_Minute_Observer o);
}
