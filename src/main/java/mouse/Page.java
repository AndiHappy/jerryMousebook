package mouse;

import java.io.IOException;

import org.jsoup.nodes.Element;

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

  public Page(String url) {
    super(url);
  }

  public String content() {
    MouseUtil.waitLoadDoc(this);
    // 找到需要的内容
    if (this.doc != null) {
      Element element = doc.select("div[id=content]").first();
      String text = element.text();
      text = text.replaceAll("\\s", "\\\r");
      this.setContent(text);
    }

    return getContent();
  }

  public static void main(String[] args) throws IOException {
    String url = "http://www.lwxs520.com/books/68/68924/16747180.html";
    Page page = new Page(url);
    System.out.println(page.content());
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
