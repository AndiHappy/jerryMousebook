package mouse;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

public class FileUtil {

  private static Logger log = org.slf4j.LoggerFactory.getLogger(FileUtil.class);

  /**
   * 写入文件，不存在则创建文件后保存
   * 
   * */
  public static boolean saveValueToFile(File file, String value, boolean append) {
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      FileUtils.write(file, value, "UTF-8", append);
      return true;
    } catch (IOException e) {
      log.error("存储数据出现错误：{}", e);
      return false;
    }
  }

}
