package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Menu;

import java.util.List;

/**
 * ClassName: MenuService
 * Package: com.atguigu.crowd.service
 * Description:
 *
 * @Author peter
 * @Create 2023/6/22 15:36
 * @Version 1.0
 */
public interface MenuService {
    List<Menu> getAll();

    void saveMenu(Menu menu);

    void editMenu(Menu menu);

    void deleteMenu(Integer id);
}
