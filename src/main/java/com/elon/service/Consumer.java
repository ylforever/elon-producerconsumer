package com.elon.service;

import com.elon.constant.EnumTaskEndType;
import com.elon.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 消费者类. 负责从队列中取出任务消费.
 *
 * @author elon
 * @version 1.0
 */
public class Consumer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Consumer.class);

    /**
     * 阻塞队列
     */
    private LinkedBlockingDeque<Task> blockingDeque = null;

    public Consumer(LinkedBlockingDeque<Task> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = blockingDeque.take();

                // 收到毒丸任务将毒丸放回队列头，结束当前线程.
                if (task.getTaskEndType() != EnumTaskEndType.NA) {
                    LOGGER.info("收到毒丸任务.{}", task);
                    blockingDeque.offerFirst(task, 2, TimeUnit.MILLISECONDS);
                    break;
                }

                // 消费任务. 这里可以写实际的业务逻辑.
                LOGGER.info("消费任务：{}", task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("消费者结束消费.");
    }
}
