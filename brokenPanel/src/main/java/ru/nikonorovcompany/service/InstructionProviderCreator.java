package ru.nikonorovcompany.service;

import java.util.Arrays;
import java.util.Iterator;

public class InstructionProviderCreator {
    private String[] inputData;

    public InstructionProviderCreator(String[] inputData) {
        this.inputData = inputData;
    }

    public InstructionProvider createInstructionProvider() {
        String workableButtons = initializeButtons();
        String requiredCombination = inputData[0];
        Integer actualDigit = requiredCombination.length();
        return new InstructionProvider(workableButtons, requiredCombination, actualDigit);
    }

    private Iterator<String> extractIteratorWithRequiredDigitValues() {
        return Arrays.asList(inputData[0].split("")).iterator();
    }

    private String initializeButtons() {
        String allButtons = "0123456789";
        return deleteBrokenButtons(allButtons);
    }

    private String deleteBrokenButtons(String workableButtons) {
        String[] brokenButtons = inputData[1].split(",");
        for (String brokenButton : brokenButtons) {
            if (workableButtons.contains(brokenButton)) workableButtons = workableButtons.replaceAll(brokenButton, "");
        }
        return workableButtons;
    }
}
