import com.sun.net.httpserver.HttpServer;
import handlers.ReleaseVersionHandler;
import handlers.StatusHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebApplication {
    private static final String RELEASE_VERSION = "/version";
    private static final String STATUS = "/status";

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: java -jar /path/to/app.jar /path/to/config.cfg");
            System.exit(1);
        }
        int port = 8080;
        HttpServer server = HttpServer.create(
                new InetSocketAddress("localhost", port),
                0);

        server.createContext(RELEASE_VERSION, new ReleaseVersionHandler(args[0]));
        server.createContext(STATUS, new StatusHandler());
        System.out.println("Server is running and listening on port " + port);
        server.start();
    }
}
