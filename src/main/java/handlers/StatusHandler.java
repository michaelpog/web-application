package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class StatusHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        sendResponse(exchange, 200, "Running");
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

}
