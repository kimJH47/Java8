package reflection.constructor.web;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;

//제한된 생성자 접근
public class ServerConfiguration {
    private final InetSocketAddress serverAddress;
    private final String greetingMessage;
    private static ServerConfiguration serverConfigurationInstance;

    private ServerConfiguration(int port, String greetingMessage) {
        this.greetingMessage = greetingMessage;
        this.serverAddress = new InetSocketAddress("localhost", port);
        if (serverConfigurationInstance == null) {
            serverConfigurationInstance = this;
        }
    }

    public static ServerConfiguration getGetInstance() {
        return serverConfigurationInstance;
    }

    public InetSocketAddress getServerAddress() {
        return serverAddress;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }
}



