package ru.nikonorovcompany.iterator;

public interface TwoStepIterableIterator {
    public boolean hasNext();

    public Integer next();
    public Integer getDegreeOfDigit();
    public Boolean hasPrevious();
    public void handled();
}
