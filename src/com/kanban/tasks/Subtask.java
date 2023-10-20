package com.kanban.tasks; //не менять - 2 итерация

public class Subtask extends Task {

    protected Integer epicId;

    public Subtask(String title, String description, TaskStatus status, Integer epicId) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }
}

