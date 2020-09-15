package org.example.google.guave.event;

public class MyEventTask {

    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "MyEventTask{" +
                "taskId=" + taskId +
                '}';
    }
}
