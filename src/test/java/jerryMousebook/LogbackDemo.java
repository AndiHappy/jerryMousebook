package jerryMousebook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class LogbackDemo {
  private static Logger log = LoggerFactory.getLogger(LogbackDemo.class);

  @Test
  public void logTest() {
    log.trace("trace ====== >");
    log.debug("debug ====== >");
    log.info("info ====== >");
    log.warn("warn ====== >");
    log.error("error ====== >");

    String name = "Aub";
    String message = "3Q";
    String[] fruits = { "apple", "banana" };

    // logback提供的可以使用变量的打印方式，结果为"Hello,Aub!"
    log.info("Hello,{}!", name);

    // 可以有多个参数,结果为“Hello,Aub! 3Q!”
    log.info("Hello,{}!   {}!", name, message);

    // 可以传入一个数组，结果为"Fruit: apple,banana"
    log.info("Fruit:  {},{}", fruits);
  }
}
