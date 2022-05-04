package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Config;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class ReleaseVersionHandler implements HttpHandler {
    private final Config config;

    public ReleaseVersionHandler(String pathToConfigFile) {
        config = parseConfigFile(pathToConfigFile);
    }

    private static Config parseConfigFile(String pathToConfigFile) {
        Path path = Paths.get(pathToConfigFile);
        try {
            return createConfigObject(Config.class, path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed in parsing the config file");
            System.exit(1);
        }
        return null;
    }

    public static <T> T createConfigObject(Class<T> clazz, Path filePath) throws IOException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {

        Scanner scanner = new Scanner(filePath);

        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T configInstance = (T) constructor.newInstance();

        while (scanner.hasNextLine()) {
            String configLine = scanner.nextLine();

            String[] nameValuePair = configLine.split("=");

            if (nameValuePair.length != 2) {
                continue;
            }

            String propertyName = nameValuePair[0];
            String propertyValue = nameValuePair[1];

            Field field;
            try {
                field = clazz.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                System.out.println(String.format("Property name : %s is unsupported",
                        propertyName));
                continue;
            }

            field.setAccessible(true);

            Object parsedValue = parseValue(field.getType(), propertyValue);

            field.set(configInstance, parsedValue);
        }

        return configInstance;
    }

    private static Object parseValue(Class<?> type, String value) {
        if (type.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(short.class)) {
            return Short.parseShort(value);
        } else if (type.equals(long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(String.class)) {
            return value;
        }
        throw new RuntimeException(String.format("Type : %s unsupported", type.getTypeName()));
    }

    /**
     * Sends back an HTTP response to the server
     *
     * @param exchange     - Object indicating the exchange of HTTP request/response between
     *                     client/server
     * @param statusCode   - The HTTP response code to be included in the HTTP response
     * @param responseBody - The body payload of the HTTP response
     *                     <p>DO NOT MODIFY THIS METHOD
     */
    private static void sendResponse(HttpExchange exchange,
                                     int statusCode,
                                     String responseBody) throws IOException {
        if (!responseBody.isBlank() && !responseBody.endsWith("\n")) {
            responseBody += "\n";
        }
        exchange.sendResponseHeaders(statusCode, responseBody.getBytes().length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBody.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        sendResponse(exchange, 200, getEnvironmentAndVersion() );
    }

    public String getEnvironmentAndVersion() {
        return String.format("Environment: %s, Version: %s",
                config.getEnvironment().toUpperCase(Locale.ROOT),
                config.getVersion());
    }
}
