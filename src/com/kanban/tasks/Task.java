package com.kanban.tasks; //не менять - 2 итерация

public class Task {
    protected Integer id;
    protected String title;
    protected String description;
    protected TaskStatus status;

    public Task(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    //  чтобы создать новую копию объекта Task на основе существующего
    //  воспользуемся конструктором копирования, добавив такой конструктор в класс Task
    public Task(Task otherTask) {
        this.title = otherTask.getTitle();
        this.description = otherTask.getDescription();
        this.status = otherTask.getStatus();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }
}

