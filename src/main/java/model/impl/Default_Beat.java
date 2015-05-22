package model.impl;

import model.api.Beat;
import model.shared.Beat_Observer;
import model.shared.Beats_Per_Minute_Observer;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequencer;
import java.util.ArrayList;
import java.util.List;

public class Default_Beat implements Beat, MetaEventListener {

    public static final int DEFAULT_BEATS_PER_MINUTE = 90;
    
    Sequencer sequencer;
    List<Beat_Observer> beat_observers = new ArrayList<>();
    List<Beats_Per_Minute_Observer> beats_per_minute_observers = new ArrayList<>();
    int beats_per_minute = DEFAULT_BEATS_PER_MINUTE;

    @Override
    public void initialize() {
        set_up_midi();
        build_track_and_start();
    }

    private void set_up_midi() {
    }

    private void build_track_and_start() {
    }

    @Override
    public void on() {
        sequencer.start();
        set_beats_per_minute(DEFAULT_BEATS_PER_MINUTE);
    }

    @Override
    public void off() {
        set_beats_per_minute(0);
        sequencer.stop();
    }

    @Override
    public void set_beats_per_minute(int beats_per_minute) {
        this.beats_per_minute = beats_per_minute;
        sequencer.setTempoInBPM(get_beats_per_minute());
        notify_beats_per_minute_observers();
    }

    private void notify_beats_per_minute_observers() {
//        beats_per_minute_observers.stream().forEach(o -> o.);
    }

    @Override
    public int get_beats_per_minute() {
        return beats_per_minute;
    }

    public void beat_event() {
        notify_beat_observers();
    }

    private void notify_beat_observers() {
//        beat_observers.stream().forEach(o -> o.);
    }

    @Override
    public void register_observer(Beat_Observer o) {
        beat_observers.add(o);
    }

    @Override
    public void remove_observer(Beat_Observer o) {
        beat_observers.remove(o);
    }

    @Override
    public void register_observer(Beats_Per_Minute_Observer o) {
        beats_per_minute_observers.add(o);
    }

    @Override
    public void remove_observer(Beats_Per_Minute_Observer o) {
        beats_per_minute_observers.remove(o);
    }

    @Override
    public void meta(MetaMessage meta) {

    }
}
