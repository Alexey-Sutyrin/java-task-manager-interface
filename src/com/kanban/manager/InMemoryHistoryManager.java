package com.kanban.manager;  // итерация 2 - не менять

import java.util.ArrayList;
import java.util.List;

import com.kanban.tasks.Task;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int LIMIT = 10; //+Хорошо что вынес в константу, в джаве константые значения принято большими буквами, т.е. LIMIT

    private final List<Task> history = new ArrayList<>();
    @Override
    public void addTask (Task task) {
        if (task == null) {
            return;
        }
        history.add(task);

        if (history.size() > LIMIT) {
            history.remove(0);
            System.out.println("Количество превысило 10!");
        }
        System.out.println("Счётчик тасков + 1!");
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history); // + Отлично что возвращаешь копию, чтобы пользователи класса не могли править историю в обход задекларированного интерфейса (только через метод addTask)
    }
}