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
public class BDKeyWords {

  private static String urlPrex = "https://www.baidu.com/s?rsv_spt=1&rsv_iqid=0xb05640bd000096a6&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=14&rsv_sug1=16&rsv_sug7=100&rsv_sug2=0&inputT=4939&rsv_sug4=7418&wd=";

  /**
   * 搜索的结果有可用的，但是也有不可用的
   * @param args
   * @throws UnsupportedEncodingException 
   */
  public static void main(String[] args) throws UnsupportedEncodingException {
    List<String> https = findDicByKeyWordsByBing("畅捷通");
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
    // <div id="content_left">
    System.out.println(page.doc.html());
    System.out.println(page.doc.text());
    Element elementDiv = page.doc.select("div[id=content_left]").first();
    Elements element = elementDiv.select("a");
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
