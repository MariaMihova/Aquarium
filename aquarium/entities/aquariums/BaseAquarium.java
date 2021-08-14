package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseAquarium implements Aquarium{

    private String name;
    private int capacity;
    private List<Decoration> decorations;
    protected List<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if(this.fish.size() == this.capacity){
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);

    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        //"{aquariumName} ({aquariumType}):
        //Fish: {fishName1} {fishName2} {fishName3} (…) / Fish: none
        //Decorations: {decorationsCount}
        //Comfort: {aquariumComfort}"
        StringBuilder fill = new StringBuilder();
        fill.append(String.format("%s (%s):", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append("Fish: ");
        if(this.fish.isEmpty()){
            fill.append("none");
        }else {
            List<String> names = new ArrayList<>();
            for (Fish fish1 : this.fish) {
                names.add(fish1.getName());
            }
            fill.append(String.join(" ", names));
        }
        fill.append(System.lineSeparator())
                .append(String.format("Decorations: %d", this.decorations.size()))
                .append(System.lineSeparator())
                .append(String.format("Comfort: %d", this.calculateComfort()));
        return fill.toString().trim();
    }

    @Override
    public List<Fish> getFish() {
        return this.fish;
    }

    @Override
    public List<Decoration> getDecorations() {
        return this.decorations;
    }
}
