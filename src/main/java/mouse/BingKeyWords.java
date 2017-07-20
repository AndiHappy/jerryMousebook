package mouse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author zhailz
 * 
 * @Date 2017年7月18日 - 下午7:30:30 -
 * 
 *  根据关键字，拿到关键字对应的目录
 */
public class BingKeyWords {

  private static String urlPrex = "http://cn.bing.com/search?q=";

  /**
   * 搜索的结果有可用的，但是也有不可用的
   * @param args
   * @throws UnsupportedEncodingException 
   */
  public static void main(String[] args) throws UnsupportedEncodingException {
    List<String> https = findDicByKeyWordsByBing("易鼎");
    for (String string : https) {
      System.out.println(string);
    }

  }

  /**
   * 根据关键词返回目录，感觉bing搜索对应的目录
   * @param key 关键词
   * @throws UnsupportedEncodingException 
   * */
  public static List<String> findDicByKeyWordsByBing(String key)
      throws UnsupportedEncodingException {
    List<String> va = new ArrayList<String>();
    String encode = urlPrex + URLEncoder.encode(key, "UTF-8");
    Page page = new Page(encode);
    MouseUtil.waitLoadDoc(page);
    Elements element = page.doc.select("li[class=b_algo]");
    System.out.println(element.size());
    for (Element element2 : element) {
      Element cite = element2.select("a").first();
      String http = cite.absUrl("href");
      if (http.startsWith("http:") || http.startsWith("www.")) {
        va.add(http);
      }
    }
    return va;
  }

}
