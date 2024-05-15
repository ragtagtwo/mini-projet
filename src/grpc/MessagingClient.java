package grpc;

package messaging;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MessagingClient {

    public static void main(String[] args) {
        // Créer un canal gRPC
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext() // Utiliser un canal non sécurisé pour simplifier
                .build();

        // Créer un stub pour le service de messagerie
        MessagingServiceGrpc.MessagingServiceBlockingStub stub = MessagingServiceGrpc.newBlockingStub(channel);

        // Tester l'envoi de messages
        testSendMessage(stub);

        // Tester la récupération de messages pour un utilisateur donné
        testGetMessagesForUser(stub);

        // Fermer le canal gRPC
        channel.shutdown();
    }

    private static void testSendMessage(MessagingServiceGrpc.MessagingServiceBlockingStub stub) {
        MessageRequest request = MessageRequest.newBuilder()
                .setSenderId("sender1")
                .setRecipientId("recipient1")
                .setMessage("Test message")
                .build();

        MessageResponse response = stub.sendMessage(request);
        System.out.println("SendMessage response: " + response.getStatus());
    }

    private static void testGetMessagesForUser(MessagingServiceGrpc.MessagingServiceBlockingStub stub) {
        UserMessagesRequest request = UserMessagesRequest.newBuilder()
                .setUserId("user1")
                .build();

        UserMessagesResponse response = stub.getMessagesForUser(request);
        System.out.println("GetMessagesForUser response:");
        for (UserMessage message : response.getMessages()) {
            System.out.println("Message ID: " + message.getMessageId());
            System.out.println("Sender ID: " + message.getSenderId());
            System.out.println("Message: " + message.getMessage());
            System.out.println("-------------");
        }
    }
}

