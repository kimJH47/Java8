package reflection.constructor.web;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        initConfiguration();
        ServerConfiguration getInstance = ServerConfiguration.getGetInstance();
        System.out.println(getInstance.getServerAddress());
        WebServer webServer = new WebServer();
        webServer.startServer();

    }

    public static void initConfiguration() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ServerConfiguration> declaredConstructor = ServerConfiguration.class.getDeclaredConstructor(int.class, String.class);
        declaredConstructor.setAccessible(true); //private 생성자 접근을 위한 설정, 이기능은 내부 생성자나 우리가 소유하지 않은 클래스에서 의도적으로 제한된 생성자에는 사용을 자제해야함.
        declaredConstructor.newInstance(8083, "Good day!");

    }
}
