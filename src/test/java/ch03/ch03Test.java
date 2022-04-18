package ch03;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    public void 필터테스트() throws Exception{
        //given
        String str1 = "hello my world!";
        String[] split = str1.split("");
        List<String> listOfStrings = Arrays.asList(split);
        //when
        List<String> result = filter(listOfStrings, (String s) -> !s.equals(" "));
        //then
        assertEquals(result.stream().collect(Collectors.joining()),"hellomyworld!");
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
    public String process(BufferedReaderProcessor p) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/kim/Desktop/study/Java8/src/test/java/ch03/test.txt"))) {
            return p.process(bufferedReader);
        }
    }

}