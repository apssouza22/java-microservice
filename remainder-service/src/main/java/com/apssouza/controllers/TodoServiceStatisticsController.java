package com.apssouza.controllers;

import com.apssouza.monitors.TodoServiceMethodInvokedStore;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.LongSummaryStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/todo-statistics")
@RestController
public class TodoServiceStatisticsController {

    @Autowired
    TodoServiceMethodInvokedStore monitor;

    @GetMapping
    public ObjectNode get() {
        LongSummaryStatistics statistics = monitor.getStatistics();
        return JsonNodeFactory.instance.objectNode().
                put("average-duration", statistics.getAverage()).
                put("invocation-count", statistics.getCount()).
                put("min-duration", statistics.getMin()).
                put("max-duration", statistics.getMax());
    }

}
