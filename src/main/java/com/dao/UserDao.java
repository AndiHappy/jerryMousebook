package com.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.model.User;

/**
 * @author zhailz
 * 
 * @Date 2017年7月20日 - 下午3:11:02 - 
 */

@Component
public class UserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void create(String name, Integer age) {
    jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
  }

  public void deleteByName(String name) {
    jdbcTemplate.update("delete from USER where NAME = ?", name);
  }

  public Integer getAllUsers() {
    return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
  }

  public void deleteAllUsers() {
    jdbcTemplate.update("delete from USER");
  }

  public User getUser(String name) {
    Map<String, Object> map = jdbcTemplate.queryForMap("select * from user where name = '" + name
        + "'");
    if (map != null) {
      User user = new User();
      user.setAge((Integer) map.get("age"));
      user.setName((String) map.get("name"));
      return user;
    }
    return null;
  }

}
