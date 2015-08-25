import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("categories", Categories.all());
      model.put("template", "templates/home.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/category", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Categories newCategories = new Categories(name);
      newCategories.save();
      model.put("categories", Categories.all());
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/category/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Categories name = Categories.find(Integer.parseInt(request.params(":id")));

      model.put("name", name);
      model.put("template", "templates/catname.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
  }
}
