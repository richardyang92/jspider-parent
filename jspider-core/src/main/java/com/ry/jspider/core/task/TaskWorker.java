package com.ry.jspider.core.task;

import com.ry.jspider.config.CrawelerConfig;
import com.ry.jspider.config.XMLConfig;
import com.ry.jspider.core.api.Service;
import com.ry.jspider.log.Log;

import java.util.concurrent.*;

/**
 * Created by yangyang on 2016/12/21.
 */
public class TaskWorker implements Runnable, Service {
    private static Log log = Log.getLogger(TaskWorker.class);
    private ConcurrentLinkedQueue<Task> taskList = new ConcurrentLinkedQueue<Task>();
    private ConcurrentLinkedQueue<Task> resultList = new ConcurrentLinkedQueue<Task>();
    private boolean run = false;
    private String id;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private TaskHandlerAdaptor handler;

    public TaskWorker(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public TaskHandlerAdaptor getHandler() {
        return handler;
    }

    public void setHandler(TaskHandlerAdaptor handler) {
        this.handler = handler;
    }

    private synchronized int getTaskSize() {
        return this.taskList.size();
    }

    public void submitTask(Task task) {
        int taskNumber = getTaskSize();
        if (taskNumber < XMLConfig.loadConfig().getInt("MaxTaskNumber", CrawelerConfig.class, new Class[]{String.class}, new Object[]{this.id})) {
            task.getAttributes().put("worker_Id", getId());

            //noinspection Since15
            this.taskList.offer(task);
        }
    }

    private Task getTask() {
        int taskNumber = getTaskSize();
        if (taskNumber <= 0) {
            return null;
        }

        @SuppressWarnings("Since15")
        Task task = (Task) this.taskList.poll();
        return task;
    }

    public void run() {
        Future<String> resultFuture;
        while (this.run) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                this.handler.exceptionCaught(e);
            }
            Task task = getTask();
            if (task != null) {
                resultFuture = this.executorService.submit(task);
                task.setResultFuture(resultFuture);

                //noinspection Since15
                this.resultList.offer(task);
            }

            for (Task result : this.resultList) {
                if (result.getResultFuture().isDone()) {
                    try {
                        String resultString = (String) result.getResultFuture().get();
                        this.handler.messageReceived(resultString);
                    } catch (InterruptedException e) {
                        this.handler.exceptionCaught(e);
                    } catch (ExecutionException e) {
                        this.handler.exceptionCaught(e);
                    }
                    this.resultList.remove(result);
                }
            }
        }
    }

    public void init() {
        this.run = true;
    }

    public void destroy() {
        this.run = false;
        this.executorService.shutdown();
    }
}
