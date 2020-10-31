package com.example.springbootdemo.controller.hadoop.param;

public enum ExecStatusEnum {

    UNASSIGNED(0, "UNASSIGNED", "Job提交到在JobTracker后，初始化setup、cleanup、Map、Reduce任务时状态置UNASSIGNED状态。"),
    RUNNING(1, "RUNNING", "Task分配到TaskTracker，并由TaskTracker启动JVM执行该任务时状态置RUNNING状态。"),
    COMMIT_PENDING(2, "COMMIT_PENDING", "任务成功执行完成，检查是否需要把临时目录下的结果转移到输出目录中，如需要则置COMMIT_PENDING状态，通常Map结果都放在本地，则不需要此状态，直接到SUCCEEDED状态，但在没有Reduce阶段，Map结束后处理结果直接放到输出目录时则需要此状态。"),
    SUCCEEDED(3, "SUCCEEDED", "如果是非Reduce任务(没有输出结果，或者结果只是临时存放在本地)则处理结束后直接到SUCCEEDED，如果是Reduce任务则等待COMMIT结束后进入SUCCEEDED状态。"),
    FAILED_UNCLEAN(4, "FAILED_UNCLEAN", "当在TaskTracker中执行Task发生错误，则把状态置为FAILD_UNCLEAN状态，由JobTracker对该Task分配Task Cleanup操作给TaskTracker执行。一个任务进入FAILED_UNCLEAN状态后，JobTracker发给TaskTracker分配TaskCleanup操作。"),
    KILLED_UNCLEAN(5, "KILLED_UNCLEAN", "当在TaskTracker中执行Task时，用户发起Kill Task操作（Kill Job操作会先Kill所有Task），则把状态置为KILLED_UNLEAN状态，由JobTracker对该Task分配Task Cleanup操作给TaskTracker执行。一个任务进入KILLED_UNCLEAN状态后，JobTracker发给TaskTracker分配TaskCleanup操作。"),
    FAILED(6, "FAILED", "当TaskTracker完成FAILED_UNCLEAN操作后，状态转为FAILED。"),
    KILLED(7, "KILLED", "当TaskTracker完成FAILED_UNCLEAN操作后，状态转为KILLED。"),
    ;

    int code;
    String value;
    String desc;

    ExecStatusEnum(int code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public ExecStatusEnum getExecStatusEnum(String value) {
        ExecStatusEnum[] values = ExecStatusEnum.values();
        for (ExecStatusEnum execStatusEnum : values) {
            if (execStatusEnum.value.equals(value)) {
                return execStatusEnum;
            }
        }
        return null;
    }
}
