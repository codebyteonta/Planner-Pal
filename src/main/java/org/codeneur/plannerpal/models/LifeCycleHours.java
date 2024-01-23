package org.codeneur.plannerpal.models;

public class LifeCycleHours {
    private LifeCycle lifeCycle;
    private int hours;

    public LifeCycleHours(LifeCycle lifeCycle, int hours){
        this.lifeCycle = lifeCycle;
        this.hours = hours;
    }

    public void addHours(int hours){
        this.hours += hours;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
