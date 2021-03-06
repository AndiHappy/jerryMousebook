package mouse;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhailz
 * @date 17/7/13 - 下午2:18.
 *
 *       开始拉去文件，分析文件
 */
public class MouseApp {

	public static void main(String[] args) throws Exception {
		String url = "http://www.piaotian.com/html/8/8791/";
		JsoupDownPage page = new JsoupDownPage(url);
		List<Page> pages = page.getPageValues();
		File filebook = new File("逍遥梦路.txt");
		for (Page page2 : pages) {
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