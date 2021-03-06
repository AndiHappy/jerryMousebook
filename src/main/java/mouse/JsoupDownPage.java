package mouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author zhailz
 * 
 * @Date 2017年7月18日 - 下午2:46:34 -
 * 
 *       jsoup 的下载页面，针对的是目录页面
 */
public class JsoupDownPage extends AbstractPage {

	private List<Page> pageValues = new ArrayList<Page>();

	public JsoupDownPage(String url) {
		super(url);
		// 初始化目录
		iniDirectory();
	}

	private void iniDirectory() {
		MouseUtil.waitLoadDoc(this);
		HashMap<String, Page> pages = new HashMap<String, Page>();
		Elements links = doc.select("a[href]");
		if (links != null && !links.isEmpty()) {
			int errorFlag = 0;
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String abshref = link.attr("abs:href");
				String text = link.text();
				if (text.contains("章") || text.contains(".") || Pattern.compile("[0-9]{1,}").matcher(text).find()) {
					Page pagetmp = pages.get(abshref);
					if (pagetmp == null) {
						pagetmp = new Page(abshref);
						pagetmp.setTitle(text);
						/**
						 * 根据text分析出具体的章节数
						 * 但是具体的章节是可以从头开始的，这样就比较的混乱了
						 * 只能按照默认的顺序进行安排
						 * */
						int compare = ChineseNumber.parseNumber(text);
						pagetmp.setCompareValue(compare);
						pageValues.add(pagetmp);
						pages.put(abshref, pagetmp);
					} else {
						errorFlag++;
						/**
						 * errorFlag 立的过多的话，需要从上一页 或者下一页的时候，进行补偿性的查找
						 * EP:http://www.brquge.com/modules/article/reader.php?aid=7751
						 */
						if (errorFlag > MouseUtil.ErrorPage) {
							pagetmp.setParent(this);
							pagetmp.setCheckNext(true);
						}
					}
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
