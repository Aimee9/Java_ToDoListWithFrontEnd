import org.junit.*;
import static org.junit.Assert.*;

public class CategoriesTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Categories.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame(){
    Categories firstCategories = new Categories("Errand");
    Categories secondCategories = new Categories("Errand");
    assertTrue(firstCategories.equals(secondCategories));
  }

  @Test
  public void equals_returnsSavedCategory(){
    Categories myCategories = new Categories("Errand");
    myCategories.save();
    assertTrue(Categories.all().get(0).equals(myCategories));
  }

  @Test
  public void find_findsCategoriesInDatabase_true() {
    Categories myCategories = new Categories("Chores");
    myCategories.save();
    Categories savedCategories = Categories.find(myCategories.getId());
    assertTrue(myCategories.equals(savedCategories));
  }
  @Test
  public void save_assignsIdToCategory() {
    Categories myCategories = new Categories("Chores");
    myCategories.save();
    Categories savedCategories = Categories.all().get(0);
    assertEquals(myCategories.getId(), savedCategories.getId());
  }
}
