package jerryMousebook;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.App;
import com.dao.UserDao;
import com.model.User;

/**
 * @author zhailz
 * 
 * @Date 2017年7月20日 - 下午4:59:56 - 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class BookPubApplicationTests {

  @Resource
  private UserDao userDao;

  @Test
  public void contextLoads() {
    User value = userDao.getUser("a");
    System.out.println(value.getName());
  }
}
