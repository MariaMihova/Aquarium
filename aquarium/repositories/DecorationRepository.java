package aquarium.repositories;

import aquarium.entities.decorations.Decoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecorationRepository implements Repository{
    private List<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new ArrayList<>();
    }

    @Override
    public void add(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return this.decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        return this.decorations.stream()
                .filter(d -> d.getClass().getSimpleName().equals(type))
                .findFirst()
                .orElse(null);
    }
}
