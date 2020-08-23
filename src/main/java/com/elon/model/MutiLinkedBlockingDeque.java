package com.elon.model;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程消费者阻塞队列.
 *
 * @author elon
 * @version 1.0
 */
public class MutiLinkedBlockingDeque extends LinkedBlockingDeque<Task>  {
    /**0
     * 消费者数量
     */
    private AtomicInteger consumerAmount = new AtomicInteger();

    public MutiLinkedBlockingDeque(int amount) {
        consumerAmount.set(amount);
    }

    public int decrementConsumer() {
        return consumerAmount.decrementAndGet();
    }
}
