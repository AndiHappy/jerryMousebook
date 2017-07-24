package mouse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

public class MouseUtil {

  // 缺漏章节数
  public static final int ErrorPage = 0;

  // 等待时间
  public static int waitTimeMils = 500000000;

  private static Logger log = org.slf4j.LoggerFactory.getLogger(MouseUtil.class);

  protected static ExecutorService executePool = new ThreadPoolExecutor(24, 24, 60L,
      TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(5000));

  public static void submit(Runnable task) {
    executePool.submit(task);
  }

  /**
   * 等待一定的时间，放弃
   * */
  public static void waitLoadDoc(AbstractPage page) {
    int waitTime = 0;
    int waitTimes = 0;
    try {
      // 乐观锁的用法
      for (;;) {
        if (page.doc != null || waitTime > waitTimeMils) {
          break;
        } else {
          // 等待，直到初始化完成
          log.info("{},没有加载完成，需要等待...", page.url);
          int waitTimetmp = waitTimes * 5 + 1000;
          Thread.sleep(waitTimetmp);
          waitTime = waitTime + waitTimetmp;
          waitTimes++;
        }
      }
    } catch (InterruptedException e) {
      log.error("初始化目录出现错误：{}", e);
    }
  }
}
