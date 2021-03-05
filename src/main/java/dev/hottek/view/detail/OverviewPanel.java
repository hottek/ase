package dev.hottek.view.detail;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OverviewPanel extends JPanel implements Observer {

    private List<Observable> observables;

    public OverviewPanel() {
        this.observables = new LinkedList<>();
    }

    public void addObservable(Observable observable) {
        observables.add(observable);
        for (Observable observable1 : observables) {
            observable1.addObserver(this);
        }
    }

    @Override
    public void update(Observable observable, Object object) {
    }
}
