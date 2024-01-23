package org.codeneur.plannerpal.models;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String title;
    private String projectManagerName;
    private String description;
    private List<String> teamMembers;
    private List<RiskTableItem> risks;
    private List<String> functionalities;
    private List<String> nonFunctionalities;

    private LifeCycleHours[] lifeCycleHours;

    public Project(){
        this.title = "";
        this.projectManagerName = "";
        this.description = "";
        this.teamMembers = new ArrayList<>();
        this.risks = new ArrayList<>();
        this.functionalities = new ArrayList<>();
        this.nonFunctionalities = new ArrayList<>();
        this.lifeCycleHours = new LifeCycleHours[5];
        this.lifeCycleHours[0] = new LifeCycleHours(LifeCycle.REQUIREMENT_ANALYSIS, 0);
        this.lifeCycleHours[1] = new LifeCycleHours(LifeCycle.DESIGN, 0);
        this.lifeCycleHours[2] = new LifeCycleHours(LifeCycle.CODING, 0);
        this.lifeCycleHours[3] = new LifeCycleHours(LifeCycle.TESTING, 0);
        this.lifeCycleHours[4] = new LifeCycleHours(LifeCycle.PROJECT_MANAGEMENT, 0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<RiskTableItem> getRisks() {
        return risks;
    }

    public void setRisks(List<RiskTableItem> risks) {
        this.risks = risks;
    }

    public List<String> getFunctionalities() {
        return functionalities;
    }

    public void setFunctionalities(List<String> functionalities) {
        this.functionalities = functionalities;
    }

    public List<String> getNonFunctionalities() {
        return nonFunctionalities;
    }

    public void setNonFunctionalities(List<String> nonFunctionalities) {
        this.nonFunctionalities = nonFunctionalities;
    }

    public LifeCycleHours[] getLifeCycleHours() {
        return lifeCycleHours;
    }

    public void setLifeCycleHours(LifeCycleHours[] lifeCycleHours) {
        this.lifeCycleHours = lifeCycleHours;
    }
}
