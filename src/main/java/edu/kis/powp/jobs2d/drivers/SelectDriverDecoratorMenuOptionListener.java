package edu.kis.powp.jobs2d.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectDriverDecoratorMenuOptionListener  implements ActionListener {

    private DriverManager driverManager;
    private DriverDecoratorApplicator driverDecoratorApplicator;
    private DriverDecorator decorator;

    public SelectDriverDecoratorMenuOptionListener(DriverManager driverManager, DriverDecoratorApplicator driverDecoratorApplicator) {
        this.driverManager = driverManager;
        this.driverDecoratorApplicator = driverDecoratorApplicator;
    }

    @Override public void actionPerformed(ActionEvent e) {
        driverDecoratorApplicator.changeStateToDecorate();

        System.out.println(driverDecoratorApplicator.getDecoratorClass().getSimpleName() + ": " + driverDecoratorApplicator.isStateToDecorate());

        driverManager.notifyObservers();
    }
}
