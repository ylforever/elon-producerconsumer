package com.elon.service;

import com.elon.constant.EnumTaskEndType;
import com.elon.model.MutiLinkedBlockingDeque;
import com.elon.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 多线程消费者类.
 *
 * @author elon
 * @version 1.0
 */
public class MutiConsumer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(MutiConsumer.class);

    private MutiLinkedBlockingDeque blockingDeque = null;

    public MutiConsumer(MutiLinkedBlockingDeque blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = blockingDeque.take();
                if (task.getTaskEndType() != EnumTaskEndType.NA) {
                    LOGGER.info("收到毒丸任务.{}", task);
                    blockingDeque.offerFirst(task, 2, TimeUnit.MILLISECONDS);

                    final int consumerAmount = blockingDeque.decrementConsumer();
                    if (consumerAmount <= 0) {
                        LOGGER.info("所有消费者停止任务处理.");
                    }
                    return;
                }

                // 消费任务(等待10秒，模拟实际业务处理时间)
                LOGGER.info("消费任务.{}", task);
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
