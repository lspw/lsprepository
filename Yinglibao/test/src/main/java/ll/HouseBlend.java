package ll;

// HouseBlend咖啡类
public class HouseBlend implements Coffee {
    @Override
    public double getCost() {
        return 20.0;
    }

    @Override
    public String getDescription() {
        return "HouseBlend Coffee";
    }
}
