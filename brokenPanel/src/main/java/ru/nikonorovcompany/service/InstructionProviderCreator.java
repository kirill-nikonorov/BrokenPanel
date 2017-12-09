package ru.nikonorovcompany.service;

import ru.nikonorovcompany.iterator.ResettableIntegerIterator;
import ru.nikonorovcompany.iterator.IntegerIterator;

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
        String requiredCombination = inputData[0];
        ResettableIntegerIterator iterator = new IntegerIterator(extractDigitsValues());
        return new InstructionProvider(workableButtons, requiredCombination, iterator);
    }

    private Integer[] extractDigitsValues() {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(inputData[0].split("")).map(Integer::parseInt).forEach(list::add);
        return list.toArray(new Integer[0]);
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
