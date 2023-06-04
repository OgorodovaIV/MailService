// Интерфейс, для составления списка сообщений по получателю
public interface IMappingMessage<T> {
    String getTo();

    T getContent();
}
