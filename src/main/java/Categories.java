import java.util.List;
import org.sql2o.*;

public class Categories {
  private int id;
  private String type;

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Categories(String type) {
    this.type = type;
  }

  public static List<Categories> all() {
    String sql = "SELECT id, type FROM categories";
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
      return this.getType().equals(newCategories.getType()) &&
        this.getId() == newCategories.getId();
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO categories(type) VALUES (:type)";
        this.id = (int) con.createQuery(sql, true)
         .addParameter("type", this.type)
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
