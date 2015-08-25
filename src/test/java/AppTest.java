import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hooray for Your To Do List!");
  }

  @Test
  public void addCategory(){
    goTo("http://localhost:4567/");
    fill("#name").with("Errands");
    submit(".btn");
    assertThat(pageSource()).contains("Errands");
  }

  @Test
  public void goToACategory(){
    goTo("http://localhost:4567/");
    fill("#name").with("Home");
    submit(".btn");
    goTo("http://localhost:4567/category");
    click("a", withText("Home"));
    assertThat(pageSource()).contains("Home");
  }
}
