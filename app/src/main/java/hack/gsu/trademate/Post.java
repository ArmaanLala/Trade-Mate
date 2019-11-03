package hack.gsu.trademate;

import java.util.Date;

public class Post {
    public String name,imageUrl,cost,title,key,buyer;
    public boolean taken;

    public Post () {

    }

    public Post ( String name ,String title, String imageUrl,String cost,String key,String buyer,boolean taken){
        this.name = name;
        this.imageUrl=imageUrl;
        this.cost= cost;
        this.taken = false;
    this.key = key;
        this.title=title;
        this.buyer = buyer;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTaken() {
//        return false;
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
