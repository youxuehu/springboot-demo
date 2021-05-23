package com.example.springbootdemo.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobHandlerFactory {

    private static Map<TaskTypeEnum, JobHandler> taskTypeEnumMap = new HashMap<>();

    public static void register(JobHandler jobHandler) {
        synchronized (JobHandlerFactory.class) {
            taskTypeEnumMap.put(jobHandler.getType(), jobHandler);
        }
    }

    public static List<JobHandler> getJobHandlers() {
        List<JobHandler> handlers = new ArrayList<>();
        for (Map.Entry<TaskTypeEnum, JobHandler> maps : taskTypeEnumMap.entrySet()) {
            handlers.add(maps.getValue());
        }
        return handlers;
    }

    public static List<JobHandler> getJobHandlers(List<TaskTypeEnum> taskTypeEnums) {
        List<JobHandler> handlers = new ArrayList<>();
        taskTypeEnums.forEach(taskTypeEnum -> {
            if (taskTypeEnumMap.containsKey(taskTypeEnum)) {
                handlers.add(taskTypeEnumMap.get(taskTypeEnum));
            }
        });
        return handlers;
    }

    public static JobHandler getJobHandler(TaskTypeEnum taskTypeEnum) {
        if (taskTypeEnumMap.containsKey(taskTypeEnum)) {
            return taskTypeEnumMap.get(taskTypeEnum);
        }
        return null;
    }
}
