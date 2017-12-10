package ru.nikonorovcompany.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DigitIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer numberOfActualDigit;
    private Integer countsOfDigits;
    private boolean isCalledFirst;


    public DigitIterator(List<Integer> list) {
        this.iterator = list.iterator();
        countsOfDigits = list.size();
        this.numberOfActualDigit = countsOfDigits;
        this.isCalledFirst = true;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }


    @Override
    public Integer next() {
        if (numberOfActualDigit < 0) throw new NoSuchElementException();
        if (isCalledFirst) {
            isCalledFirst = false;
            return iterator.next();
        } else {
            numberOfActualDigit--;
            return iterator.next();
        }
    }


    public Integer getDegreeOfDigit() {
        return numberOfActualDigit - 1;
    }

    public Boolean hasPrevious() {
        return (numberOfActualDigit != countsOfDigits);
    }


}
