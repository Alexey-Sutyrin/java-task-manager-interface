package com.kanban.tasks; //не менять - 2 итерация

public enum TaskStatus { // Возможные статусы задач - Новая/В процессе/Выполнена
    NEW("Новая"),
    IN_PROGRESS("В процессе"),
    DONE("Выполнена");

    public final String rusStatus;

    TaskStatus(String nameStatus) {
        this.rusStatus = nameStatus;
    }
    public String getName() {
        return rusStatus;
    }
}