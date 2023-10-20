package com.kanban.manager; //все данные внесены - итерация 2 - не менять

import java.util.List;

import com.kanban.tasks.Task;

public interface HistoryManager {
    void addTask(Task task);
    List<Task> getHistory();
}