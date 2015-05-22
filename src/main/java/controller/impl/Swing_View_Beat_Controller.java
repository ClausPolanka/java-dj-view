package controller.impl;

import controller.api.Controller;
import model.api.Beat;
import view.impl.DJ_Swing_View;

public class Swing_View_Beat_Controller implements Controller {

    Beat model;
    DJ_Swing_View view;

    public Swing_View_Beat_Controller(Beat model) {
        this.model = model;
        view = new DJ_Swing_View(this, model);
        view.create_view();
        view.create_controls();
        view.disable_stop_menu_item();
        view.enable_start_menu_item();
        model.initialize();
    }

    @Override
    public void set_beats_per_minute(int beats_per_minute) {
        model.set_beats_per_minute(beats_per_minute);
    }

    @Override
    public void increase_beats_per_minute() {
        int beats_per_minute = model.get_beats_per_minute();
        model.set_beats_per_minute(beats_per_minute + 1);
    }

    @Override
    public void decrease_beats_per_minute() {
        int beats_per_minute = model.get_beats_per_minute();
        model.set_beats_per_minute(beats_per_minute - 1);
    }

    @Override
    public void start() {
        model.on();
        view.disable_start_menu_item();
        view.enable_stop_menu_item();
    }

    @Override
    public void stop() {
        model.off();
        view.disable_stop_menu_item();
        view.enable_start_menu_item();
    }
}
