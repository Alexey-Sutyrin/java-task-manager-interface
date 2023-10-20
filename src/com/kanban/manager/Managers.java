package com.kanban.manager; // ЗЕЛЁНОЕ !!!  итерация 2 - не менять

public class Managers {
    public static TaskManager getDefault() { // тот самый метод getDefault из ТЗ - использовать в Main.java

        return new InMemoryTaskManager();
    }
    public static HistoryManager getDefaultHistory() { //  использовать в InMemoryTaskManager.java
        return new InMemoryHistoryManager();
    }
}
