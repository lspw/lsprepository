package ll;


public class CoffeeShop {
    public static void main(String[] args) {
        // 创建一个HouseBlend咖啡，加上牛奶和摩卡
        Coffee houseBlend = new HouseBlend();
        Coffee milkHouseBlend = new MilkDecorator(houseBlend);
        Coffee mochaHouseBlend = new MochaDecorator(milkHouseBlend);

        // 计算价格和描述
        System.out.println(mochaHouseBlend.getCost()); // 输出35.0
        System.out.println(mochaHouseBlend.getDescription()); // 输出"HouseBlend Coffee, with Milk, with Mocha"

        // 创建一个Espresso咖啡，加上牛奶
        Coffee espresso = new Espresso();
        Coffee milkEspresso = new MilkDecorator(espresso);

        // 计算价格和描述
        System.out.println(milkEspresso.getCost()); // 输出30.0
        System.out.println(milkEspresso.getDescription()); // 输出"Espresso Coffee, with Milk"
    }
}
