package model.impl;

import model.api.Beat;
import model.api.Beat_Observer;
import model.api.Beats_Per_Minute_Observer;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

public class Default_Beat implements Beat, MetaEventListener {

    public static final int DEFAULT_BEATS_PER_MINUTE = 90;

    Sequencer sequencer;
    Sequence sequence;
    Track track;

    List<Beat_Observer> beat_observers = new ArrayList<>();
    List<Beats_Per_Minute_Observer> beats_per_minute_observers = new ArrayList<>();
    int beats_per_minute = DEFAULT_BEATS_PER_MINUTE;

    @Override
    public void initialize() {
        set_up_midi();
        build_track_and_start();
    }

    private void set_up_midi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener(this);
            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(get_beats_per_minute());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void build_track_and_start() {
        int[] trackList = {35, 0, 46, 0};

        sequence.deleteTrack(null);
        track = sequence.createTrack();

        make_tracks(trackList);
        track.add(make_event(192, 9, 1, 0, 4));
        try {
            sequencer.setSequence(sequence);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void make_tracks(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int key = list[i];

            if (key != 0) {
                track.add(make_event(144, 9, key, 100, i));
                track.add(make_event(128, 9, key, 100, i + 1));
            }
        }
    }

    public  MidiEvent make_event(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return event;
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
        beats_per_minute_observers.stream().forEach(o -> o.update_beats_per_minute());
    }

    @Override
    public int get_beats_per_minute() {
        return beats_per_minute;
    }

    @Override
    public void meta(MetaMessage message) {
        if (message.getType() == 47) {
            beat_event();
            sequencer.start();
            set_beats_per_minute(get_beats_per_minute());
        }
    }

    public void beat_event() {
        notify_beat_observers();
    }

    private void notify_beat_observers() {
        beat_observers.stream().forEach(o -> o.update_beat());
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

}
