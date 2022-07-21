package reflection.constructor.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

public class WebServer {
    public void startServer() throws IOException {

        ServerConfiguration configuration = ServerConfiguration.getGetInstance();
        HttpServer httpServer = HttpServer.create(configuration.getServerAddress(), 0);
        httpServer.createContext("/greeting")
                  .setHandler(exchange -> {

                      String responseMsg = ServerConfiguration.getGetInstance()
                                                              .getGreetingMessage();

                      exchange.sendResponseHeaders(200, responseMsg.length());

                      OutputStream responseBody = exchange.getResponseBody();
                      responseBody.write(responseMsg.getBytes());

                      responseBody.flush();
                      responseBody.close();
                  });

        System.out.println(String.format("Starting server on address %s:%d",
                configuration.getServerAddress()
                             .getHostName(), configuration.getServerAddress()
                                                          .getPort()));
        httpServer.start();
    }
}
