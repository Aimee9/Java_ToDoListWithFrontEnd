import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;

public class Task {
  private int id;
  private String name;
  private Timestamp due_date;
  private String person;
  private int category_id;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Timestamp getDue_Date() {
    return due_date;
  }

  public String getPerson() {
    return person;
  }

  public int getCategory_Id() {
    return category_id;
  }

  public Task(String name) {
    this.name = name;
  }


  public static List<Task> all() {
    String sql = "SELECT id, name FROM tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }
  @Override
  public boolean equals(Object otherTask){
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      return this.getName().equals(newTask.getName()) &&
        this.getId() == newTask.getId();
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO tasks(name) VALUES (:name)";
        this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .executeUpdate()
         .getKey();
      }
    }

    public static Task find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM Tasks where id=:id";
        Task task = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Task.class);
        return task;
      }
    }
  }
