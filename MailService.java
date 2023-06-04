import java.util.*;
import java.util.function.Consumer;

// Реализация почтового сервиса
public class MailService<T> implements Consumer<IMappingMessage<T>> {
    private Map<String, List<T>> mailBox;

    // В случае поиска сообщений по пользователю, которому не было сообщений, возникает ошибка
    // Переопределим HashMap.get, чтоб в этом случае возвращался пустой список
    private static class EmptyGetHashMap<K,V> extends HashMap<K,V>{
        @Override
        public V get(Object key){
            V temp = super.get(key);
            if (temp == null) temp = (V)Collections.emptyList();
            return temp;
        }
    }

    public MailService() {
        mailBox = new EmptyGetHashMap<String, List<T>>();
    }

    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }

    @Override
    public void accept(IMappingMessage<T> msg) {
        // При обработке нового сообщения, проверяем наличие получателя в списке mailBox
        // Если отсутствует - добавляем, создаём новый список сообщений для него, в который добавляем текущее сообщение
        // Если же в списке он уже был, то текущее сообщение добавляем к списку сообщений для него
        mailBox.computeIfAbsent(msg.getTo(), k -> new ArrayList<T>()).add(msg.getContent());

        // При обработке нового сообщения, проверяем наличие получателя в списке mailBox и
        // если отсутствует - добавляем, создаём новый (пустой) список сообщений для него
        //mailBox.putIfAbsent(msg.getTo(), new ArrayList<T>());
        // Остаётся добавить текущее сообщение для данного получателя (теперь-то уж он точно есть в списке)
        //mailBox.get(msg.getTo()).add(msg.getContent());
    }
}
