package jerryMousebook;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.App;
import com.cache.UserCache;
import com.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class CacheTests {

  /**
   * 使用的log是logback，默认配置logback.xm文件即可
   * 在工程src目录下建立logback.xml
   * 注：
   * 1.logback首先会试着查找logback.groovy文件;
   * 2.当没有找到时，继续试着查找logback-test.xml文件;
   * 3.当没有找到时，继续试着查找logback.xml文件;
   * 4.如果仍然没有找到，则使用默认配置（打印到控制台）。
   * */
  private Logger log = LoggerFactory.getLogger(getClass());

  @Resource
  private UserCache cache;

  @Test
  public void testCache() {
    cache.cacheUser(new User("aa", 1));
    User aa = cache.getcacheUser("aa");
    log.info(aa.toString());

    cache.cacheStringValue("aa", "bb");
    String value = cache.getcacheStringValue("aa");
    System.out.println(value);

  }
}
