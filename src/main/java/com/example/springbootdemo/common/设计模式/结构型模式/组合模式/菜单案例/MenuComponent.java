package com.example.springbootdemo.common.设计模式.结构型模式.组合模式.菜单案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className MenuComponent
 * @date 2021/5/9 9:46 下午
 * @desrription 菜单组件
 */
public abstract class MenuComponent {

    protected String name;
    protected int level;

    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void del(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChildren(int index) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return name;
    }

    public abstract void print();
}
