package com.elon.service;

import com.elon.model.MutiLinkedBlockingDeque;
import com.elon.model.Task;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * 生产者消费者服务。启动生产者消费者线程.
 *
 * @author elon
 * @version 1.0
 */
public class ProConsService {
    /**
     * 开始任务.
     *
     * @param taskAmount 生产的任务数量
     * @author elon
     */
    public void startTask(int taskAmount) {
        LinkedBlockingDeque<Task> blockingDeque = new LinkedBlockingDeque<>();

        Producer producer = new Producer(blockingDeque, taskAmount);

        new Thread(producer).start();

        Consumer consumer = new Consumer(blockingDeque);
        new Thread(consumer).start();
    }

    /**
     * 开始多线程任务.
     *
     * @param taskAmount           任务数量
     * @param consumerThreadAmount 消费者线程数量
     * @author elon
     */
    public void startMutiTask(int taskAmount, int consumerThreadAmount) {
        MutiLinkedBlockingDeque blockingDeque = new MutiLinkedBlockingDeque(consumerThreadAmount);

        Producer producer = new Producer(blockingDeque, taskAmount);
        new Thread(producer).start();

        for (int i = 1; i <= consumerThreadAmount; ++i) {
            MutiConsumer consumer = new MutiConsumer(blockingDeque);
            new Thread(consumer).start();
        }
    }
}
