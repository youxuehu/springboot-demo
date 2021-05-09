package com.example.springbootdemo.common.设计模式.结构型模式.组合模式.菜单案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className Client
 * @date 2021/5/9 9:55 下午
 * @desrription 这是类的描述信息
 */
public class Client {

    public static void main(String[] args) {

        MenuComponent menu1 = new Menu("菜单管理", 2);
        menu1.add(new MenuItem("页面访问", 3));
        menu1.add(new MenuItem("展开访问", 3));
        menu1.add(new MenuItem("编辑访问", 3));
        menu1.add(new MenuItem("删除访问", 3));
        menu1.add(new MenuItem("新增访问", 3));

        MenuComponent menu2 = new Menu("权限管理", 2);
        menu2.add(new MenuItem("新增访问", 3));
        menu2.add(new MenuItem("提交保存", 3));

        MenuComponent menu3 = new Menu("角色管理", 2);
        menu3.add(new MenuItem("页面访问", 3));
        menu3.add(new MenuItem("新增角色", 3));
        menu3.add(new MenuItem("修改角色", 3));

        MenuComponent menuComponent = new Menu("系统管理", 1);
        menuComponent.add(menu1);
        menuComponent.add(menu2);
        menuComponent.add(menu3);

        menuComponent.print();
    }
}
