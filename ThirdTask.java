import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public static class Client {
    private final String name;
    private final int priority;

    public Client(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Имя клиента='" + name + "', Приоритет=" + priority + ")";
    }
}

public static class BankQueue {
    private final PriorityQueue<Client> queue;

    public BankQueue() {
        queue = new PriorityQueue<>();
    }

    public void addClient(Client client) {
        queue.offer(client);
        System.out.println("Клиент " + client.getName() + " добавлен в очередь.");
    }

    public Client serveClient() {
        return queue.poll();
    }

    public void displayQueue() {
        System.out.println(queue.isEmpty() ? "Очередь пуста." : "Текущая очередь: " + new ArrayList<>(queue));
    }

    public void moveToEnd(String clientName) {
        List<Client> tempQueue = new ArrayList<>(queue);
        tempQueue.removeIf(client -> !client.getName().equals(clientName));

        Client clientToMove = tempQueue.stream()
                .filter(client -> client.getName().equals(clientName))
                .findFirst()
                .orElse(null);

        if (clientToMove == null) {
            System.out.println("Клиент " + clientName + " не найден.");
            return;
        }

        tempQueue.add(clientToMove);
        queue.clear();
        queue.addAll(tempQueue);
        System.out.println("Клиент " + clientName + " перемещен в конец очереди.");
    }
}

void main() {
    BankQueue bankQueue = new BankQueue();

    bankQueue.addClient(new Client("Иван", 2));
    bankQueue.addClient(new Client("Мария", 1));
    bankQueue.addClient(new Client("Петр", 3));
    bankQueue.addClient(new Client("Анна", 2));

    bankQueue.displayQueue();

    while ((bankQueue.serveClient()) != null) {
        System.out.println("Обслуживается: " + bankQueue.serveClient());
    }

    bankQueue.displayQueue();
    bankQueue.moveToEnd("Иван");
    
}
