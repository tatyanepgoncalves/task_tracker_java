public enum TaskStatus {
  TODO("todo"),
  IN_PROGRESS("in-progress"),
  DONE("done");

  private final String value;

  TaskStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static TaskStatus fromString(String status) {
    for (TaskStatus s : TaskStatus.values()) {
      if (s.getValue().equalsIgnoreCase(status)) {
        return s;
      }
    }
    throw new IllegalArgumentException("Status inválido: " + status);
  }
}