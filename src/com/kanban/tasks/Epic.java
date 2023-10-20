package com.kanban.tasks; //не менять - 2 итерация
/* java-sprint-4 */
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private ArrayList<Integer> subtaskIds;
    public Epic(String title, String description) {
        super(title, description, TaskStatus.NEW);
        subtaskIds = new ArrayList<>();
    }
    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }
    public void addSubtaskId (Integer subtaskId){
        subtaskIds.add(subtaskId);
    }



    protected List<Subtask> tasks = new ArrayList<>();
    public void addSubtask(Subtask task) {
        this.tasks.add(task);
        this.setStatus();
    }

    public List<Subtask> getTasks() {
        return tasks;
    }

    public void removeSubtask(int subTaskId){
        tasks.remove(subTaskId);
        setStatus(); //добавил обновление статуса
    }

    public void setStatus() {
        boolean allDone = true;
        boolean allNew = true;

        for (Subtask task : tasks) { // Проверка на то, выполнен ли уже Epic, или ещё нет

            if (task.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }

            if (task.getStatus() != TaskStatus.NEW) {
                allNew = false;
            }
        }

        if (allDone) {
            status = TaskStatus.DONE;
        } else if (allNew) {
            status = TaskStatus.NEW;
        } else {
            status = TaskStatus.IN_PROGRESS;
        }
    }
}
