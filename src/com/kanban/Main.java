package com.kanban; //изменения внесены - 2 строки - 2 итерация - не трогать
/* java-sprint4-hw*/
import com.kanban.manager.Managers;
import com.kanban.manager.TaskManager;
import com.kanban.tasks.Epic;
import com.kanban.tasks.Subtask;
import com.kanban.tasks.Task;
import com.kanban.tasks.TaskStatus;

import java.util.List;
public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();
        Epic epic1 = new Epic("Epic 1", "Description epic 1");
        int epic1id = taskManager.addNewEpic(epic1);
        Subtask subtask1 = new Subtask("Subtask 1", "Description subtask 1", TaskStatus.NEW, epic1.getId());
        int subtask1id = taskManager.addNewSubTask(subtask1);

        Task task = taskManager.getTaskById(1);
        List <Task> history = taskManager.getHistory();
        assert history.size() == 1;

        // -------------------------------------------------------------------------

        System.out.println("Все задачи - НА СТАРТ!");

        //Создаем ещё подзадачи

        Subtask subtask2 = new Subtask("Subtask 2", "Description subtask 2", TaskStatus.NEW, epic1.getId());
        Subtask subtask3 = new Subtask("Subtask 3", "Description subtask 3", TaskStatus.NEW, epic1.getId());
        Subtask subtask4 = new Subtask("Subtask 4", "Description subtask 4", TaskStatus.NEW, epic1.getId());

        taskManager.addNewSubTask(subtask2);
        taskManager.addNewSubTask(subtask3);
        taskManager.addNewSubTask(subtask4);

        taskManager.printAllTasks();

        //Выполняем 3 подзадачи из 4

        System.out.println("1---------- Выполняем подзадачи Subtask 1,3,4 -----------");

        subtask1.setStatus(TaskStatus.DONE);
        subtask3.setStatus(TaskStatus.DONE);
        subtask4.setStatus(TaskStatus.DONE);

        taskManager.updateSubTask(subtask1);
        taskManager.updateSubTask(subtask3);
        taskManager.updateSubTask(subtask4);

        taskManager.printAllTasks();


        //Теперь добавим простые задачи типа Task

        System.out.println("2--------- Добавляем 3 задачи типа Task ---------");

        Task t1 = new Task("Task 1", "Task 1 description", TaskStatus.NEW);
        Task t2 = new Task("Task 2", "Task 2 description", TaskStatus.NEW);
        Task t3 = new Task("Task 3", "Task 3 description", TaskStatus.NEW);


        taskManager.addNewTask(t1);
        taskManager.addNewTask(t2);
        taskManager.addNewTask(t3);

        taskManager.printAllTasks();



        //Добавляем ещё один Epic 2 и два Subtask

        System.out.println("4--------- Добавляем ещё один Epic 3 и 2 подзадачи Subtask -----------");

        Epic epic2 = new Epic("Epic 2", "Description epic 2");

        taskManager.addNewEpic(epic2);

        Subtask subtask6 = new Subtask("Subtask 6", "Description subtask 6", TaskStatus.NEW, epic2.getId());
        Subtask subtask5 = new Subtask("Subtask 5", "Description subtask 5", TaskStatus.NEW, epic2.getId());

        taskManager.addNewSubTask(subtask5);
        taskManager.addNewSubTask(subtask6);

        taskManager.printAllTasks();


        // Тестово обновим статус простой задачи Task 1

        System.out.println("5---------- Обновляем статус задачи Task 1 ----------");

        t1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(t1);

        taskManager.printAllTasks();

        //Удаляем одну простую задачу Task 3

        System.out.println("6--------- Удаляем одну задачу Task 3 ----------");

        taskManager.deleteTasks(7);

        taskManager.printAllTasks();

        /* --Удаляем Epic 2 - и его Subtask исчезают вместе с ним ---
        System.out.println("7--------- Удаляем тестово Epic 2 (и его ветку) ----------");
        taskManager.deleteEpics(9);

        taskManager.printAllTasks();*/
    }
}


