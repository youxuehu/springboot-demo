package com.example.springbootdemo.common.设计模式.结构型模式.组合模式.菜单案例;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youxuehu
 * @version v1.0
 * @className Menu
 * @date 2021/5/9 9:49 下午
 * @desrription 菜单
 */
public class Menu extends MenuComponent {

    private final List<MenuComponent> menuComponentList = new ArrayList<>();

    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponentList.add(menuComponent);
    }

    @Override
    public void del(MenuComponent menuComponent) {
        menuComponentList.remove(menuComponent);
    }

    @Override
    public MenuComponent getChildren(int index) {
        return menuComponentList.get(index);
    }

    @Override
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }

        // 打印菜单名称
        System.out.println(name);
        //打印子菜单项名称
        for (MenuComponent menuComponent : menuComponentList) {
            menuComponent.print();
        }
    }
}
