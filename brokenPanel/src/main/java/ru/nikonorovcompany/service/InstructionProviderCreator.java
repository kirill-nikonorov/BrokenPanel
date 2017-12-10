package ru.nikonorovcompany.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionProviderCreator {
    private String[] inputData;

    public InstructionProviderCreator(String[] inputData) {
        this.inputData = inputData;
    }

    public InstructionProvider createInstructionProvider() {
        String workableButtons = initializeButtons();
        Integer requiredCombination = Integer.parseInt(inputData[0]);
        return new InstructionProvider(workableButtons, requiredCombination);
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
