package mouse;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author zhailz
 * 
 * @Date 2017年7月18日 - 下午3:09:48 - 
 * 
 * 对应每一章节的内容
 */
public class Page extends AbstractPage {

  private String content = null;

  private String title = null;

  private String text = null;

  public Page(String url) {
    super(url);
  }

  public String text() {
    MouseUtil.waitLoadDoc(this);
    return this.doc.text();
  }

  public String content() {
    MouseUtil.waitLoadDoc(this);
    // 找到需要的内容
    if (this.doc != null) {
      Element element = doc.select("div[id=content]").first();
      if (element == null) {
        element = doc.select("div[id=BookText]").first();
        String text = element.html();
        text = text.replaceAll("&nbsp;", " ");
        text = text.replaceAll("<br>", "\r");
        this.setContent(text);
      } else {
        Element p = element.select("p").first();
        String text = p.html();
        text = text.replaceAll("&nbsp;", " ");
        text = text.replaceAll("<br>", "\r");
        this.setContent(text);
      }
    }

    return getContent();
  }

  public static void main(String[] args) throws IOException {
    String va = "我";
    byte[] vav = va.getBytes();
    System.out.println(vav);
    String url = "http://www.lwxs520.com/books/68/68924/16747180.html";
    url = "http://cn.bing.com/search?q=%E5%86%9C%E5%AE%B6%E5%AD%90%E7%9A%84%E5%8F%A4%E4%BB%A3%E7%A7%91%E4%B8%BE%E7%94%9F%E6%B4%BB";
    Page page = new Page(url);
    MouseUtil.waitLoadDoc(page);
    // System.out.println(page.doc.html());
    Elements element = page.doc.select("li[class=b_algo]");
    System.out.println(element.size());
    for (Element element2 : element) {
      Element cite = element2.select("cite").first();
      System.out.println(cite.text());
    }
    // System.out.println(page.text());
    System.exit(0);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
