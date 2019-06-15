package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;

public class DriverDecoratorApplicator {

    private ArrayList<DecoratorWithStateToDecorate> listOfDecorators = new ArrayList<>();

    public DriverDecoratorApplicator() {
    }

    public void addDecoratorWithStateToDecorate(DriverDecorator driverDecorator, Boolean stateToDecorate){
        listOfDecorators.add(new DecoratorWithStateToDecorate(driverDecorator,stateToDecorate));

    }

    public Class getDecoratorClass() {
        return decoratorClass;
    }

    public void changeStateToDecorate(DriverDecorator driverDecorator){
        listOfDecorators.stream().filter((i)->i.driverDecorator.equals(driverDecorator)).findAny();
    }

    public boolean isStateToDecorate() {
        return stateToDecorate;
    }

    public Job2dDriver applicateDecoration(Job2dDriver job2dDriver){
       listOfDecorators.forEach(decorator->{
           if(decorator.stateToDecorate) decorator.driverDecorator.decorateDriver(job2dDriver);
       });

       return  job2dDriver;
    }

    private Job2dDriver decorate(Job2dDriver job2dDriver){
        beforeDecoration = job2dDriver;

        try {
            job2dDriver = (Job2dDriver)  decoratorClass.getConstructor(Job2dDriver.class).newInstance(job2dDriver);
        } catch (Exception e){
            return job2dDriver;
        }

        return job2dDriver;
    }

    private Job2dDriver undoDecorate(Job2dDriver job2dDriver){
        if(beforeDecoration != null) return beforeDecoration;
        else return job2dDriver;
    }

    private class DecoratorWithStateToDecorate{
        DriverDecorator driverDecorator;
        boolean stateToDecorate;

        DecoratorWithStateToDecorate(DriverDecorator driverDecorator, boolean stateToDecorate) {
            this.driverDecorator = driverDecorator;
            this.stateToDecorate = stateToDecorate;
        }
    }
}
