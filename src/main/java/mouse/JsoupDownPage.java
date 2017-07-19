package mouse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author zhailz
 * 
 * @Date 2017年7月18日 - 下午2:46:34 - 
 * 
 * jsoup 的下载页面，针对的是目录页面
 */
public class JsoupDownPage extends AbstractPage {

  private List<Page> pageValues = null;

  public JsoupDownPage(String url) {
    super(url);
    iniDirectory();
  }

  private void iniDirectory() {
    MouseUtil.waitLoadDoc(this);
    Set<String> pages = new HashSet<String>();
    Elements links = doc.select("a[href]");
    if (links != null && !links.isEmpty()) {
      pageValues = new ArrayList<Page>();
      for (Element link : links) {
        String abshref = link.attr("abs:href");
        String text = link.text();
        if (text.contains("章") && !pages.contains(abshref)) {
          pages.add(abshref);
          Page pagetmp = new Page(abshref);
          pagetmp.setTitle(text);
          pageValues.add(pagetmp);
        }
      }
    }
  }

  public List<Page> getPageValues() {
    return pageValues;
  }

  public void setPageValues(List<Page> pageValues) {
    this.pageValues = pageValues;
  }

}
