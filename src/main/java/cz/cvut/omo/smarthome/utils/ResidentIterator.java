package cz.cvut.omo.smarthome.utils;

import cz.cvut.omo.smarthome.house.resident.Resident;
import cz.cvut.omo.smarthome.house.resident.person.Person;
import cz.cvut.omo.smarthome.house.resident.animal.Animal;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Iterator for Resident collections.
 * Implements the Iterator pattern to provide sequential access to Resident objects.
 * Supports optional filtering via predicates.
 */
public class ResidentIterator implements Iterator<Resident> {
    private final List<Resident> residents;
    private int currentPosition;
    private final Predicate<Resident> filter;

    /**
     * Creates an iterator for all residents in the collection.
     * @param residents the list of residents to iterate over
     */
    public ResidentIterator(List<Resident> residents) {
        this(residents, null);
    }

    /**
     * Creates an iterator with a filter predicate.
     * @param residents the list of residents to iterate over
     * @param filter predicate to filter residents (null for no filtering)
     */
    public ResidentIterator(List<Resident> residents, Predicate<Resident> filter) {
        this.residents = residents != null ? residents : List.of();
        this.currentPosition = 0;
        this.filter = filter;
        // Move to first matching element if filter is present
        if (this.filter != null) {
            advanceToNextMatch();
        }
    }

    /**
     * Advances the position to the next element that matches the filter.
     */
    private void advanceToNextMatch() {
        while (currentPosition < residents.size() && !filter.test(residents.get(currentPosition))) {
            currentPosition++;
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < residents.size();
    }

    @Override
    public Resident next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more residents in the collection");
        }
        Resident resident = residents.get(currentPosition);
        currentPosition++;
        // If filter is present, advance to next match
        if (filter != null) {
            advanceToNextMatch();
        }
        return resident;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported by ResidentIterator");
    }

    /**
     * Creates an iterator that filters residents to only Person types.
     * @param residents the list of residents to iterate over
     * @return iterator that only returns Person residents
     */
    public static ResidentIterator filterPersons(List<Resident> residents) {
        return new ResidentIterator(residents, resident -> resident instanceof Person);
    }

    /**
     * Creates an iterator that filters residents to only Animal types.
     * @param residents the list of residents to iterate over
     * @return iterator that only returns Animal residents
     */
    public static ResidentIterator filterAnimals(List<Resident> residents) {
        return new ResidentIterator(residents, resident -> resident instanceof Animal);
    }

    /**
     * Creates an iterator that filters residents by class type.
     * @param residents the list of residents to iterate over
     * @param residentClass the class of resident to filter by
     * @param <T> the type of resident
     * @return iterator that only returns residents of the specified class
     */
    public static <T extends Resident> ResidentIterator filterByClass(List<Resident> residents, Class<T> residentClass) {
        return new ResidentIterator(residents, residentClass::isInstance);
    }
}
