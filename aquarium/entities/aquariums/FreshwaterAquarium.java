package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.entities.fish.Fish;

public class FreshwaterAquarium extends BaseAquarium{

    private static final int CAPACITY = 50;


    public FreshwaterAquarium(String name) {
        super(name, CAPACITY);
    }

    @Override
    public void addFish(Fish fish){
        super.addFish(fish);
        if(fish.getClass().getSimpleName().equals("FreshwaterFish")){
            this.fish.add(fish);

        }else {
            throw new IllegalArgumentException(ConstantMessages.WATER_NOT_SUITABLE);
        }

    }
}
