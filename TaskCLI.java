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
    
      default:
        System.out.println("Comando desconhecido: " + command);
    }

  }
}