public static class Task { 
    private final String description;
    private final int id;
    public Task(String description, int id) {
        this.description = description;
        this.id = id;
    }
    String getDescription() {
        return this.description;
    }
    int getId() {
        return this.id;
    }
}

public static class ToDoList {
    private final ArrayList<Task> tasks;
    private final ArrayList<Boolean> completed;
    private int nextId = 0;
    public ToDoList() {
        this.tasks = new ArrayList<>();
        this.completed = new ArrayList<>();
    }
    public void addTask(Task getTask) {
        tasks.add(getTask);
        completed.add(false);
        nextId++;
    }
    public void markAsCompleted(int id) {
        for(Task findDefiniteTask : tasks) {
            if(id == findDefiniteTask.getId()) {
                completed.set(tasks.indexOf(findDefiniteTask),true);
            }
        }
    }
    public void deleteTask(int id) {
        while(nextId > 0) {
            tasks.removeIf(findDefiniteTask -> id == findDefiniteTask.getId());
            nextId--;
        }
    }
    public void printAllTasks() {
        for(int i = 0; i < nextId; ++i) {
            System.out.println("[" + this.tasks.get(i).getId() + "]" + " " + this.tasks.get(i).getDescription() + " " + "[" + this.completed.get(i) + "]");
        }
    }
}

# пришлось делать без класса ToDoApp, поскольку были проблемы с переименованием файла для исходника кода на Java под main функцию

void start(ToDoList getListTasks) {
    String[] options = new String[5];
    options[0] = "Показать все задачи";
    options[1] = "Добавить задачу";
    options[2] = "Отметить задачу как выполненную";
    options[3] = "Удалить задачу";
    options[4] = "Выход";

    int index = 0, nextId = 0;

    System.out.println("Мой список задач");

    for (String every : options) {
        index++;
        System.out.println(index + "." + " " + every);
    }

    Scanner chooseOption = new Scanner(System.in);
    Scanner operation = new Scanner(System.in);
    System.out.println("Выберите действие: ");
    int option = chooseOption.nextInt();

    if (option == 1) {
        getListTasks.printAllTasks();
        start(getListTasks);
    } else if(option == 2) {
        System.out.println("Введите описание задачи: ");
        String description = operation.nextLine();

        nextId++;
        Task createTask = new Task(description,nextId);
        getListTasks.addTask(createTask);
        System.out.println("Задача успешно добавлена!");
        start(getListTasks);
    } else if(option == 3) {
        System.out.println("Введите ID задачи: ");
        int idOfTask = operation.nextInt();

        getListTasks.markAsCompleted(idOfTask);
        System.out.println("Задача помечена как выполненная!");
        start(getListTasks);
    } else if(option == 4) {
        System.out.println("Введите ID задачи: ");
        int idOfTask = operation.nextInt();

        getListTasks.deleteTask(idOfTask);
        System.out.println("Задача успешно удалена!");
        start(getListTasks);
    } else {
        System.out.println("Завершение программы");
        System.exit(0);
    }
}

void main() {
    ToDoList listTasks = new ToDoList();
    start(listTasks);
}
