package ru.nikonorovcompany.service;

public class InstructionProvider {

    private String workableButtons;
    private String requiredCombination;
    private int actualDigit;
    private boolean calledFirstTime;

    public InstructionProvider(String workableButtons, String requiredCombination, int actualDigit) {
        this.workableButtons = workableButtons;
        this.requiredCombination = requiredCombination;
        this.actualDigit = actualDigit;
        this.calledFirstTime = true;
    }

    public Integer findCombination() {
        if (isAllCombinationIsPossibleToDial()) return Integer.parseInt(requiredCombination);
        Integer numberOfTheClosestCombinationAbove = sumNumbersOfDigitsFromTheClosestCombinationAbove();
        Integer numberTheClosestCombinationUnder = sumNumbersOfDigitsFromTheClosestCombinationUnder();
        return getTheClosest(numberOfTheClosestCombinationAbove, numberTheClosestCombinationUnder);

    }

    private Integer nextDigitValue() {
        if (calledFirstTime) calledFirstTime = false;
        else {
            actualDigit--;
        }
        int countOfDigits = requiredCombination.length();
        int positionOfDigitInString = countOfDigits - actualDigit;
        String digitValue;
        if (positionOfDigitInString == countOfDigits) {
            digitValue = requiredCombination.substring(positionOfDigitInString);
        } else {
            digitValue = requiredCombination.substring(positionOfDigitInString, positionOfDigitInString + 1);
        }
        return Integer.parseInt(digitValue);
    }

    private Boolean isAllCombinationIsPossibleToDial() {
        for (String requiredDigit : requiredCombination.split("")) {
            if (!workableButtons.contains(requiredDigit)) return false;
        }
        return true;
    }

    private Integer sumNumbersOfDigitsFromTheClosestCombinationAbove() {
        Integer valueOfDigit = nextDigitValue();
        if (workableButtons.contains(String.valueOf(valueOfDigit)))
            return toNumber(valueOfDigit) + sumNumbersOfDigitsFromTheClosestCombinationAbove();
        else {
            Integer numberFromTheClosestCombinationAbove = toNumber(findExistingValueOfButtonAbove(valueOfDigit));
            while (actualDigit > 1) {
                actualDigit--;
                numberFromTheClosestCombinationAbove += toNumber(addTheMinimalNumbers());
            }
            calledFirstTime = true;
            actualDigit = requiredCombination.length();
            return numberFromTheClosestCombinationAbove;
        }
    }

    private Integer sumNumbersOfDigitsFromTheClosestCombinationUnder() {
        Integer valueOfDigit = nextDigitValue();
        if (workableButtons.contains(String.valueOf(valueOfDigit)))
            return toNumber(valueOfDigit) + sumNumbersOfDigitsFromTheClosestCombinationUnder();
        else {
            Integer numberOfTheClosestCombinationUnder = toNumber(findExistingValueOfButtonUnder(valueOfDigit));
            while (actualDigit > 1) {
                actualDigit--;
                numberOfTheClosestCombinationUnder += toNumber(addTheMaximalNumbers());
            }
            calledFirstTime = true;
            actualDigit = requiredCombination.length();
            return numberOfTheClosestCombinationUnder;
        }
    }

    private Integer toNumber(Integer valueOfDigit) {
        int digitDegree = actualDigit - 1;
        return valueOfDigit * (int) Math.pow(10, digitDegree);
    }

    private Integer getTheClosest(Integer above, Integer under) {

        if (isTheUnderResultValid(under)) return above;
        Integer requiredCombinationNumber = Integer.parseInt(requiredCombination);
        Integer differenceBetweenAboveAndRequired = above - requiredCombinationNumber;
        Integer differenceBetweenUnderAndRequired = requiredCombinationNumber - Math.abs(under);
        return differenceBetweenUnderAndRequired <= differenceBetweenAboveAndRequired ? under : above;
    }

    private boolean isTheUnderResultValid(Integer theUnderResult) {
        return (theUnderResult < 0 ||  theUnderResult == 0&&!workableButtons.contains("0") ) ;
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
