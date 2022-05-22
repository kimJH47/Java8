package ch00;

import ch00.stream.Dish;
import ch00.stream.DishType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class StreamTest {


    List<Dish> menu = new ArrayList<>();

    @BeforeEach
    public void create() {
        menu.add(new Dish("pork", false, 800, DishType.MEAT));
        menu.add(new Dish("beef", false, 700, DishType.MEAT));
        menu.add(new Dish("chiken", false, 400, DishType.MEAT));
        menu.add(new Dish("french fries", true, 530, DishType.OTHER));
        menu.add(new Dish("rice", true, 350, DishType.OTHER));
        menu.add(new Dish("season fruit", true, 120, DishType.OTHER));
        menu.add(new Dish("pizza", true, 550, DishType.OTHER));
        menu.add(new Dish("prawns", false, 300, DishType.FISH));
        menu.add(new Dish("salmon", false, 450, DishType.FISH));

    }
    @Test
    public void 필터테스트() throws Exception{
        //given
        List<Dish> meats = menu.stream()
                               .filter(dish -> dish.getDishType()
                                                   .equals(DishType.MEAT))
                               .collect(Collectors.toList());
        //when
        //then
        assertThat(meats)
                .extracting(Dish::getDishType)
                .containsOnly(DishType.MEAT);
    }
    @Test
    public void 필터테스트_distinct() throws Exception{
        //distinct 는 hashcode() 와 equals() 를 비교해서 필퍼링한다

        List<Integer> collect = Arrays.asList(1, 1, 5, 6, 7, 9, 1, 5, 12)
                                      .stream()
                                      .filter(integer -> integer % 2 == 0)
                                      .distinct()
                                      .collect(Collectors.toList());

        //when
        //then
        assertThat(collect)
                .doesNotHaveDuplicates();
    }

    @Test
    public void map_테스트() {
        //given
        List<String> dishNameList = menu.stream()
                                        .map(Dish::getName)
                                        .collect(Collectors.toList());
        //when
        //then
        for (String s : dishNameList) {
            System.out.println("dishes " + s);
        }
    }

    @Test
    public void TAKEWHILE_테스트 () throws Exception{
        //given
        //자바9의 내용
        List<Dish> collect = menu.stream()
                                 .sorted(Comparator.comparing(Dish::getCalories))
                                 .takeWhile(dish -> dish.getCalories() < 400)
                                 .collect(Collectors.toList());
        //then
        //when

        for (Dish dish : collect) {
            System.out.println("dish.getName() = " + dish.getName());
            System.out.println("dish.getCalories() = " + dish.getCalories());

        }
    }
    @Test
    public void DROPWHILE_테스트() throws Exception{
        //given
        //자바9
        List<Dish> collect = menu.stream()
                                 .sorted(Comparator.comparing(Dish::getCalories))
                                 .dropWhile(dish -> dish.getCalories() < 320)
                                 .collect(Collectors.toList());
        //when
        //then
        //거짓이 되는 지점까지 발견된 요소들을 버린다. 그 후 작업을 중단하고 남은 요소를 전부 반환한다.
        for (Dish dish : collect) {
            System.out.println("dish.getName() = " + dish.getName());
            System.out.println("dish = " + dish.getCalories());
        }
    }
    @Test
    public void 스트림축소() throws Exception{
        //given
        List<Dish> collect = menu.stream()
                                 .limit(3)
                                 .collect(Collectors.toList());
        //when
        //then
        assertThat(collect.size() <=3).isTrue();
    }

}
