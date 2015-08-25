import java.util.List;
import org.sql2o.*;

public class Categories {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Categories(String name) {
    this.name = name;
  }

  public static List<Categories> all() {
    String sql = "SELECT id, name FROM categories";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Categories.class);
    }
  }

  @Override
  public boolean equals(Object otherCategories){
    if (!(otherCategories instanceof Categories)) {
      return false;
    } else {
      Categories newCategories = (Categories) otherCategories;
      return this.getName().equals(newCategories.getName()) &&
        this.getId() == newCategories.getId();
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO categories(name) VALUES (:name)";
        this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .executeUpdate()
         .getKey();
      }
    }
    public static Categories find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM categories where id=:id";
        Categories categories = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Categories.class);
        return categories;
      }
    }

}
