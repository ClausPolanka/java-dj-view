package controller.impl;

import controller.api.Controller;
import model.api.Heart;
import model.impl.HeartAdapter;
import view.impl.DJ_Swing_View;

public class Swing_View_Heart_Controller implements Controller {

    Heart heart;
    DJ_Swing_View view;

    public Swing_View_Heart_Controller(Heart heart) {
        this.heart = heart;
        view = new DJ_Swing_View(this, new HeartAdapter(heart));
        view.create_view();
        view.create_controls();
        view.disable_start_menu_item();
        view.disable_stop_menu_item();
    }

    @Override
    public void set_beats_per_minute(int beats_per_minute) {

    }

    @Override
    public void increase_beats_per_minute() {

    }

    @Override
    public void decrease_beats_per_minute() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
