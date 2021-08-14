package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish{

    private static final int INITIAL_SIZE = 5;
    private static final int INCREASE_SIZE = 2;


    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.size = INITIAL_SIZE;
    }

    @Override
    public void eat() {
        this.size += INCREASE_SIZE;
    }

}
