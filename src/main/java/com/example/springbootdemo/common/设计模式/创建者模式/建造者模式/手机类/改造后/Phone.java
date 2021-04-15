package com.example.springbootdemo.common.设计模式.创建者模式.建造者模式.手机类.改造后;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:11 下午
 * @desrription 使用建造者模式创建Phone对象
 */
public class Phone {
    private String cpu;
    private String memory;
    private String screen;
    private String mainBoard;

    private Phone(Builder builder) {
        this.cpu = builder.cpu;
        this.mainBoard = builder.mainBoard;
        this.memory = builder.memory;
        this.screen = builder.screen;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", screen='" + screen + '\'' +
                ", mainBoard='" + mainBoard + '\'' +
                '}';
    }

    public static final class Builder {
        private String cpu;
        private String memory;
        private String screen;
        private String mainBoard;

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder memory(String memory) {
            this.memory = memory;
            return this;
        }

        public Builder screen(String screen) {
            this.screen = screen;
            return this;
        }

        public Builder mainBoard(String mainBoard) {
            this.mainBoard = mainBoard;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }
    }
}
