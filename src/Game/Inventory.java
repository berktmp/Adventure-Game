package Game;

public class Inventory {
    private Weapon weapon;

    private Armor armor;
    private String firewood;
    private String food;
    private String water;
    private String snake;
    public Inventory() {

        this.weapon = new Weapon("Punch",-1,0,0);
        this.armor = new Armor(-1,"Rag",0,0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public String getFirewood() {
        return firewood;
    }

    public void setFirewood(String firewood) {
        this.firewood = firewood;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getSnake() {
        return snake;
    }

    public void setSnake(String snake) {
        this.snake = snake;
    }
}
