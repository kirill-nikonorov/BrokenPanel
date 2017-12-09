package ru.nikonorovcompany.iterator;

public interface ResettableIntegerIterator {

    public boolean hasNext();

    public Integer next();

    public void resetIterator();

    public Integer getDegreeOfDigit();
}
