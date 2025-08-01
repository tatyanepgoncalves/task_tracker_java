import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class TaskManager {
  private static final String FILE_NAME = "tasks.json";

  public static void addTask(String description) {
    List<Task> tasks = readTasks();

    int nextId = tasks.size() == 0 ? 1 : tasks.get(tasks.size() - 1).id + 1;

    Task newTask = new Task(nextId, description);
    tasks.add(newTask);
    saveTasks(tasks);

    System.out.println("Task adicionada com sucesso (ID: " + newTask.id + ")");
  }

  private static List<Task> readTasks() {
    List<Task> tasks = new ArrayList<>();

    try {
      File file = new File(FILE_NAME);
      if (!file.exists()) {
        file.createNewFile();
        FileWriter writer = new FileWriter(FILE_NAME);
        writer.write("[]");
        writer.close();
      }

      BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line  = reader.readLine()) != null) {
        sb.append(line);
      }
      
      reader.close();
      String json = sb.toString();

      if (json == null || json.equals("[]")) return tasks;

      String[] entries = json.substring(1, json.length() - 1).split("\\},\\{");


      for (String entry : entries) {
        String jsonEntry = entry;
        if (!entry.startsWith("{")) jsonEntry =  "{" + entry;
        if (!entry.endsWith("}")) jsonEntry = entry + "}";

        String[] fields = jsonEntry.replace("{", "").replace("}", "").split(",");
        int id = 0;
        String description = "", status = "", createdAt = "", updatedAt = "";

        for (String field : fields) {
          String[] keyValue = field.split(":", 2);
          String key = keyValue[0].replace("\"", "").trim();
          String value = keyValue[1].replace("\"", "").trim();

          switch (key) {
            case "id": id = Integer.parseInt(value); break; 
            case "description": description = value; break;
            case "status": status = value; break;
            case "createdAt": createdAt = value; break;
            case "updatedAt": updatedAt = value; break;
          }

        }

        Task task = new Task(id, description);
        task.status = status;
        task.createdAt = createdAt;
        task.updatedAt = updatedAt;
        
        tasks.add(task);
      }
    } catch (IOException e) {
      System.out.println("Erro ao ler tarefas: " + e.getMessage());
    }

    return tasks;
  } 

  private static void saveTasks(List<Task> tasks) {
    try {
      FileWriter writer = new FileWriter(FILE_NAME);
      writer.write("[");
      for (int i = 0; i < tasks.size(); i++) {
        writer.write(tasks.get(i).toJSON());
        if (i != tasks.size() - 1) writer.write(",");
      }
      writer.write("]");
      writer.close();
    } catch (IOException e ) {
      System.out.println("Erro ao salvar tarefas: " + e.getMessage());
    }
  }

  public static void listAllTasks() {
    List<Task> tasks = readTasks();

    if (tasks.isEmpty()) {
      System.out.println("Nenhuma tarefa encontrada.");
      return;
    }

    for (Task task: tasks) {
      System.out.println("ID: " + task.id);
      System.out.println("Descrição: " + task.description);
      System.out.println("Status: " + task.status);
      System.out.println("Criada em: " + task.createdAt);
      System.out.println("Atualizada em: " + task.updatedAt);
      System.out.println("--------------------------");
    }

  }

  public static void listByStatus(String status) {
    List<Task> tasks = readTasks();
    boolean found = false;

    for (Task task : tasks) {
      if (task.status.equalsIgnoreCase(status)) {
        System.out.println("ID: " + task.id);
        System.out.println("Descrição: " + task.description);
        System.out.println("Status: " + task.status);
        System.out.println("Criada em: " + task.createdAt);
        System.out.println("Atualizada em: " + task.updatedAt);
        System.out.println("--------------------------");
        found = true;
      }
    }

    if (!found) {
      System.out.println("Nenhuma tarefa com status: " + status);
    }
  }
}