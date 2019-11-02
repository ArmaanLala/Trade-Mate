package hack.gsu.trademate;

import java.util.Date;

public class Post {
    public String name,imageUrl,cost,product;


    public Post () {

    }

    public Post ( String name,String product, String imageUrl,String cost){
        this.name = name;
        this.imageUrl=imageUrl;
        this.cost= cost;


        this.product=product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
