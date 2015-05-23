package model.impl;

import model.api.Beat_Observer;
import model.api.Beats_Per_Minute_Observer;
import model.api.Heart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Default_Heart implements Heart, Runnable {
    List<Beat_Observer> beat_observers = new ArrayList<>();
    List<Beats_Per_Minute_Observer> beats_per_minute_observers = new ArrayList<>();
    Random random = new Random(System.currentTimeMillis());
    Thread thread;
    int time = 1000;
    int bpm = 90;

    public Default_Heart() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        int last_rate = -1;

        for(;;) {
            int change = random.nextInt(10);

            if (random.nextInt(2) == 0) {
                change = 0 - change;
            }

            int rate = 60000/(time + change);

            if (rate < 120 && rate > 50) {
                time += change;
                notify_beat_observers();

                if (rate != last_rate) {
                    last_rate = rate;
                    notify_beats_per_minute_observers();
                }
            }
            try {
                Thread.sleep(time);
            } catch (Exception e) {}
        }
    }
    public void notify_beat_observers() {
        beat_observers.stream().forEach(o -> o.update_beat());
    }

    public void notify_beats_per_minute_observers() {
        beats_per_minute_observers.stream().forEach(o -> o.update_beats_per_minute());
    }

    @Override
    public int get_heart_rate() {
        return 60000/time;
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
