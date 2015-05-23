package view.impl;

import controller.api.Controller;
import model.api.Beat;
import model.api.Beat_Observer;
import model.api.Beats_Per_Minute_Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DJ_Swing_View implements Beat_Observer, Beats_Per_Minute_Observer, ActionListener {

    Controller controller;
    Beat model;

    JFrame view_frame;
    JPanel view_panel;
    Beat_Bar beat_bar;
    JLabel beats_per_minute_output_label;

    JFrame control_frame;
    JPanel control_panel;
    JLabel beats_per_minute_label;
    JTextField beats_per_minute_text_field;
    JButton set_beats_per_minute_button;
    JButton increase_beats_per_minute_button;
    JButton decrease_beats_per_minute_button;
    JMenuBar menu_bar;
    JMenu menu;
    JMenuItem start_menu_item;
    JMenuItem stop_menu_item;

    public DJ_Swing_View(Controller controller, Beat model) {
        this.controller = controller;
        this.model = model;
        this.model.register_observer((Beat_Observer) this);
        this.model.register_observer((Beats_Per_Minute_Observer) this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == set_beats_per_minute_button)
        {
            int beats_per_minute = Integer.parseInt(beats_per_minute_text_field.getText());
            controller.set_beats_per_minute(beats_per_minute);
        }
        else if (e.getSource() == increase_beats_per_minute_button)
        {
            controller.increase_beats_per_minute();
        }
        else if (e.getSource() == decrease_beats_per_minute_button)
        {
            controller.decrease_beats_per_minute();
        }
    }
    
    @Override
    public void update_beats_per_minute() {
        if (model == null) {
            return;
        }

        int beats_per_minute = model.get_beats_per_minute();

        if (beats_per_minute == 0) {
            if (beats_per_minute_output_label != null) {
                beats_per_minute_output_label.setText("offline");
            }
        } else {
            if (beats_per_minute_output_label != null) {
                beats_per_minute_output_label.setText("Current BPM: " + model.get_beats_per_minute());
            }
        }
    }

    @Override
    public void update_beat() {
        if (beat_bar != null) {
            beat_bar.setValue(100);
        }
    }

    public void enable_stop_menu_item() {
        stop_menu_item.setEnabled(true);
    }

    public void disable_stop_menu_item() {
        stop_menu_item.setEnabled(false);
    }

    public void enable_start_menu_item() {
        start_menu_item.setEnabled(true);
    }

    public void disable_start_menu_item() {
        start_menu_item.setEnabled(false);
    }

    public void create_view() {
        view_panel = new JPanel(new GridLayout(1, 2));
        view_frame = new JFrame("View");
        view_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view_frame.setSize(new Dimension(100, 80));
        beats_per_minute_output_label = new JLabel("offline", SwingConstants.CENTER);
        beat_bar = new Beat_Bar();
        beat_bar.setValue(0);
        JPanel beats_per_minute_panel = new JPanel(new GridLayout(2, 1));
        beats_per_minute_panel.add(beat_bar);
        beats_per_minute_panel.add(beats_per_minute_output_label);
        view_panel.add(beats_per_minute_panel);
        view_frame.getContentPane().add(view_panel, BorderLayout.CENTER);
        view_frame.pack();
        view_frame.setVisible(true);
    }

    public void create_controls() {
        // Create all Swing components here
        JFrame.setDefaultLookAndFeelDecorated(true);
        control_frame = new JFrame("Control");
        control_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        control_frame.setSize(new Dimension(100, 80));

        control_panel = new JPanel(new GridLayout(1, 2));

        menu_bar = new JMenuBar();
        menu = new JMenu("DJ Control");
        start_menu_item = new JMenuItem("Start");
        menu.add(start_menu_item);
        start_menu_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.start();
            }
        });
        stop_menu_item = new JMenuItem("Stop");
        menu.add(stop_menu_item);
        stop_menu_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.stop();
            }
        });
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        menu.add(exit);
        menu_bar.add(menu);
        control_frame.setJMenuBar(menu_bar);

        beats_per_minute_text_field = new JTextField(2);
        beats_per_minute_label = new JLabel("Enter BPM:", SwingConstants.RIGHT);
        set_beats_per_minute_button = new JButton("Set");
        set_beats_per_minute_button.setSize(new Dimension(10,40));
        increase_beats_per_minute_button = new JButton(">>");
        decrease_beats_per_minute_button = new JButton("<<");
        set_beats_per_minute_button.addActionListener(this);
        increase_beats_per_minute_button.addActionListener(this);
        decrease_beats_per_minute_button.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        buttonPanel.add(decrease_beats_per_minute_button);
        buttonPanel.add(increase_beats_per_minute_button);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(beats_per_minute_label);
        enterPanel.add(beats_per_minute_text_field);
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(set_beats_per_minute_button);
        insideControlPanel.add(buttonPanel);
        control_panel.add(insideControlPanel);

        beats_per_minute_label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        beats_per_minute_output_label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        control_frame.getRootPane().setDefaultButton(set_beats_per_minute_button);
        control_frame.getContentPane().add(control_panel, BorderLayout.CENTER);

        control_frame.pack();
        control_frame.setVisible(true);
    }


}
