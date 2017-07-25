package mouse;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

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

  public String text() {
    MouseUtil.waitLoadDoc(this);
    return this.doc.text();
  }

  public String content() {
    MouseUtil.waitLoadDoc(this);
    // 找到需要的内容
    if (this.doc != null) {
      Element element = doc.select("div[id=content]").first();
      if (element != null) {
        return textInP(element);
      }

      element = doc.select("div[id=BookText]").first();
      if (element != null) {
        return bookText(element);
      }

      Element elements = doc.select("body").first();
      if (elements != null) {
        return bodyTextNodeCollect(elements);
      }

      throw new RuntimeException("没有能够找到页面的内容");
    }
    return null;
  }

  private String bodyTextNodeCollect(Element elements) {
    StringBuilder builder = new StringBuilder();
    if (elements != null) {
      for (Node element : elements.childNodes()) {
        if (element.attributes().hasKey("text")) {
          builder.append(element.toString().replaceAll("&nbsp;", ""));
          builder.append(IOUtils.LINE_SEPARATOR);
          builder.append(IOUtils.LINE_SEPARATOR);
        }
      }
    }
    return builder.toString();
  }

  private String bookText(Element element) {
    String text = element.html();
    text = text.replaceAll("&nbsp;", " ");
    text = text.replaceAll("<br>", IOUtils.LINE_SEPARATOR);
    this.setContent(text);
    return text;
  }

  private String textInP(Element element) {
    Element p = element.select("p").first();
    if (p != null) {
      String text = p.html();
      text = text.replaceAll("&nbsp;", " ");
      text = text.replaceAll("<br>", IOUtils.LINE_SEPARATOR);
      this.setContent(text);
      return text;
    } else {
      element = element.select("div[id=BookText]").first();
      if (element != null) {
        return bookText(element);
      }
    }

    return null;
  }

  public static void main(String[] args) throws IOException {
    String va = "我";
    byte[] vav = va.getBytes();
    System.out.println(vav);
    String url = null;
    Page page = null;

    /* url = "http://www.piaotian.com/html/8/8760/5636612.html";
    Page page = new Page(url);
    MouseUtil.waitLoadDoc(page);
    System.out.println(page.content());*/

    url = "http://www.snwx8.com/book/139/139211/44894468.html";
    page = new Page(url);
    MouseUtil.waitLoadDoc(page);
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
