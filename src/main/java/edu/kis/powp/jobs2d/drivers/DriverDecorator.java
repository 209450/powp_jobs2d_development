package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public abstract class DriverDecorator implements Job2dDriver {

    private Job2dDriver driverToDecoration;

    public DriverDecorator() {
    }

    public void setDriverToDecoration(Job2dDriver driverToDecoration) {
        this.driverToDecoration = driverToDecoration;
    }

    @Override public void setPosition(int i, int i1) {
        driverToDecoration.setPosition(i, i1);
    }

    @Override public void operateTo(int i, int i1) {
        driverToDecoration.operateTo(i, i1);
    }

    public abstract Job2dDriver decorateDriver(Job2dDriver job2dDriver);
}
