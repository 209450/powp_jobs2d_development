package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverDecorator;
import edu.kis.powp.jobs2d.file.DataFile;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.io.FileNotFoundException;

public class LineDriverAdapterUseControl extends DriverDecorator {

    private double distance;
    private int prevX, prevY;

    public LineDriverAdapterUseControl() {
    }

//    public LineDriverAdapterUseControl(Job2dDriver job2dDriver) throws FileNotFoundException {
//        super(job2dDriver);
//        DataFile dataFile = new DataFile(this);
//        distance = dataFile.getCurrentLevel();
//        prevX = 0;
//        prevY = 0;
//    }

    private void calculateDistance(int nextX, int nextY){

       double currentDistance = sqrt((pow(nextX - this.prevX,2) + pow(nextY - this.prevY,2)));
       this.distance += currentDistance;
       
       DataFile dataFile = new DataFile(this);
       dataFile.saveData();
}

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x,y);
        this.prevX = x;
        this.prevY = y;

    }

    @Override
    public void operateTo(int x, int y) {
        calculateDistance(x,y);
        System.out.println(distance);
        super.operateTo(x,y);
        this.prevY = y;
        this.prevX = x;
    }

    @Override public Job2dDriver decorateDriver(Job2dDriver job2dDriver) {
        try {
            super.setDriverToDecoration(job2dDriver);
            DataFile dataFile = new DataFile(this);
            distance = dataFile.getCurrentLevel();
            prevX = 0;
            prevY = 0;
        } catch (FileNotFoundException e) {

        } return job2dDriver;
    }

}
