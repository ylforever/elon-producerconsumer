package com.elon.model;

import com.alibaba.fastjson.JSONObject;
import com.elon.constant.EnumTaskEndType;

/**
 * 任务模型. 任务模型中存储生产者和消费者沟通信息. 具体存储什么信息根据实际的业务来。
 *
 * @author elon
 * @version 1.0
 */
public class Task {
    /**
     * 任务名称
     */
    private String taskName = "";

    /**
     * 任务结束方式(此字段用于标识任务是否为毒丸任务)
     */
    private EnumTaskEndType taskEndType = EnumTaskEndType.NA;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public EnumTaskEndType getTaskEndType() {
        return taskEndType;
    }

    public void setTaskEndType(EnumTaskEndType taskEndType) {
        this.taskEndType = taskEndType;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
