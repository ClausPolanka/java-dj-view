package model.impl;

import model.api.Beat;
import model.api.Beat_Observer;
import model.api.Beats_Per_Minute_Observer;
import model.api.Heart;

public class HeartAdapter implements Beat {

    Heart heart;

    public HeartAdapter(Heart heart) {
        this.heart = heart;
    }

    @Override
    public int get_beats_per_minute() {
        return heart.get_heart_rate();
    }

    @Override
    public void register_observer(Beat_Observer o) {
        heart.register_observer(o);
    }

    @Override
    public void remove_observer(Beat_Observer o) {
        heart.remove_observer(o);
    }

    @Override
    public void register_observer(Beats_Per_Minute_Observer o) {
        heart.register_observer(o);
    }

    @Override
    public void remove_observer(Beats_Per_Minute_Observer o) {
        heart.remove_observer(o);
    }

    @Override
    public void initialize() {
        // no op
    }

    @Override
    public void on() {
        // no op
    }

    @Override
    public void off() {
        // no op
    }

    @Override
    public void set_beats_per_minute(int beats_per_minute) {
        // no op
    }
}
