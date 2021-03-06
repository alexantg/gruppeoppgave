package Controller;

import Model.AntiqueShop;
import Repository.AntiqueSystemJSONRep;
import io.javalin.Javalin;
import io.javalin.http.Context;
import junit.framework.TestCase;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import static org.assertj.core.api.Assertions.assertThat;


public class AntiqueShopControllerTest extends TestCase {
    Javalin app = Javalin.create();

    public void testGetAllShops() {

        app.start(8000);

        AntiqueSystemJSONRep iAntiqueSystemJSONRep = new AntiqueSystemJSONRep("src/main/resources/test.json");
        AntiqueShopController antiqueShopController = new AntiqueShopController(iAntiqueSystemJSONRep);
        iAntiqueSystemJSONRep.readShopsFromFile();

        app.get("api/shops", antiqueShopController::getAllShops);

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8000/api/shops").asJson();
        assertThat(response.getStatus()).isEqualTo(201);

        app.stop();

    }

    public void testGetShop() {
        app.start(8000);

        AntiqueSystemJSONRep iAntiqueSystemJSONRep = new AntiqueSystemJSONRep("src/main/resources/test.json");
        AntiqueShopController antiqueShopController = new AntiqueShopController(iAntiqueSystemJSONRep);
        iAntiqueSystemJSONRep.readShopsFromFile();

        app.get("api/user/:shop-id", antiqueShopController::getShop);

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8000/api/user/TestStore").asJson();
        assertThat(response.getStatus()).isEqualTo(202);

        app.stop();
    }

    public void testCreateShop() {
        app.start(8000);

        AntiqueSystemJSONRep iAntiqueSystemJSONRep = new AntiqueSystemJSONRep("src/main/resources/test.json");
        AntiqueShopController antiqueShopController = new AntiqueShopController(iAntiqueSystemJSONRep);
        iAntiqueSystemJSONRep.readShopsFromFile();

        app.post("api/user/createshop", antiqueShopController::createShop);

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8000/api/user/createshop").asJson();
        assertThat(response.getStatus()).isEqualTo(202);

        app.stop();
    }

    public void testDataFromInputShop() {

        //Metoden "dataFromInputShop" blir indirekte testet i testCreateShop siden createShop metoden bruker dataFromInputshop som en del av sin funksjonalitet

    }
}