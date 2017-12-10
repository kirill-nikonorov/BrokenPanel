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

    private Integer findNumberOfTheClosestCombinationAboveForTheFirstMetBrokenButton() {
        Integer numberOfTheClosestCombination = 0;
        Integer value;
        Integer foundButton;
        while (iterator.hasNext()) {
            value = iterator.next();
            foundButton = findButtonAbove(value);
            numberOfTheClosestCombination += toNumber(foundButton);
            if (!value.equals(foundButton)) break;
        }
        return numberOfTheClosestCombination;
    }

    private Integer getNumberOfMinimalValuesOfWorkedButtons() {
        Integer number = 0;
        while (iterator.hasNext()) {
            iterator.next();
            number += toNumber(getTheMinimalWorkedButton());
        }
        return number;
    }

    private Integer getNumberOfMaximalValuesOfWorkedButtons() {
        Integer numberOfCombination = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfCombination += toNumber(getTheMaximalWorkedButton());
        }
        return numberOfCombination;
    }

    private Integer findNumberOfTheClosestCombinationAbove(Integer requiredCombination) {
        iterator = getIterator(requiredCombination);
        Integer numberOfTheClosestCombinationAbove = findNumberOfTheClosestCombinationAboveForTheFirstMetBrokenButton();
        numberOfTheClosestCombinationAbove += getNumberOfMinimalValuesOfWorkedButtons();
        return checkAboveCombination(numberOfTheClosestCombinationAbove);

    }

    private Integer checkAboveCombination(Integer above) {
        if (isWorkable(above)) return above;
        else return findNumberOfTheClosestCombinationAbove(above);
    }

    private Integer findNumberOfTheClosestCombinationUnder(Integer requiredCombination) {
        iterator = getIterator(requiredCombination);
        Integer numberOfTheClosestCombinationUnder = findNumberOfTheClosestCombinationUnderForTheFirstMetBrokenButton();
        numberOfTheClosestCombinationUnder += getNumberOfMaximalValuesOfWorkedButtons();
        return checkUnderCombination(numberOfTheClosestCombinationUnder);
    }

    private Integer findNumberOfTheClosestCombinationUnderForTheFirstMetBrokenButton() {
        Integer numberOfTheClosestCombination = 0;
        Integer value;
        Integer foundButton;
        while (iterator.hasNext()) {
            value = iterator.next();
            foundButton = findButtonUnder(value);
            numberOfTheClosestCombination += toNumber(foundButton);
            if (!value.equals(foundButton)) break;
        }
        return numberOfTheClosestCombination;
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

    private Integer findButtonAbove(Integer startOfSearch) {
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
        return Integer.parseInt(String.valueOf(findButtonAbove(1)) + String.valueOf(getTheMinimalWorkedButton()));
    }

    private Integer findButtonUnder(Integer startOfSearch) {
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
        if (iterator.hasPrevious()) return findButtonUnder(9) - 10;
        else {
            return 0;
        }
    }

    private Integer getTheMaximalWorkedButton() {
        int max = 0;
        int buttonValue;
        for (String button : workableButtons.split("")) {
            buttonValue = Integer.parseInt(button);
            if (buttonValue > max) max = buttonValue;
        }
        return max;
    }

    private Integer getTheMinimalWorkedButton() {
        int min = 9;
        int buttonValue;
        for (String button : workableButtons.split("")) {
            buttonValue = Integer.parseInt(button);
            if (buttonValue < min) min = buttonValue;
        }
        return min;
    }
}
