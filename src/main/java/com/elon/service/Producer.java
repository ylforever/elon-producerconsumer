package com.elon.service;

import com.elon.constant.EnumTaskEndType;
import com.elon.datamanager.TaskManager;
import com.elon.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 生产者类. 负责创建任务放到阻塞队列.
 *
 * @author  elon
 * @version 1.0
 */
public class Producer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Producer.class);

    private LinkedBlockingDeque<Task> blockingDeque = null;

    private final int taskAmount;

    public Producer(LinkedBlockingDeque<Task> blockingDeque, int taskAmount) {
        this.blockingDeque = blockingDeque;
        this.taskAmount = taskAmount;
    }

    @Override
    public void run() {
        EnumTaskEndType taskEndType = EnumTaskEndType.COMPLETE;
        for (int i = 1; i <= taskAmount; ++i) {
            try {
                // 处理任务被用户终止的场景
                if (TaskManager.instance().isTaskTerminated()) {
                    taskEndType = EnumTaskEndType.TERMINATE;
                    break;
                }

                Task task = new Task();
                task.setTaskName("任务：" + i);
                blockingDeque.offer(task, 2, TimeUnit.MILLISECONDS);
                LOGGER.info("生产任务：{}", task);
            } catch (InterruptedException e) {
                LOGGER.error("创建任务异常.", e);
            }
        }

        // 放入毒丸任务已结束消费者线程
        try {
            Task task = new Task();
            task.setTaskName("任务处理结束毒丸");
            task.setTaskEndType(taskEndType);
            blockingDeque.offer(task, 2, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("放入毒丸任务异常.", e);
        }
    }
}
