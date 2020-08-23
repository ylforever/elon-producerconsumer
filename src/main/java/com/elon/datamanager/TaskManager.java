package com.elon.datamanager;

/**
 * 任务管理类. 管理任务是否被终止的标识信息.
 *
 * @author elon
 * @since 1.0
 */
public class TaskManager {
    /**
     * 标识任务是否被终止
     */
    private volatile boolean isTaskTerminated = false;

    public static TaskManager instance() {
        return TaskManagerBuilder.instance;
    }

    public boolean isTaskTerminated() {
        return isTaskTerminated;
    }

    public void terminateTask() {
        isTaskTerminated = true;
    }

    public void clearTerminateFlag() {
        isTaskTerminated = false;
    }

    private static class TaskManagerBuilder {
        private static TaskManager instance = new TaskManager();
    }
}
