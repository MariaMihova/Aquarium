package aquarium.entities.fish;


public class FreshwaterFish extends  BaseFish{

    private static final int INITIAL_SIZE = 3;
    private static final int INCREASE_SIZE = 3;



    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.size = INITIAL_SIZE;
    }

    @Override
    public void eat() {
        this.size += INCREASE_SIZE;
    }



}
