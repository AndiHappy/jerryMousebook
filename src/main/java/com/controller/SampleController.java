package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.ConfigCenter;

@Controller
public class SampleController {

  private ConfigCenter configCenter;

  @RequestMapping("/")
  /**
   * @ResponseBody 这个标注影响着：
   *               URL handler的选择
   *               有ResponseBody的时候，处理的handler是：
   *               org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor
   *               没有的情况是：
   *               org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler
   * 
   *               影响的方法名称是：
   *               org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite.selectHandler(Object, MethodParameter)
   */
  public String home(ModelMap map) {
    map.addAttribute("host", "http://github.com");
    return "index";
  }

  @RequestMapping("/hello")
  public String hello() {

    return "Hello World!";
  }

  @RequestMapping("/config")
  @ResponseBody
  public String config() {
    return configCenter.toString();
  }

}