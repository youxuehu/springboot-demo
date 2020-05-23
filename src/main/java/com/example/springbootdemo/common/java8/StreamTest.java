package com.example.springbootdemo.common.java8;

import lombok.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        // count()
//        long count = Stream.of("1", "2", "3", "4").count();
//        System.out.println(count);
        // forEach()
//        Stream.of("1", "2", "3", "4").forEach(item -> System.out.println(item));

//        Stream.of("1", "2", "3", "4").map(item -> {
//            if (item.equals("3")) {
//                return "333";
//            }
//            return item;
//        }).forEach(item -> System.out.println(item));

//        String reduce = Stream.of("1", "2", "3", "4").reduce("", (a, b) -> (a + b));
//        System.out.println(reduce);//1234
//        Integer sum = Stream.of("1", "2", "3", "4").map(item -> {
//            return Integer.valueOf(item);
//        }).reduce((a, b) -> a + b).get();
//        System.out.println(sum);
//        Integer integer = Stream.of(1, 2, 3, 4).reduce((a, b) -> a + b).get();
//        System.out.println(integer);//10



    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class Student{
        private String name;
        private int score;

        public static void main(String[] args) {
            // 需求：列出90分以上的学生姓名，并按照分数降序排序
            List<Student> students = Arrays.asList(new Student("张1", 10), new Student("张2", 21),
                    new Student("张3", 43), new Student("张4", 87),
                    new Student("李1", 45), new Student("李2", 77),
                    new Student("李3", 40), new Student("王2", 99),
                    new Student("王1", 65), new Student("王3", 90));

//            System.out.println(students);
//            List<Student> collect = students.stream().filter(stu -> stu.getScore() >= 80).
//                    sorted(Comparator.comparing(Student::getScore).reversed()).// 加上reversed() 表示降序
//                    collect(Collectors.toList());
//            System.out.println(collect);


            // 计算总分
            Integer sumScore = students.stream().filter(stu -> stu.getScore() >= 80).
                    sorted(Comparator.comparing(Student::getScore)).map(Student::getScore).reduce((a, b) -> a + b).get();

            System.out.println(sumScore);

        }


    }
}