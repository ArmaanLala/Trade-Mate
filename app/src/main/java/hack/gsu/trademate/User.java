package hack.gsu.trademate;

public class User {
    public String name,college;
    public User(){

    }
    public User(String name, String College){
        this.name = name;
        this.college=College;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCollege() {
        return college;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
