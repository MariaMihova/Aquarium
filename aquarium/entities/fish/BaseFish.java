package aquarium.entities.fish;

import aquarium.common.ExceptionMessages;

public abstract class BaseFish implements Fish{

    private String name;
    private String species;
    protected int size;
    private double price;
    private static final int INCREASE_SIZE = 5;

    protected BaseFish(String name, String species, double price) {
        this.setName(name);
        this.setSpecies(species);
        this.setPrice(price);
    }

    private void setSpecies(String species) {
        if(species == null || species.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.SPECIES_NAME_NULL_OR_EMPTY);
        }
        this.species = species;
    }

    private void setPrice(double pricer){
        if(pricer <= 0){
            throw new IllegalArgumentException(ExceptionMessages.FISH_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = pricer;
    }



    @Override
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.FISH_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void eat() {
    this.size += INCREASE_SIZE;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
