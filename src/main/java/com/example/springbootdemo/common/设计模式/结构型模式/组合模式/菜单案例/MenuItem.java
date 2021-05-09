package com.example.springbootdemo.common.设计模式.结构型模式.组合模式.菜单案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className MenuItem
 * @date 2021/5/9 9:53 下午
 * @desrription 菜单项
 */
public class MenuItem extends MenuComponent {

    public MenuItem(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(name);
    }
}
