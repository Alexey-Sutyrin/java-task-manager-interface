package com.kanban.manager; // итерация 2 - не менять

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kanban.tasks.Epic;
import com.kanban.tasks.Subtask;
import com.kanban.tasks.Task;

public interface  TaskManager {

    Integer addNewTask(Task task);
    Integer addNewEpic(Epic epic);
    Integer addNewSubTask(Subtask subtask);
    void deleteEpics(Integer epicId);
    void deleteTasks(Integer taskId);
    void deleteSubtasks(Integer taskId);
    Epic getEpicById(Integer epicId);
    Task getTaskById(Integer taskId);
    Subtask getSubTaskById(Integer subtaskId);
    List<Epic> getAllEpics();
    List<Task> getAllTasks();
    List<Subtask> getAllSubTasks();

    void updateTask(Task task);
    void updateSubTask(Subtask subtask);
    void updateEpicStatus(Integer epicId);
    void printAllTasks();
    Integer generateId();
    List<Task> getHistory();
}
