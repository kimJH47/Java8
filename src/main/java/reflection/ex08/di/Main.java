package reflection.ex08.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //제한된 클래스의 인스턴스화 와 자동 의존성 주입 구현


    /**
     * 1-1.Package-private(protected) classes Instantiation using Constructor Class
     *  public 클래스는 모두가 접근 가능하고 모든클래스에서 볼 수 있음(의존가능)
     *  접근제어자가 없다면 Package-private 클래스로 간주되고 같은 패키지 내에서만 접근이 가능함 이는 패키지 외부 사용자로부터 InternalClass(내부 클래스)
     *  를 제한 하는 가장 좋은 방법으로 패키지 사용자에게 사용 할 수 있는 클래스를 명확하게 나태내고 내부 구현상세 사항에 대해서는 헷갈려 할 필요없음.
     *  하지만 Package-private 도 패키지 외부에서 꼭 접근 해야 하는 경우가 있음
     *  -Reading(읽기 엑세스)
     *  -Initializing(클래스 분석)
     *  -인스턴스화를 위해 외부 라이브러리를 사용 할 때, 외부 라이브러리의 코드는 패키지 밖에 있으므로 Reflection 없이는 불가능함.
     *
     * private 생성자를 사용 할 때 처럼 Constructor.setAccessible(true) 를 통해서 접근이 가능하다.
     *
     * 1-2.External Package-private classes access use case
     * 외부 라이브러리를 사용할 때 Package-private class 를 사용해야하면 Reflection 을 이용해서 객체를 분석하거나 인스턴스화 할 수 있다.
     *
     * 2.Dependency Injection Implementation
     * 애플리케이션 시작 시 클래스를 자동 생성하는 경우 Injector 는 제한된 Package-private 클래스를 모든 생성하고 연결하기 위해 분석할 접근 권한이 존재햐아함
     */
    public static void main(String[] args) {

    }

    public static <T> T createObjectRecursively(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<?> constructor = getFirstConstructor(clazz);
        List<Object> constructorArguments = new ArrayList<>();
        for (Class<?> argumentType :
                constructor.getParameterTypes()) {
            Object objectRecursively = createObjectRecursively(argumentType);
            constructorArguments.add(objectRecursively);
        }
        constructor.setAccessible(true);
        return (T) constructor.newInstance(constructorArguments.toArray());

    }
    public static Constructor<?> getFirstConstructor(Class<?> clazz) {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        if (declaredConstructors.length == 0) {
            throw new IllegalArgumentException(String.format("%s 에 생성자가 존재하지 않습니다", clazz.getSimpleName()));
        }
        return declaredConstructors[0];
    }
}
