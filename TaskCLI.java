public class TaskCLI {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Uso: java TaskCLI <comando> [argumentos]");
      return;
    }

    String command = args[0];

    switch (command) {
      case "add":
        if (args.length < 2) {
           System.out.println("Uso: java TaskCLI add \"descrição da tarefa\"");
           return;
        }
        String description = args[1];
        TaskManager.addTask(description);
        break;

      case "list":
        if (args.length == 1) {
          TaskManager.listAllTasks();
        } else {
          String statusFilter = args[1];
          TaskManager.listByStatus(statusFilter);
        }
        break;
      
      case "update":
        if (args.length < 3) {
          System.out.println("Uso: java TaskCLI update <id> \"nova descrição\"");
          return;
        }

        try {
          int id = Integer.parseInt(args[1]);
          String newDescription = args[2];
          TaskManager.updatedTask(id, newDescription);
        } catch (NumberFormatException e) {
          System.out.println("ID inválido: deve ser um número inteiro.");
        }
        break;
        
      case "delete":
        if (args.length < 2) {
          System.out.println("Uso: java TaskCLI delete <id>.");
          return;
        }

        try {
          int id = Integer.parseInt(args[1]);
          TaskManager.deleteTask(id);
        } catch (NumberFormatException e) {
          System.out.println("ID inválido: deve ser um número inteiro.");
        }
        
        break;

      default:
        System.out.println("Comando desconhecido: " + command);
    }

  }
}