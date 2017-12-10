package ru.nikonorovcompany.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TwoStepIterableIntegerIterator implements TwoStepIterableIterator {
    private Integer[] arrayOfDigits;
    private Integer numberOfActualDigit;
    private Integer countOfDigits;
    private boolean isCalledFirst;
    private boolean isHandled;


    public TwoStepIterableIntegerIterator(Integer number) {
        this.arrayOfDigits = extractDigits(number);
        this.numberOfActualDigit = arrayOfDigits.length;
        this.countOfDigits = arrayOfDigits.length;
        this.isCalledFirst = true;
        this.isHandled = false;
    }
    private Integer[] extractDigits(Integer number) {
        List<Integer> list = new ArrayList<>();
        String numbers = String.valueOf(number);
        Arrays.stream(numbers.split("")).map(Integer::parseInt).forEach(list::add);
        return list.toArray(new Integer[0]);
    }

    @Override
    public boolean hasNext() {
        return !isHandled || numberOfActualDigit != 1;
    }


    public void handled() {
        isHandled = true;
    }

    @Override
    public Integer next() {
        if (numberOfActualDigit < 0) throw new NoSuchElementException();

        int positionOfDigit;
        if (isCalledFirst || !isHandled) {
            isCalledFirst = false;
            positionOfDigit = countOfDigits - numberOfActualDigit;
            return arrayOfDigits[positionOfDigit];
        } else {
            numberOfActualDigit--;
            isHandled = false;
            positionOfDigit = countOfDigits - numberOfActualDigit;
            return arrayOfDigits[positionOfDigit];
        }
    }

    @Override
    public Integer getDegreeOfDigit() {
        return numberOfActualDigit - 1;
    }

    @Override
    public Boolean hasPrevious() {
        return (!numberOfActualDigit.equals(countOfDigits));
    }

}
