package mouse;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;

/**
 * @author zhailz
 * 
 * @Date 2017年7月18日 - 下午3:20:29 - 
 * 
 * 虚拟页面
 */
public abstract class AbstractPage {

  private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
  public static String userAgent ="Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36"; 


  protected String url = null;
  protected Document doc;
  protected boolean isIni = false;

  public AbstractPage(String url) {
    this.url = url;
    this.ini();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Document getDoc() {
    return doc;
  }

  public void setDoc(Document doc) {
    this.doc = doc;
  }

  public boolean isIni() {
    return isIni;
  }

  public void setIni(boolean isIni) {
    this.isIni = isIni;
  }

  public void submit(Runnable task) {
    MouseUtil.submit(task);
  }

  public void ini() {
    submit(new Runnable() {
      @Override
      public void run() {
        if (doc == null) {
          try {
            log.info("开始初始化:{}", url);
            Connection connection = Jsoup.connect(url);
            connection.timeout(MouseUtil.waitTimeMils);
            connection.userAgent(userAgent);
            Document docValue = connection.get();
            doc = docValue;
            isIni = true;
            log.info("{} 初始化完成。", url);
          } catch (IOException e) {
            log.error("{},初始化出现错误：{}", url, e);
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
          }
        }
      }
    });

  }
}
