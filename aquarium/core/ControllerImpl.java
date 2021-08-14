package aquarium.core;


import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;

public class ControllerImpl implements Controller{
        private DecorationRepository decorations;
        private List<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium = null;
        switch (aquariumType){
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        this.aquariums.add(aquarium);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration = null;
        switch (type){
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }
        this.decorations.add(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Aquarium currentAquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);

        Decoration currentDecoration = this.decorations.findByType(decorationType);
        if(currentDecoration == null){
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
        currentAquarium.addDecoration(currentDecoration);
        this.decorations.remove(currentDecoration);
        return String.format(
                ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Aquarium currentAquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        Fish currentFish = null;
        switch (fishType){
            case "FreshwaterFish":
                currentFish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                currentFish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        currentAquarium.addFish(currentFish);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium currentAquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        currentAquarium.feed();
        return String.format(ConstantMessages.FISH_FED, currentAquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium currentAquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        double decorationsPrice = currentAquarium.getDecorations().stream()
                .mapToDouble(Decoration::getPrice).sum();
        double fishPrice = currentAquarium.getFish().stream()
                .mapToDouble(Fish::getPrice).sum();
        return String.format(
                ConstantMessages.VALUE_AQUARIUM, aquariumName, decorationsPrice + fishPrice);
    }

    @Override
    public String report() {
        StringBuilder fill = new StringBuilder();
        for (Aquarium aquarium : this.aquariums) {
            fill.append(aquarium.getInfo())
                    .append(System.lineSeparator());
        }
        return fill.toString().trim();
    }
}
