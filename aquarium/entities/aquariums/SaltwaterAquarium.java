package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.fish.Fish;

public class SaltwaterAquarium extends BaseAquarium{

    private static final int CAPACITY = 25;


    public SaltwaterAquarium(String name) {
        super(name, CAPACITY);
    }

    @Override
    public void addFish(Fish fish){
        super.addFish(fish);
        if(fish.getClass().getSimpleName().equals("SaltwaterFish")){
            this.fish.add(fish);

        }else {
            throw new IllegalArgumentException(ConstantMessages.WATER_NOT_SUITABLE);
        }

    }
}
