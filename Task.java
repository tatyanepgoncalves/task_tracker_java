import java.time.LocalDateTime;

public class Task {
  public int id;
  public String description;
  public TaskStatus status;
  public String createdAt;
  public String updatedAt;

  public Task(int id, String description) {
    this.id = id;
    this.description = description;
    this.status = TaskStatus.TODO;
    this.createdAt = LocalDateTime.now().toString();
    this.updatedAt = this.createdAt;
  }

  public void updateDescription(String newDescription) {
    this.description = newDescription;
    this.updatedAt = LocalDateTime.now().toString();
  }

  public void markDone() {
    this.status = TaskStatus.DONE;
    this.updatedAt = LocalDateTime.now().toString();
  }

  public void markInProgress() {
    this.status = TaskStatus.IN_PROGRESS;
    this.updatedAt = LocalDateTime.now().toString();
  }
  
  public String toJSON() {
    return String.format(
        "{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\",\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}",
        id, description, status.getValue(), createdAt, updatedAt
    );
  }


  public static Task fromJSON(String json) {
    return null;
  }
} 