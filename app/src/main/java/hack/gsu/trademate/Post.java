package hack.gsu.trademate;

import java.util.Date;

public class Post {
    public String name,imageUrl,cost,imageThumb,product;
    public Date currentTime;

    public Post () {

    }

    public Post ( String name,String product, String imageUrl,String cost,String imageThumb, Date date){
        this.name = name;
        this.imageUrl=imageUrl;
        this.cost= cost;
        this.imageThumb = imageThumb;
        this.currentTime=date;
        this.product=product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
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

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
