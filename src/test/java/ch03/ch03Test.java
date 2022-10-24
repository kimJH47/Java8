package ch03;

import java8.ch00.Apple;
import java8.ch00.Color;
import java8.ch03.BufferedReaderProcessor;
import java8.ch03.TriFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ch03Test {

    @Test
    public void 함수형인터페이스_테스트() throws Exception{
        //given
        String line1 = process((BufferedReader br) -> br.readLine());
        String line2 = process((BufferedReader br) -> br.readLine() + br.readLine());
        //when
        //then
        assertEquals(line1, "test line 1");
        assertEquals(line2, "test line 1test line 2");


    }

    @Test
    public void Predicate_테스트() throws Exception{
        //given
        String str1 = "hello my world!";
        String[] split = str1.split("");
        List<String> listOfStrings = Arrays.asList(split);
        //when
        List<String> result = filter(listOfStrings, (String s) -> !s.equals(" "));
        //then
        assertEquals(result.stream().collect(Collectors.joining()),"hellomyworld!");
    }
    @Test
    public void Consumer_테스트() throws Exception{
        //given
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println("i = " + i));
        //when
        //then
    }

    @Test
    public void Function_테스트() throws Exception{
        //given
        List<Integer> result = map(Arrays.asList(1, 2, 3, 4, 5, 6), (Integer i) -> i + 10);
        //when
        int sum = result.stream()
                        .mapToInt(i -> i)
                        .sum();
        //then
        assertEquals(sum,81);

    }
    @Test
    public void 메서드참조_테스트() throws Exception{
        //given
        BiFunction<Color, Integer, Apple> c3 = Apple::new;
        //when
        Apple apply = c3.apply(Color.GREEN, 100);
        //then
        assertEquals(apply.getColor(), Color.GREEN);
    }
    @Test
    public void 메서드참조_테스트_응용() throws Exception{
        //given
        Map<String, BiFunction<Color,Integer,Apple>> map = new HashMap<>();
        //when
        map.put("apple1", Apple::new);
        map.put("apple2", Apple::new);
        Apple apple1 = map.get("apple1")
                         .apply(Color.RED,100);
        Apple apple2 = map.get("apple2")
                         .apply(Color.RED,200);
        //then
        assertEquals(apple1.getWeight(),100);
        assertEquals(apple2.getWeight(),200);


    }
    @Test
    public void 메서드참조_테스트_파라미터가_3개인_함수형인터페이스() throws Exception{
        //given
        Map<String, TriFunction<Color,Integer,Double,Apple>> map = new HashMap<>();
        //when
        map.put("apple1", Apple::new);
        map.put("apple2", Apple::new);
        Apple apple1 = map.get("apple1")
                          .apply(Color.RED,100,1.5);
        Apple apple2 = map.get("apple2")
                          .apply(Color.RED,200,2.9);
        //then
        assertEquals(apple1.getWeight(),100);
        assertEquals(apple2.getBrix(),2.9);


    }
    public <T, R> List<R> map(List<T> list, Function<T, R>function) {
        //T -> 매핑 전 타입
        //R -> 매핑 후 타입
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public<T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
    public String process(BufferedReaderProcessor p) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/kim/Desktop/study/Java8/src/test/java/ch03/test.txt"))) {
            return p.process(bufferedReader);
        }
    }


}