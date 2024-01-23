package org.codeneur.plannerpal.globals;

import org.codeneur.plannerpal.models.Project;

import java.util.ArrayList;
import java.util.List;

public class Globals {
    private static Globals instance;

    private List<Project> projects;

    private Project currentProject;

    private String projectStatus;

    private Globals() {
        projects = new ArrayList<>();
        currentProject = null;
        projectStatus = "";
    }

    public static Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(String projectName) {
        this.currentProject = projects.stream().filter(project -> project.getTitle().equals(projectName)).findFirst().orElse(null);
    }

    public void setCurrentProject(Project project) {
        this.currentProject = project;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
