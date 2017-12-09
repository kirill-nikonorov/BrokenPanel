package ru.nikonorovcompany.iterator;

import java.util.NoSuchElementException;

public class IntegerIterator implements ResettableIntegerIterator {
    private Integer[] integers;
    private Integer numberOfActualDigit;
    private Integer countOfDigits;
    private boolean isGettedFirst;

    public IntegerIterator(Integer[] integers) {
        this.integers = integers;
        countOfDigits = integers.length;
        numberOfActualDigit = countOfDigits;
        isGettedFirst = true;
    }

    @Override
    public boolean hasNext() {

        return numberOfActualDigit != 1;
    }

    @Override
    public Integer next() {
        if (numberOfActualDigit < 0) throw new NoSuchElementException();
        int positionOfDigit = countOfDigits - numberOfActualDigit;
        if (isGettedFirst) {
            isGettedFirst = false;
            return integers[positionOfDigit];
        } else {
            numberOfActualDigit--;
            return integers[positionOfDigit];
        }
    }

    public void resetIterator() {
        numberOfActualDigit = countOfDigits;
        isGettedFirst = true;
    }

    @Override
    public Integer getDegreeOfDigit() {
        return numberOfActualDigit - 1;
    }

}
