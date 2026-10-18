package ma.ensate.bibliotheque.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class Gestionnaire<T> {
    private final ArrayList<T> elements;
    private final Function<T, String> extracteurId;

    public Gestionnaire(Function<T, String> extracteurId) {
        this.elements = new ArrayList<>();
        this.extracteurId = extracteurId;
    }

    public void add(T element) {
        elements.add(element);
    }

    public boolean remove(T element) {
        return elements.remove(element);
    }

    public ArrayList<T> getAll() {
        return new ArrayList<>(elements);
    }

    public Optional<T> findById(String id) {
        return elements.stream().filter(e -> extracteurId.apply(e).equals(id)).findFirst();
    }
}
