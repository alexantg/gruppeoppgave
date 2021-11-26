package Controller;

import Model.AntiqueShop;
import Model.Item;
import Repository.IAntiqueSystemRep;
import io.javalin.http.Context;

import java.util.ArrayList;

public class ItemController {
    private IAntiqueSystemRep iAntiqueSystemRep;

    public ItemController(IAntiqueSystemRep iAntiqueSystemRep) {
        this.iAntiqueSystemRep = iAntiqueSystemRep;
    }


    public void getShop(Context context){
        String name = context.pathParam("shop-id");
        context.json(iAntiqueSystemRep.getShop(name));
    }

    public void getAllShops(Context context){
        context.json(iAntiqueSystemRep.getAllShops());
    }

    public void createShop(Context context){
        iAntiqueSystemRep.createShop(dataFromInputShop(context));
        context.redirect("/user/");
    }

    //get data from form-input when creating
    public AntiqueShop dataFromInputShop(Context context){
        String name;
        String email;
        String address;

        name = context.formParam("shopName");
        email = context.formParam("email");
        address = context.formParam("address");

        AntiqueShop shop = new AntiqueShop(name,email,address);
        return shop;
    }

    public void createItem(Context context){
        String shopName = context.pathParam("shop-id");
        iAntiqueSystemRep.createItem(shopName,dataFromInputItem(context));
        context.redirect("/user/");
    }

    public Item dataFromInputItem(Context context){
        String name;
        String description;
        String pictureUrl;
        String price;

        name = context.formParam("itemName");
        description = context.formParam("description");
        pictureUrl = context.formParam("pictureUrl");
        price = context.formParam("price");

        return new Item(name, description, price, pictureUrl);
    }

      /*  public void getAllItems(Context context) {
        ArrayList<Item> allItems = iAntiqueSystemRep.getAllItems();

        context.json(allItems);
    } */

   /* public void getOneItem(Context context) {
        String itemID = context.pathParam(":item-id");

        Item aItem = iAntiqueSystemRep.getOneItem(itemID);

        context.json(aItem);
    } */

}
