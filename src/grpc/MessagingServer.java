package grpc;

package messaging;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MessagingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Créer un serveur gRPC
        Server server = ServerBuilder.forPort(50051)
                .addService(new MessagingServiceImpl())
                .build();

        // Démarrer le serveur
        server.start();
        System.out.println("Server started on port 50051");

        // Attente indéfinie
        server.awaitTermination();
    }
}
