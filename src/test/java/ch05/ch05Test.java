package ch05;

import ch00.stream.Dish;
import ch00.stream.DishType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ch05Test {



    @Test
    public void flatMap() throws Exception{
        //
        //given
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        //when
        //then
        words.stream()
             .map(word -> word.split(""))
             .forEach(strings -> System.out.println("strings = " + strings));
        /**
         *  map 을 사용햇을 때 기대한 값은 Stream<String> 이엿지만 spilt 이 반환값은 Stream<String[]> 으로 배열을 반환하였다
         */
        //map 과 Arrays.stream 활용
        List<Stream<String>> collect = words.stream()
                                            .map(word -> word.split(""))
                                            .map(array -> Arrays.stream(array))
                                            .distinct()
                                            .collect(Collectors.toList());
        /**
         *  결국 이것도 List<Stream<String> 이 만들어 지면서 원하는 결과를 얻지못한다.
         */
        //flatMap 이용해서 해결
        List<String> stringList = words.stream()
                                     .map(word -> word.split(""))
                                     .flatMap(Arrays::stream)
                                     .distinct()//중복제거
                                     .collect(Collectors.toList());

        //flatMap 은 각 배열(컬랙션)의 스트림이 아니라 스트림이 가지고 있는 컨탠츠(요소)와 매핑한다.
        for (String s : stringList) {
            System.out.print(s);
        }
        stringList.stream()
                  .allMatch(s -> s.equals(""));
    }
    @Test
    public void flatMap_ex1() throws Exception{
        //given
        // 두개의 리스트의 숫자 쌍 반환
        List<Integer> list1= new ArrayList<>();
        List<Integer> list2= new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list2.add(3);
        list2.add(4);
        //when
        List<int[]> collect = list1.stream()
                                   .flatMap(integer -> list2.stream()
                                                            .map(integer2 -> new int[]{integer, integer2}))
                                   .collect(Collectors.toList());
        //then
        for (int[] ints : collect) {
            System.out.println(Arrays.toString(ints));
        }
    }

    @Test
    public void flatMap_ex2() throws Exception{
        //given
        // 두개의 리스트의 숫자 쌍 반환
        List<Integer> list1= new ArrayList<>();
        List<Integer> list2= new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list2.add(3);
        list2.add(4);

        //when
        List<int[]> collect = list1.stream()
                                   .flatMap(integer -> list2.stream()
                                                            .filter(integer1 -> (integer+integer1)%3==0)
                                                            .map(integer2 -> new int[]{integer, integer2}))
                                   .collect(Collectors.toList());
        //then
        for (int[] ints : collect) {
            System.out.println(Arrays.toString(ints));
        }
    }

    @Test
    public void 요소검색() throws Exception{
        //given
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("pork", true, 800, DishType.MEAT));
        menu.add(new Dish("beef", false, 700, DishType.MEAT));
        menu.add(new Dish("chiken", false, 400, DishType.MEAT));
        menu.add(new Dish("french fries", true, 530, DishType.OTHER));
        menu.add(new Dish("rice", true, 350, DishType.OTHER));
        menu.add(new Dish("season fruit", true, 120, DishType.OTHER));
        menu.add(new Dish("pizza", true, 550, DishType.OTHER));
        menu.add(new Dish("prawns", false, 300, DishType.FISH));
        menu.add(new Dish("salmon", false, 450, DishType.FISH));


        //when
        //then
        menu.stream()
            .filter(Dish::isVegetarian)
            .findAny()
            .ifPresent(dish -> System.out.println("dish = " + dish.getName()));

        menu.stream()
            .filter(Dish::isVegetarian)
            .findFirst()
            .ifPresent(dish -> System.out.println("dish = " + dish.getName()));

        //findAny 와 findFirst 를 두개다 사용하는 이유는 병렬처리 떄문이다. 병렬처리에서는 첫번째순서를 정하기 애매하기 때문에 두가지를 적절히 사용한다.(순서가 안중요하면 findAny)

    }
    @Test
    public void 리듀싱() throws Exception{
        //given
        List<Integer> numbers = Arrays.asList(4,5,6,7);
        //when
        Integer sum = numbers.stream()
                                .reduce(0,Integer::sum);
        Optional<Integer> max = numbers.stream()
                                       .reduce(Integer::max);

        Optional<Integer> min = numbers.stream()
                                       .reduce(Integer::min);
        //then

        System.out.println("sum = " + sum);
        System.out.println("max = " + max.orElseThrow());
        System.out.println("min = " + min.orElseThrow());

        //기존 sum 변수를 사용해서 값을 구하는것은 쉽게 병렬화가 어렵다. reduce 사용하면 내부반복이 추상화 되면서 병렬로 reuduce 될수 있게 된다.
    }
    @Test
    public void 리듀싱_ex1() throws Exception{

        //given
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("pork", true, 800, DishType.MEAT));
        menu.add(new Dish("beef", false, 700, DishType.MEAT));
        menu.add(new Dish("chiken", false, 400, DishType.MEAT));
        menu.add(new Dish("french fries", true, 530, DishType.OTHER));
        menu.add(new Dish("rice", true, 350, DishType.OTHER));
        menu.add(new Dish("season fruit", true, 120, DishType.OTHER));
        menu.add(new Dish("pizza", true, 550, DishType.OTHER));
        menu.add(new Dish("prawns", false, 300, DishType.FISH));
        menu.add(new Dish("salmon", false, 450, DishType.FISH));
        //when
        Integer sum = menu.stream()
                             .map(dish -> 1)
                             .reduce(0, Integer::sum);
        //then
        System.out.println("sum = " + sum);
    }
    @Test
    public void ex() throws Exception{
        //given
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(brian, 2011, 300));
        transactions.add(new Transaction(raoul, 2012, 1000));
        transactions.add(new Transaction(raoul, 2011, 400));
        transactions.add(new Transaction(mario, 2012, 710));
        transactions.add(new Transaction(mario, 2012, 700));
        transactions.add(new Transaction(alan, 2012, 950));

        //when
        //then
        List<Transaction> ex1 = transactions.stream()
                                                .filter(transaction -> transaction.getYear() == 2011)
                                                .sorted(Comparator.comparing(Transaction::getValue))
                                                .collect(Collectors.toList());
        for (Transaction transaction : ex1) {
            System.out.println("transaction = " + transaction);
        }
        List<String> ex2 = transactions.stream()
                                           .map(Transaction::getTrader)
                                           .map(Trader::getCity)
                                           .distinct()
                                           .collect(Collectors.toList());
        for (String s : ex2) {
            System.out.println("city = " + s);
        }
        List<Trader> ex3 = transactions.stream()
                                       .map(Transaction::getTrader)
                                       .filter(trader -> trader.getCity()
                                                               .equals("Cambridge"))
                                       .distinct()
                                       .sorted(Comparator.comparing(Trader::getName))
                                       .collect(Collectors.toList());
        for (Trader s : ex3) {
            System.out.println("name = " + s);
        }
        String str = transactions.stream()
                                       .map(Transaction::getTrader)
                                       .map(Trader::getName)
                                       .distinct()
                                       .sorted()
                                       .collect(Collectors.joining());
        System.out.println("str = " + str);

        boolean milan = transactions.stream()
                                    .anyMatch(transaction -> transaction.getTrader()
                                                                        .getCity()
                                                                        .equals("Milan"));
        System.out.println("milan = " + milan);

        List<Integer> ex6 = transactions.stream()
                                              .filter(transaction -> transaction.getTrader()
                                                                                .getCity()
                                                                                .equals("Cambridge"))
                                              .map(Transaction::getValue)
                                              .collect(Collectors.toList());

        for (Integer integer : ex6) {
            System.out.println("value = " + integer);
        }
        Integer max= transactions.stream()
                                     .map(Transaction::getValue)
                                     .reduce(0, Integer::max);
        System.out.println("max = " + max);

        Optional<Transaction> min = transactions.stream()
                                                .min(Comparator.comparing(Transaction::getValue));
        System.out.println("min = " + min.get());
    }

}
