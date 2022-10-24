package ch01;


import java8.ch01.Apple;
import java8.ch01.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Java8Test {

    @Test
    @DisplayName("정규식 표현 테스트")
    public void 정규식() {


        // given
        String pattern = "^[0-9]*$";
        String input = "123456789";
        boolean regex = Pattern.matches(pattern, input);

        // when

        Pattern pattern2 = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");

        Matcher matcher = pattern2.matcher("000-111-222");

        // then
        Assertions.assertTrue(regex);
        Assertions.assertTrue(matcher.find());


    }

    @Test
    @DisplayName("기본적인 스트림사용")
    public void 스트림() {
        Assertions.assertTrue(Stream.of(1, 2, 3, 4, 5)
                                    .mapToInt(i -> i - 1)
                                    .sum() > 5, () -> "5보다 큰가");


    }

    @Test
    public void 스트림필터_테스트() {

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple(250, Color.GREEN));
        appleList.add(new Apple(155, Color.GREEN));
        appleList.add(new Apple(170, Color.GREEN));
        appleList.add(new Apple(160, Color.GREEN));
        appleList.add(new Apple(200, Color.RED));
        appleList.add(new Apple(110, Color.RED));
        appleList.add(new Apple(130, Color.RED));
        appleList.add(new Apple(200, Color.RED));
        appleList.add(new Apple(150, Color.RED));
        appleList.add(new Apple(180, Color.RED));


        List<Apple> inventory = new ArrayList<>();

        inventory = appleList.parallelStream()

                             .filter(apple -> apple.getWeight() > 100 && apple.getColor()
                                                                              .equals(Color.GREEN))
                             .collect(Collectors.toList());


        Assertions.assertTrue(inventory.stream()
                                       .allMatch(a -> a.getColor()
                                                       .equals(Color.GREEN)));
        Assertions.assertTrue(inventory.stream()
                                       .allMatch(a -> a.getWeight() > 100));


    }

    @Test
    @DisplayName("map테스트")
    public void 스트림map_테스트() {

        List<Apple> appleList = new ArrayList<>();


        appleList.add(new Apple(250, Color.GREEN));

        appleList.add(new Apple(155, Color.GREEN));

        appleList.add(new Apple(170, Color.GREEN));

        appleList.add(new Apple(160, Color.GREEN));

        appleList.add(new Apple(200, Color.RED));

        appleList.add(new Apple(110, Color.RED));

        appleList.add(new Apple(130, Color.RED));

        appleList.add(new Apple(200, Color.RED));

        appleList.add(new Apple(150, Color.RED));

        appleList.add(new Apple(180, Color.RED));

        appleList.add(new Apple(180, Color.YELLOW));


/*        for (Apple apple : appleList) {
            ArrayList<Apple> result = new ArrayList<>();
            if (apple.getColor()
                     .equals(Color.RED)) {
                result.add(apple)
            ;
            }
            return result;
        }*/
        int sum = appleList.stream()
                           .filter(a -> a.getColor()
                                         .equals(Color.RED))
                           .mapToInt(apple -> apple.getWeight())
                           .sum();

        Assertions.assertTrue(sum == 180);
    }

    @Test
    public void basicTest() throws Exception {
        //given
        process(() -> {
            System.out.println("Hello");
        });

        //when

        //then
    }

    public static void process(Runnable runnable) {
        runnable.run();
        fetch();

    }

    public static Callable<String> fetch() {
        return () -> "example";
    }

}
