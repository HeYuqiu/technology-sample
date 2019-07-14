package com.hyq.concurrent.keyword_volatile;

/**
 * 现在已经将isOver设置成了volatile变量，这样在main线程中将isOver改为了true后，
 * thread的工作内存该变量值就会失效，从而需要再次从主内存中读取该值，
 * 现在能够读出isOver最新值为true从而能够结束在thread里的死循环，从而能够顺利停止掉thread线程。
 *
 */
public class VolatileSample4 {
    private static volatile boolean isOver = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver) {
//                    System.out.println(1);
                }
                System.out.println("done!");
            }
        });
        thread.start();
        Thread.sleep(2000);
        isOver = true;
    }
}
