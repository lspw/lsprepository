package ll;

// Espresso咖啡类
public class Espresso implements Coffee {
    @Override
    public double getCost() {
        return 25.0;
    }

    @Override
    public String getDescription() {
        return "Espresso Coffee";
    }
}
