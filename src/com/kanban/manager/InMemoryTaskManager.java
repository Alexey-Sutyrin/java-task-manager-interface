package com.kanban.manager; // + final + примитив int ??? historyManager.add ???

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.kanban.tasks.Epic;
import com.kanban.tasks.Subtask;
import com.kanban.tasks.Task;
import com.kanban.tasks.TaskStatus;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Epic> epicsMap = new HashMap<>(); // Добавил неизменяемость final, согласно замечанию
    private final HashMap<Integer, Task> tasksMap = new HashMap<>(); // Добавил неизменяемость final, согласно замечанию
    private final HashMap<Integer, Subtask> subTasksMap = new HashMap<>(); // Добавил неизменяемость final, согласно замечанию
    private final HistoryManager historyManager = Managers.getDefaultHistory(); // Добавил неизменяемость final, согласно замечанию
    private int taskId = 0;// Сменил Integer на примитив int
    // Счетчик-идентификатор начинаем с 0. Номер по +1 каждой новой задаче
    public Integer generateId() {
        return taskId++;
    }
    @Override
    public Integer addNewTask(Task task) {
        task.setId(generateId());
        tasksMap.put(task.getId(), task);
        return task.getId();
    }
    @Override
    public Integer addNewEpic(Epic epic) {
        epic.setId(generateId());
        epicsMap.put(epic.getId(), epic);
        return epic.getId();
    }
    @Override
    public Integer addNewSubTask(Subtask subtask) {  //Добавляем подзадачу в нужный Epic и в subTaskMap
        Epic epic = epicsMap.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }
        subtask.setId(generateId());
        subTasksMap.put(subtask.getId(), subtask);
        epic.addSubtaskId(subtask.getId());
        updateEpicStatus(subtask.getEpicId());
        return subtask.getId();
    }

    // УДАЛЕНИЕ - Эпик, Таск, Субтаск
    @Override
    public void deleteEpics(Integer epicId) { //Удаляем Epic и все его подзадачи следом за ним
        Epic epic = getEpicById(epicId);
        for (Task epicTask : epic.getTasks()) {
            deleteSubtasks(epicTask.getId());
        }
        epicsMap.remove(epicId);
    }
    @Override
    public void deleteTasks(Integer taskId) { // Удаляем простой Task
        tasksMap.remove(taskId);
    }
    @Override
    public void deleteSubtasks(Integer taskId) { //Удаляем Subtask с удалением его связи с Epic
        Subtask subtask = getSubTaskById(taskId);
        Epic epic = getEpicById(subtask.getEpicId());
        int subTaskId = 0; // Аналогично строке 18 сменил Integer на примитив int
        for (Task epicTask : epic.getTasks()) {
            if (epicTask.getId().equals(subtask.getId())){
                epic.removeSubtask(subTaskId);
                break;
            }
            subTaskId++;
        }
        subTasksMap.remove(taskId);
    }
    //Гетеры - ЭПИК, ТАСК, СУБТАСК

    @Override
    public Task getTaskById(Integer taskId) {
        Task task1 = tasksMap.get(taskId);
        if (task1 == null) {
            return null;
        }
        historyManager.addTask(task1); //historyManager.add - добавлен!
        return task1;
        //return new Task(task1); //это уже будет копия объекта task1
    }
    @Override
    public Epic getEpicById(Integer epicId) {
        Epic epic1 = epicsMap.get(epicId);
        if (epic1 == null) {
            return null;
        }
        historyManager.addTask(epic1); // аналогично historyManager.add - добавлен!
        return epic1;
    }
    @Override
    public Subtask getSubTaskById(Integer subtaskId) {
        Subtask subtask1 = subTasksMap.get(subtaskId);
        if (subtask1 == null) {
            return null;
        }
        historyManager.addTask(subtask1); // аналогично historyManager.add - добавлен!
        return subtask1;
    }
    // Листы значений - MAP - ПОКА НЕ НУЖНЫ
    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epicsMap.values());
    }
    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasksMap.values());
    }
    @Override
    public List<Subtask> getAllSubTasks() {
        return new ArrayList<>(subTasksMap.values());
    }
    // ПОЛНОСТЬЮ НОВЫЕ МЕТОДЫ UPDATE
    public void updateTask(Task task) {
        Task savedTask = tasksMap.get(task.getId());
        if (savedTask == null) {
            return;
        }
        tasksMap.put(task.getId(), task);
    }
    public void updateSubTask(Subtask updatedSubTask) {
        subTasksMap.put(updatedSubTask.getId(), updatedSubTask);
        //Проверим статус Epic вслед за изменением Subtask
        Epic epic = getEpicById(updatedSubTask.getEpicId());
        epic.setStatus();
    }
    public void updateEpicStatus(Integer epicId) {
        Epic epic = epicsMap.get(epicId);
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
        if (subtaskIds.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }
        String status = "null";
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = subTasksMap.get(subtaskId);
            //если первый проход - сохраняем статус первой подзадачи
            if (status == null) {
                status = subtask.getStatus().getName();
                continue;
            }
            if (status.equals(subtask.getStatus()) && (!status.equals("IN_PROGRESS"))) {
                continue;
            }
            epic.setStatus(TaskStatus.IN_PROGRESS);
            return;
        }
        epic.setStatus();
    }
    @Override
    public void printAllTasks() { //множественный вызов на экран из Main переделал в метод
        System.out.println("Все задачи:");
        for (Epic epic : getAllEpics()) {
            epic.getStatus();
            System.out.println(epic.getId() + "  " + epic.getTitle() + " - " + epic.getStatus());
            for (Task epicTask : epic.getTasks()) {
                System.out.println(epicTask.getId() + "  " + epicTask.getTitle() + " - " + epicTask.getStatus());
            }
        }
        for (Task task : getAllTasks()) {
            System.out.println(task.getId() + "  " + task.getTitle() + " - " + task.getStatus());
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory(); }
}
