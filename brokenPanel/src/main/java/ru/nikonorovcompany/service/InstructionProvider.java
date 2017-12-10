package ru.nikonorovcompany.service;

import ru.nikonorovcompany.iterator.TwoStepIterableIntegerIterator;
import ru.nikonorovcompany.iterator.TwoStepIterableIterator;

public class InstructionProvider {

    private String workableButtons;
    private Integer requiredCombination;
    private TwoStepIterableIterator iteratorOfDigits;
    private Integer numberOfTheClosestCombinationResult;
    private Integer countOfIterationsToTheClosestCombination;

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

    private Integer findNumberFromMatchesUntilRestInBrokenButton() {
        Integer numberFromMatchesInBeginning = 0;
        Integer digitValue;
        while (iteratorOfDigits.hasNext()) {
            digitValue = iteratorOfDigits.next();
            if (isWorkable(digitValue)) {
                numberFromMatchesInBeginning += toNumber(digitValue);
                iteratorOfDigits.handled();
            } else break;
        }
        return numberFromMatchesInBeginning;
    }

    private void findCombination() {
        Integer numberOfTheClosestCombinationAbove = findTheClosestCombinationAbove(requiredCombination);
        Integer numberOfTheClosestCombinationUnder = findTheClosestCombinationUnder(requiredCombination);
        numberOfTheClosestCombinationResult = getTheClosestFromTwo(numberOfTheClosestCombinationAbove, numberOfTheClosestCombinationUnder);
    }

    private Integer findTheClosestCombinationAbove(Integer requiredCombination) {
        iteratorOfDigits = new TwoStepIterableIntegerIterator(requiredCombination);
        Integer digitValue;
        Integer numberOfTheClosestCombinationAbove = findNumberFromMatchesUntilRestInBrokenButton();
        if (iteratorOfDigits.hasNext()) {
            digitValue = iteratorOfDigits.next();
            numberOfTheClosestCombinationAbove += toNumber(findExistingValueOfButtonAbove(digitValue));
            iteratorOfDigits.handled();
        }
        while (iteratorOfDigits.hasNext()) {
            iteratorOfDigits.next();
            iteratorOfDigits.handled();
            numberOfTheClosestCombinationAbove += toNumber(addTheMinimalNumbers());
        }

        return checkAboveCombination(numberOfTheClosestCombinationAbove);
    }

    private Integer findTheClosestCombinationUnder(Integer requiredCombination) {
        iteratorOfDigits = new TwoStepIterableIntegerIterator(requiredCombination);
        Integer digitValue;
        Integer numberOfTheClosestCombinationUnder = findNumberFromMatchesUntilRestInBrokenButton();
        if (iteratorOfDigits.hasNext()) {
            digitValue = iteratorOfDigits.next();
            numberOfTheClosestCombinationUnder += toNumber(findExistingValueOfButtonUnder(digitValue));
            iteratorOfDigits.handled();
        }
        while (iteratorOfDigits.hasNext()) {
            iteratorOfDigits.next();
            iteratorOfDigits.handled();
            numberOfTheClosestCombinationUnder += toNumber(addTheMaximalNumbers());
        }
        return numberOfTheClosestCombinationUnder;
    }

    private Integer checkAboveCombination(Integer ubove) {
        if (isWorkable(ubove)) return ubove;
        else return findTheClosestCombinationAbove(ubove);
    }

    private Boolean isWorkable(Integer combination) {
        String combinationInString = String.valueOf(combination);
        for (String requiredDigit : combinationInString.split("")) {
            if (!workableButtons.contains(requiredDigit)) return false;
        }
        return true;
    }

    private Integer toNumber(Integer valueOfDigit) {
        return valueOfDigit * (int) Math.pow(10, iteratorOfDigits.getDegreeOfDigit());
    }

    private Integer getTheClosestFromTwo(Integer above, Integer under) {

        if (!isTheUnderResultValid(under)) return above;
        Integer differenceBetweenAboveAndRequired = above - requiredCombination;
        Integer differenceBetweenUnderAndRequired = requiredCombination - Math.abs(under);
        return differenceBetweenUnderAndRequired <= differenceBetweenAboveAndRequired ? under : above;
    }

    private boolean isTheUnderResultValid(Integer theUnderResult) {
        return (theUnderResult > 0 || theUnderResult == 0 && workableButtons.contains("0"));
    }

    private Integer findExistingValueOfButtonAbove(Integer startOfSearch) {
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
        return Integer.parseInt(String.valueOf(findExistingValueOfButtonAbove(1)) + String.valueOf(addTheMinimalNumbers()));
    }

    private Integer findExistingValueOfButtonUnder(Integer startOfSearch) {
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
        if (iteratorOfDigits.hasPrevious()) return findExistingValueOfButtonUnder(9) - 10;
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
