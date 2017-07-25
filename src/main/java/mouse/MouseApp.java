package mouse;

import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhailz
 * @date 17/7/13 - 下午2:18.
 *
 *       开始拉去文件，分析文件
 */
public class MouseApp {

	public static void main(String[] args) throws Exception {
		String url = "http://www.brquge.com/modules/article/reader.php?aid=7751";
		JsoupDownPage page = new JsoupDownPage(url);
		TreeMap<Integer, Page> pages = page.getPageValues();
		File filebook = new File("hose.txt");
		for (Entry<Integer, Page> entry : pages.entrySet()) {
			Page page2 = entry.getValue();
			String title = page2.getTitle();
			if (StringUtils.isNotBlank(title)) {
				FileUtil.saveValueToFile(filebook, title, true);
			}
			FileUtil.saveValueToFile(filebook, "\r", true);
			String value = page2.content();
			if (StringUtils.isNotBlank(value)) {
				FileUtil.saveValueToFile(filebook, value, true);
			}
			FileUtil.saveValueToFile(filebook, "\r", true);
		}

		System.exit(0);
	}
}