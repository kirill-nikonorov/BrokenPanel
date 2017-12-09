package ru.nikonorovcompany.service;

import ru.nikonorovcompany.iterator.ResettableIntegerIterator;

public class InstructionProvider {

    private String workableButtons;
    private String requiredCombination;
    private ResettableIntegerIterator iteratorOfDigitsFromRequiredCombination;
    private Integer theClosestCombination;
    private Integer countOfIterationsToTheClosestCombination;

    public InstructionProvider(String workableButtons, String requiredCombination, ResettableIntegerIterator iteratorOfDigitsFromRequiredCombination) {
        this.workableButtons = workableButtons;
        this.requiredCombination = requiredCombination;
        this.iteratorOfDigitsFromRequiredCombination = iteratorOfDigitsFromRequiredCombination;
    }

    public Integer getTheCountOfIterationsToRequiredCombination() {
        if (countOfIterationsToTheClosestCombination != null) return countOfIterationsToTheClosestCombination;
        else
            countOfIterationsToTheClosestCombination = Math.abs(getTheClosestCombination() - Integer.parseInt(requiredCombination));
        return countOfIterationsToTheClosestCombination;
    }

    public Integer getTheClosestCombination() {
        if (theClosestCombination != null) return theClosestCombination;
        else return findCombination();
    }

    private Integer findCombination() {
        if (isAllCombinationIsPossibleToDial()) return Integer.parseInt(requiredCombination);
        Integer numberOfTheClosestCombinationAbove = sumNumbersOfDigitsFromTheClosestCombinationAbove();
        iteratorOfDigitsFromRequiredCombination.resetIterator();
        Integer numberTheClosestCombinationUnder = sumNumbersOfDigitsFromTheClosestCombinationUnder();
        theClosestCombination = getTheClosestFromTwo(numberOfTheClosestCombinationAbove, numberTheClosestCombinationUnder);
        return theClosestCombination;
    }


    private Boolean isAllCombinationIsPossibleToDial() {
        for (String requiredDigit : requiredCombination.split("")) {
            if (!workableButtons.contains(requiredDigit)) return false;
        }
        return true;
    }

    private Integer sumNumbersOfDigitsFromTheClosestCombinationAbove() {
        Integer valueOfDigit = iteratorOfDigitsFromRequiredCombination.next();
        if (workableButtons.contains(String.valueOf(valueOfDigit)))
            return toNumber(valueOfDigit) + sumNumbersOfDigitsFromTheClosestCombinationAbove();
        else {
            Integer numberFromTheClosestCombinationAbove = toNumber(findExistingValueOfButtonAbove(valueOfDigit));
            while (iteratorOfDigitsFromRequiredCombination.hasNext()) {
                iteratorOfDigitsFromRequiredCombination.next();
                numberFromTheClosestCombinationAbove += toNumber(addTheMinimalNumbers());
            }
            return numberFromTheClosestCombinationAbove;
        }
    }

    private Integer sumNumbersOfDigitsFromTheClosestCombinationUnder() {
        Integer valueOfDigit = iteratorOfDigitsFromRequiredCombination.next();
        if (workableButtons.contains(String.valueOf(valueOfDigit)))
            return toNumber(valueOfDigit) + sumNumbersOfDigitsFromTheClosestCombinationUnder();
        else {
            Integer numberOfTheClosestCombinationUnder = toNumber(findExistingValueOfButtonUnder(valueOfDigit));
            while (iteratorOfDigitsFromRequiredCombination.hasNext()) {
                iteratorOfDigitsFromRequiredCombination.next();
                numberOfTheClosestCombinationUnder += toNumber(addTheMaximalNumbers());
            }
            return numberOfTheClosestCombinationUnder;
        }
    }

    private Integer toNumber(Integer valueOfDigit) {
        return valueOfDigit * (int) Math.pow(10, iteratorOfDigitsFromRequiredCombination.getDegreeOfDigit());
    }

    private Integer getTheClosestFromTwo(Integer above, Integer under) {

        if (isTheUnderResultValid(under)) return above;
        Integer requiredCombinationNumber = Integer.parseInt(requiredCombination);
        Integer differenceBetweenAboveAndRequired = above - requiredCombinationNumber;
        Integer differenceBetweenUnderAndRequired = requiredCombinationNumber - Math.abs(under);
        return differenceBetweenUnderAndRequired <= differenceBetweenAboveAndRequired ? under : above;
    }

    private boolean isTheUnderResultValid(Integer theUnderResult) {
        return (theUnderResult < 0 || theUnderResult == 0 && !workableButtons.contains("0"));
    }

    private Integer findExistingValueOfButtonAbove(Integer startOfSearch) {
        String theClosestButton;
        for (int i = startOfSearch; i <= 9; i++) {
            theClosestButton = String.valueOf(i);
            if (workableButtons.contains(theClosestButton)) {
                return i;
            }
        }
        return Integer.parseInt(findExistingValueOfButtonAbove(0).toString() + addTheMinimalNumbers().toString());
    }

    private Integer findExistingValueOfButtonUnder(Integer startOfSearch) {
        String theClosestButton;
        for (int i = startOfSearch; i > 0; i--) {
            theClosestButton = String.valueOf(i);
            if (workableButtons.contains(theClosestButton)) {
                return i;
            }
        }
        return 0;
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
