package com.ashish.qa.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public final class LocalApiServer {
    private static HttpServer server;
    private LocalApiServer() {}

    public static synchronized int start() throws IOException {
        if (server != null) return server.getAddress().getPort();
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8089), 0);
        server.createContext("/health", exchange ->
            respond(exchange, 200, "{\"status\":\"UP\"}"));
        server.createContext("/users/1", exchange ->
            respond(exchange, 200, "{\"id\":1,\"name\":\"Ashish Demo\",\"role\":\"QA\"}"));
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        return server.getAddress().getPort();
    }

    private static void respond(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("Cache-Control", "no-store");
        exchange.sendResponseHeaders(status, bytes.length);
        try (var output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }

    public static synchronized void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
    }
}
