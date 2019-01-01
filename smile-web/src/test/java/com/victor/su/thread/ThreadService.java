package com.victor.su.thread;

public class ThreadService {
    private Thread executeThread;

    private static boolean finished = false;

    public void execute (Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run () {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    //Daemon 线程死掉后执行
                    finished = true;
                } catch (InterruptedException e) {

                }
            }
        };
        executeThread.start();
    }
    public void shutDown (long mils) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime) >= mils) {
               executeThread.interrupt();
               break;
            }

            try {
                executeThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
