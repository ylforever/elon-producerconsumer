package com.elon.rest;

import com.elon.datamanager.TaskManager;
import com.elon.service.ProConsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/produce-consumer")
@Api(tags = "生产者消费者服务")
public class ProduceConsumerController {
    @PostMapping("/start-task/{taskAmount}")
    @ApiOperation(value = "启动服务任务")
    public void startTask(@PathVariable("taskAmount") int taskAmount) {
        ProConsService proConsService = new ProConsService();
        proConsService.startTask(taskAmount);
    }

    /**
     * 启动多线程任务.
     *
     * @param taskAmount 任务数量
     * @param consumerThreadAmount 线程数量
     */
    @PostMapping("/start-muti-task/{taskAmount}/{consumerThreadAmount}")
    @ApiOperation(value="启动多线程任务")
    public void startMutiTask(@PathVariable("taskAmount") int taskAmount,
                              @PathVariable("consumerThreadAmount") int consumerThreadAmount) {
        ProConsService proConsService = new ProConsService();
        proConsService.startMutiTask(taskAmount, consumerThreadAmount);
    }

    /**
     * 终止任务
     */
    @PostMapping("/terminate-task")
    @ApiOperation(value = "终止任务")
    public void terminateTask() {
        TaskManager.instance().terminateTask();
    }

    @GetMapping("/get-string/{num}")
    @ApiOperation(value = "获取压缩字符串")
    public String getString(@PathVariable("num") int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; ++i) {
            sb.append("A");
        }

        return sb.toString();
    }
}
