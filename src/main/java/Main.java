import controller.ItemController;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {

        ItemController itemController = new ItemController();
        get("items", (request, response) -> itemController.prueba(request, response));
    }
}
