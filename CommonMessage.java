// Общий класс для MailMessage и Salary
public class CommonMessage<T> implements IMappingMessage<T> {
    private String from;
    private String to;
    private T content;

    public CommonMessage(String from, String to, T content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public T getContent() {
        return content;
    }
}
