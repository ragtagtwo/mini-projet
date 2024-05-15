package grpc;

package messaging;

import io.grpc.stub.StreamObserver;

public class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        // Implementer la logique d'envoi de message ici
        System.out.println("Sending message from " + request.getSenderId() + " to " + request.getRecipientId() + ": " + request.getMessage());

        // Envoyer une réponse au client
        MessageResponse response = MessageResponse.newBuilder()
                .setStatus("Message sent successfully")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessagesForUser(UserMessagesRequest request, StreamObserver<UserMessagesResponse> responseObserver) {
        // Implémenter la logique de récupération de message ici
        String userId = request.getUserId();

        // Supposons que les messages sont récupérés à partir d'une source de données
        UserMessage message1 = UserMessage.newBuilder()
                .setMessageId("1")
                .setSenderId("sender1")
                .setMessage("Hello!")
                .build();
        UserMessage message2 = UserMessage.newBuilder()
                .setMessageId("2")
                .setSenderId("sender2")
                .setMessage("How are you?")
                .build();

        // Créer une réponse contenant les messages
        UserMessagesResponse response = UserMessagesResponse.newBuilder()
                .addMessages(message1)
                .addMessages(message2)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
