package ch11;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class Ch11Test {


    @Test
    public void 기본_옵셔널_생성() throws Exception {
        //슈뢰딩거의 옵셔널 ㄷ
        //given
        //when
        //then
        //빈 Optional 생성
        Optional<Car> car = Optional.empty();
        Assertions.assertEquals(car.isEmpty(), true);

        //null 객체 인자로 넣을시 NPE 발생
        Car car1 = null;
        Assertions.assertThrows(RuntimeException.class, () ->Optional.of(car1));

        //Optional.ofNullable null 객체 인자로 넣을시 빈 Optional 반환 만약 있으면 ==of(value)
        Optional<Car> optionalCar = Optional.ofNullable(car1);
        System.out.println("optionalCar = " + optionalCar);
        Assertions.assertEquals(optionalCar.isEmpty(), true);


    }

    @Test
    public void 옵셔널추출() throws Exception{
     //맵으로 Optional 값 추출하고 변환하기

        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();

        insurance.name = "COMPANY";

        person.setCar(car);
        car.setInsurance(insurance);


        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        //만약 Optional 이 empty 일시 아무것도 하지않음
        Optional<String> name = optionalInsurance.map(Insurance::getName);

        Assertions.assertEquals(name.stream()
                                    .collect(Collectors.joining()), "COMPANY");

        //
        Insurance insurance2 = null;

        Optional<Insurance> optionalInsurance2 = Optional.ofNullable(insurance2);
        //만약 Optional 이 empty 일시 아무것도 하지않음
        Optional<String> name2 = optionalInsurance2.map(Insurance::getName);
        Assertions.assertEquals(name2.stream()
                                    .collect(Collectors.joining()), "");
        Assertions.assertEquals(optionalInsurance2.isEmpty(),true);


        //person.getCar().getInsurance().getName(); =>
        Optional<Person> optionalPerson = Optional.of(person);

        String n = optionalPerson.flatMap(Person::getCar)
                                 .flatMap(Car::getInsurance)
                                 .map(Insurance::getName)
                                 .orElse("UnKnown");

        Assertions.assertEquals(n,"COMPANY");


    }

}
