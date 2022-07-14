package reflection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception{

        Member member = new Member("kim",20,"kmr2644@gmail.com");
        String stringObject = "some-string";
        Map<String, Integer> map = new HashMap<>();
        //Class 객체를 얻는 3가지방법
        //1
        Class<? extends Member> aClass = member.getClass();
        Class<? extends String> aClass1 = stringObject.getClass();
        Class<? extends Map> aClass2 = map.getClass(); //Class 는 인터페이스인 Map 이 아닌 구현체인 HashMap를 가르킴(변수의 런타임 타입이기 때문)

        //2
        //인스턴스 생성x  원사타입도 클래스 객체를 얻을 수 있다. 메서드 매개변수나 클래스 맴버를 확인 할 때(원시타입) 이것을 이용해서 클래스타입을 만들어냄
        Class<Member> memberClass = Member.class;
        Class<Integer> integerClass = int.class;
        Class<Boolean> booleanClass = boolean.class;

        //3
        //동적으로 위치를 정한다
        Class<?> aClass3 = Class.forName("java.lang.String");
        Class<?> aClass4 = Class.forName("reflection.Member"); // 내부클래스에 접근시 . 대신 $ 사용

        //Class<?> aBoolean = Class.forName("boolean"); //Runtime Error!
        //첫번째 방법과 마찬가지로 원사타입을 인자로 넣을시 ClassNotFoundException 발생, 경로설정에 주의해야한다

        //런타임에러에 취약하기 때문에 3가지 방법중 가장 위험함
        //그래도 사용되는 몇가지 경우 : 인스턴스를 확인하거나 만들려는 타입이 자바코드가 아닌 외부 설정에서 가져올 때(xml 서블릿,빈설정파일 등),
        //우리가 확인 할려는 클래스가 프로젝트에 없 코드를 컴파일할 때 클래스가 없으면 forName() 을 사 클래스를 불러와서 사용하는 앱과 분리해서 별도로 라이브러리를
        //구축하는 경우 매우편리

        Class<String> stringClass = String.class;
        HashMap<String, Integer> hashMap = new HashMap<>();
        Class<? extends HashMap> hashMapClass = hashMap.getClass();
        Class<?> squareClass = Class.forName("reflection.Main$Square");
        printClassInfo(stringClass,hashMapClass,squareClass);


        var cicleObjcet = new Drawble() {
            @Override
            public int getNumberOfCorner() {
                return 0;
            }
        };


        printClassInfo(Collection.class, boolean.class, int[][].class, Color.class,cicleObjcet.getClass());
    }

    private static void printClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.println(String.format("class name : %s, class package name : %s",
                    clazz.getSimpleName(), clazz.getPackageName()));
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                System.out.println(String.format("class %s implement %s",
                        clazz.getSimpleName(), anInterface.getSimpleName()));

            }
            System.out.println("Is array: " + clazz.isArray());
            System.out.println("Is primitive: " + clazz.isPrimitive());
            System.out.println("Is enum: " + clazz.isEnum());
            System.out.println("Is interface: " + clazz.isInterface());
            System.out.println("Is anonymous: " + clazz.isAnonymousClass());
            System.out.println("superClass" + clazz.getSuperclass().getSimpleName());
            System.out.println();
            System.out.println();
        }
    }
    private static class Square implements Drawble {

        @Override
        public int getNumberOfCorner() {
            return 0;
        }
    }
    private static interface Drawble {
        int getNumberOfCorner();
    }

    private enum Color {
        RED,
        BLUE,
        GREEN
    }

}
