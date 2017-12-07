package ru.nikonorovcompany.service;

import java.util.Iterator;

public class BrokenPanel {

    private String allButtons;
    private Iterator<String> iterator;

    public BrokenPanel(String allButtons, Iterator<String> iterator) {
        this.allButtons = allButtons;
        this.iterator = iterator;
    }

    public Integer findCombination() {
        return Integer.parseInt(tryToBuildPreciseCombination(iterator.next()));
    }

    private String tryToBuildPreciseCombination(String digit) {
        if (allButtons.contains(digit)) {
            if (iterator.hasNext()) {
                return digit + tryToBuildPreciseCombination(iterator.next());
            } else {
                return digit;
            }
        }
        return complementCombination(digit);
    }

    private String complementCombination(String digit) {
        String theClosestCombinationAbove = findTheClosestNumberAbove(digit);
        String theClosestCombinationUnder = findTheClosestNumberUnder(digit);
        while (iterator.hasNext()) {
            theClosestCombinationAbove = theClosestCombinationAbove != null ? theClosestCombinationAbove + addTheMinimalNumbers() : null;
            theClosestCombinationUnder = theClosestCombinationUnder != null ? theClosestCombinationUnder + addTheMaximalNumbers() : null;
            digit += iterator.next();
        }

        return handelResults(theClosestCombinationAbove, theClosestCombinationUnder, digit);
    }

    private String handelResults(String above, String under, String remainingPartOfCombination) {
        if (above != null && under != null)
            return getTheClosest(above, under, remainingPartOfCombination);
        if (above != null) return above;
        if (under != null) return under;
        else throw new RuntimeException("\n-------\nВсе кнопки нерабочие !\n-------\n");

    }

    private String getTheClosest(String above, String under, String remainingPartOfCombination) {
        Integer ab = Integer.parseInt(above);
        Integer un = Integer.parseInt(under);
        Integer rem = Integer.parseInt(remainingPartOfCombination);
        Integer differenceBetweenAboveAndRequired = (Math.abs(ab - rem));
        Integer differenceBetweenUnderAndRequired = (Math.abs(un - rem));
        return differenceBetweenAboveAndRequired < differenceBetweenUnderAndRequired ? above : under;
    }

    private String findTheClosestNumberAbove(String digit) {
        int theLowerLimit = Integer.parseInt(digit) + 1;
        String concreteButton;
        for (int i = theLowerLimit; i <= 9; i++) {
            concreteButton = String.valueOf(i);
            if (allButtons.contains(concreteButton)) {
                return concreteButton;
            }
        }

        return null;
    }

/*    private String checkForZero(String concreteButton) {
        if (concreteButton == "0") return "";
        return concreteButton;
    }*/

    private String findTheClosestNumberUnder(String digit) {
        int theUpperLimit = Integer.parseInt(digit) - 1;
        String concreteButton;
        for (int i = theUpperLimit; i >= 0; i--) {
            concreteButton = String.valueOf(i);
            if (i == 0) return "";
            if (allButtons.contains(concreteButton)) {
                return concreteButton;
            }

        }
        return null;
    }

    private String addTheMaximalNumbers() {
        int max = 0;
        int buttonValue;
        for (String button : allButtons.split("")) {
            buttonValue = Integer.parseInt(button);
            if (buttonValue > max) max = buttonValue;
        }
        return String.valueOf(max);

    }


    private String addTheMinimalNumbers() {
        int min = 9;
        int buttonVlue;
        for (String button : allButtons.split("")) {
            buttonVlue = Integer.parseInt(button);
            if (buttonVlue < min) min = buttonVlue;
        }
        return String.valueOf(min);

    }


}
