package view;

import javax.swing.*;

public class Beat_Bar extends JProgressBar implements Runnable {
    JProgressBar progressBar;
    Thread thread;

    public Beat_Bar() {
        thread = new Thread(this);
        setMaximum(100);
        thread.start();
    }

    public void run() {
        while (true) {
            int value = getValue();
            value = (int) (value * .75);
            setValue(value);
            repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }
}
