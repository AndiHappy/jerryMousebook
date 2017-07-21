package jerryMousebook;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.App;
import com.cache.UserCache;
import com.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class CacheTests {

  @Resource
  private UserCache cache;

  @Test
  public void testCache() {
    cache.cacheUser(new User("aa", 1));
    User aa = cache.getcacheUser("aa");
    System.out.println(aa.toString());

    cache.cacheStringValue("aa", "bb");
    String value = cache.getcacheStringValue("aa");
    System.out.println(value);

  }
}
