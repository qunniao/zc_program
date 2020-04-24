package com.zhancheng.applet.test;

import com.zhancheng.core.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BianShuHeng
 * @decription
 * @project TestController
 * @date 2019/10/25 15:20
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/1")
    public Object test1(Long cid) {
        return null;
    }
}
