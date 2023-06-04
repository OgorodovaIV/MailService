// Зарплатное (Integer'овое) сообщение
public class Salary extends CommonMessage<Integer> {
    public Salary(String from, String to, Integer content) {
        super(from, to, content);
    }
}
