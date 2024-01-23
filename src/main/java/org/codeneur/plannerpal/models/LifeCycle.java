package org.codeneur.plannerpal.models;

import java.util.List;

public enum LifeCycle {
    REQUIREMENT_ANALYSIS("Requirement Analysis"),
    DESIGN("Design"),
    CODING("Coding"),
    TESTING("Testing"),
    PROJECT_MANAGEMENT("Project Management");


    private final String name;

    LifeCycle(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static List<String> getLifeCycleNames(){
        return List.of(
                REQUIREMENT_ANALYSIS.getName(),
                DESIGN.getName(),
                CODING.getName(),
                TESTING.getName(),
                PROJECT_MANAGEMENT.getName()
        );
    }

}
