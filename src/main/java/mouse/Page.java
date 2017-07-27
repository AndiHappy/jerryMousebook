package mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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
  
  private int compareValue = -1;
  
  private JsoupDownPage parent = null;
  
  private boolean checkNext = false;

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
      /**
       * 应对不同的网站，采用暴力过滤的形式
       * */
      List<Node> testvalue = getAllTextNodes(doc);
      StringBuilder builder = new StringBuilder();
      for (Node element : testvalue) {
        String nodeValue = element.toString();
        if (StringUtils.isNotBlank(nodeValue) && nodeValue.contains("&nbsp;")) {
          String value = nodeValue.replaceAll("&nbsp;", "");
          value = value.replaceAll("&gt;", "");
          if(StringUtils.isNotBlank(value)) {
            builder.append(value);
            builder.append(IOUtils.LINE_SEPARATOR);
            builder.append(IOUtils.LINE_SEPARATOR);
          }
        }
      }
      
      String text = builder.toString();
      if (StringUtils.isNotBlank(text)) {
        this.setContent(text);
        return text;
      } else {
        throw new RuntimeException("没有能够找到页面的内容");
      }
    }
    return null;
  }

  /**
   * 拿到所有的textNode，进行逐个的过滤
   * */
  private List<Node> getAllTextNodes(Node doc) {
    List<Node> result = new ArrayList<Node>();
    List<Node> tmp = doc.childNodes();
    if (tmp != null && tmp.size() > 0) {
      for (Node node : tmp) {
        if (node.nodeName().equalsIgnoreCase("#text")) {
          result.add(node);
        } else {
          result.addAll(getAllTextNodes(node));
        }
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    String va = "我";
    byte[] vav = va.getBytes();
    System.out.println(vav);
    String url = null;
    Page page = null;

    url = "http://www.piaotian.com/html/8/8760/5636612.html";
    page = new Page(url);
    MouseUtil.waitLoadDoc(page);
    System.out.println(page.content());

    url = "http://www.snwx8.com/book/139/139211/44894468.html";
    page = new Page(url);
    MouseUtil.waitLoadDoc(page);
    System.out.println(page.content());

    url = "http://www.brquge.com/read/4/4939/15462931.html";
    page = new Page(url);
    MouseUtil.waitLoadDoc(page);
    System.out.println(page.content());

    url = "http://www.sqsxs.com/book/0/1/5.html";
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

public JsoupDownPage getParent() {
	return parent;
}

public void setParent(JsoupDownPage parent) {
	this.parent = parent;
}

public boolean isCheckNext() {
	return checkNext;
}

public void setCheckNext(boolean checkNext) {
	this.checkNext = checkNext;
}

public int getCompareValue() {
	return compareValue;
}

public void setCompareValue(int compareValue) {
	this.compareValue = compareValue;
}

}
