package com.ry.jspider.core.task;

import com.ry.jspider.core.api.Service;
import com.ry.jspider.core.config.SpiderXMLConfig;
import com.ry.jspider.core.config.XMLConfig;
import com.ry.jspider.core.log.Log;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangyang on 2016/12/21.
 */
public class TaskWorker implements Runnable, Service {
    private static Log log = Log.getLogger(TaskWorker.class);
    private LinkedList<Task> taskList = new LinkedList();
    private LinkedList<Task> resultList = new LinkedList();
    private Lock lock = new ReentrantLock();
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
        if (taskNumber < XMLConfig.loadConfig().getInt("MaxTaskNumber", SpiderXMLConfig.class, new Class[]{String.class}, new Object[]{this.id})) {
            this.lock.lock();
            task.getAttributes().put("worker_Id", getId());

            //noinspection Since15
            this.taskList.push(task);
            this.lock.unlock();
        }
    }

    private Task getTask() {
        int taskNumber = getTaskSize();
        if (taskNumber <= 0) {
            return null;
        }
        this.lock.lock();

        @SuppressWarnings("Since15")
        Task task = (Task) this.taskList.pop();
        this.lock.unlock();
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
                this.lock.lock();

                //noinspection Since15
                this.resultList.push(task);
                this.lock.unlock();
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
