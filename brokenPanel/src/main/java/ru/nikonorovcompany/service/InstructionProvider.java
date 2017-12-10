package ru.nikonorovcompany.service;

import ru.nikonorovcompany.iterator.DigitIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionProvider {

    private String workableButtons;
    private Integer requiredCombination;

    private DigitIterator iterator;
    private Integer numberOfTheClosestCombinationResult;
    private Integer countOfIterationsToTheClosestCombination;
    private Integer value;

    public InstructionProvider(String workableButtons, Integer requiredCombination) {
        this.workableButtons = workableButtons;
        this.requiredCombination = requiredCombination;
    }

    public Integer getTheCountOfIterationsToRequiredCombination() {
        if (countOfIterationsToTheClosestCombination != null) return countOfIterationsToTheClosestCombination;
        else
            countOfIterationsToTheClosestCombination = Math.abs(getNumberOfTheClosestCombinationResult() - requiredCombination);
        return countOfIterationsToTheClosestCombination;
    }

    public Integer getNumberOfTheClosestCombinationResult() {
        if (numberOfTheClosestCombinationResult != null) return numberOfTheClosestCombinationResult;
        else {
            findCombination();
            return numberOfTheClosestCombinationResult;
        }
    }

    private Integer findNumberOfCombinationBeginningInWorkableButtons() {
        Integer NumberOfCombinationBeginningInWorkableButtons = 0;
        while (iterator.hasNext()) {
            if (isWorkable(value)) {
                NumberOfCombinationBeginningInWorkableButtons += toNumber(value);
                value = iterator.next();
            } else break;
        }
        return NumberOfCombinationBeginningInWorkableButtons;
    }

    private void findCombination() {
        Integer numberOfTheClosestCombinationAbove = findNumberOfTheClosestCombinationAbove(requiredCombination);
        Integer numberOfTheClosestCombinationUnder = findNumberOfTheClosestCombinationUnder(requiredCombination);
        numberOfTheClosestCombinationResult = getTheClosestFromTwo(numberOfTheClosestCombinationAbove, numberOfTheClosestCombinationUnder);
    }

    private DigitIterator getIterator(Integer number) {
        List<Integer> list = new ArrayList<>();
        String numbers = String.valueOf(number);
        Arrays.stream(numbers.split("")).map(Integer::parseInt).forEach(list::add);
        return new DigitIterator(list);
    }

    private Integer findNumberOfTheClosestCombinationAbove(Integer requiredCombination) {
        iterator = getIterator(requiredCombination);
        value = iterator.next();

        Integer numberOfTheClosestCombinationAbove = findNumberOfCombinationBeginningInWorkableButtons();
        if (!iterator.hasNext() && isLastValueWorkable()) return numberOfTheClosestCombinationAbove;

        else {
            return fillNumberByRestCombinationAbove(numberOfTheClosestCombinationAbove);
        }
    }

    private Boolean isLastValueWorkable() {
        return isWorkable(value);
    }

    private Integer fillNumberByRestCombinationAbove(Integer numberOfTheClosestCombinationAbove) {
        numberOfTheClosestCombinationAbove += toNumber(findButtonAboveExistingValue(value));
        while (iterator.hasNext()) {
            iterator.next();
            numberOfTheClosestCombinationAbove += toNumber(addTheMinimalNumbers());
        }
        return checkAboveCombination(numberOfTheClosestCombinationAbove);

    }

    private Integer checkAboveCombination(Integer above) {
        if (isWorkable(above)) return above;
        else return findNumberOfTheClosestCombinationAbove(above);
    }

    private Integer findNumberOfTheClosestCombinationUnder(Integer requiredCombination) {
        iterator = getIterator(requiredCombination);
        value = iterator.next();

        Integer numberOfTheClosestCombinationUnder = findNumberOfCombinationBeginningInWorkableButtons();
        if (!iterator.hasNext() && isLastValueWorkable()) return numberOfTheClosestCombinationUnder;

        else {
            return fillNumberByRestCombinationUnder(numberOfTheClosestCombinationUnder);
        }
    }

    private Integer fillNumberByRestCombinationUnder(Integer numberOfTheClosestCombinationUnder) {
        numberOfTheClosestCombinationUnder += toNumber(findNumberOfButtonUnderExistingValue(value));
        while (iterator.hasNext()) {
            iterator.next();
            numberOfTheClosestCombinationUnder += toNumber(addTheMaximalNumbers());
        }
        return checkUnderCombination(numberOfTheClosestCombinationUnder);

    }

    private Integer checkUnderCombination(Integer under) {
        if (under == 0) return under;
        if (isWorkable(under)) return under;
        else return findNumberOfTheClosestCombinationUnder(under);
    }


    private Boolean isWorkable(Integer combination) {
        String combinationInString = String.valueOf(combination);
        for (String requiredDigit : combinationInString.split("")) {
            if (!workableButtons.contains(requiredDigit)) return false;
        }
        return true;
    }

    private Integer toNumber(Integer valueOfDigit) {
        return valueOfDigit * (int) Math.pow(10, iterator.getDegreeOfDigit());
    }

    private Integer getTheClosestFromTwo(Integer above, Integer under) {

        if (!isTheUnderResultValid(under)) return above;
        Integer differenceBetweenAboveAndRequired = above - requiredCombination;
        Integer differenceBetweenUnderAndRequired = requiredCombination - Math.abs(under);
        return differenceBetweenUnderAndRequired <= differenceBetweenAboveAndRequired ? under : above;
    }

    private boolean isTheUnderResultValid(Integer theUnderResult) {
        return (theUnderResult > 0 || theUnderResult == 0 && isWorkable(0));
    }

    private Integer findButtonAboveExistingValue(Integer startOfSearch) {
        int theClosestButton;
        for (int i = startOfSearch; i <= 9; i++) {
            theClosestButton = i;
            if (isWorkable(theClosestButton)) {
                return i;
            }
        }
        return handleOverNine();
    }

    private Integer handleOverNine() {
        return Integer.parseInt(String.valueOf(findButtonAboveExistingValue(1)) + String.valueOf(addTheMinimalNumbers()));
    }

    private Integer findNumberOfButtonUnderExistingValue(Integer startOfSearch) {
        int theClosestButton;
        for (int i = startOfSearch; i > 0; i--) {
            theClosestButton = i;
            if (isWorkable(theClosestButton)) {
                return i;
            }
        }
        return handleZero();
    }

    private Integer handleZero() {
        if (iterator.hasPrevious()) return findNumberOfButtonUnderExistingValue(9) - 10;
        else {
            return 0;
        }
    }

    private Integer addTheMaximalNumbers() {
        int max = 0;
        int buttonValue;
        for (String button : workableButtons.split("")) {
            buttonValue = Integer.parseInt(button);
            if (buttonValue > max) max = buttonValue;
        }
        return max;
    }

    private Integer addTheMinimalNumbers() {
        int min = 9;
        int buttonValue;
        for (String button : workableButtons.split("")) {
            buttonValue = Integer.parseInt(button);
            if (buttonValue < min) min = buttonValue;
        }
        return min;
    }
}
