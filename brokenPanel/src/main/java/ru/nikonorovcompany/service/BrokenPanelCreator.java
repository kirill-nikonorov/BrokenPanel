package ru.nikonorovcompany.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BrokenPanelCreator {
    private String[] args;

    public BrokenPanelCreator(String[] args) {
        this.args = args;
    }

    public BrokenPanel createBrokenPanel() {
        String allButtons = initializeAllButtons();
        Iterator<String> iterator = extractIteratorWithRequiredNubmers();

        return new BrokenPanel(allButtons , iterator );
    }

    private Iterator<String> extractIteratorWithRequiredNubmers() {
        List<String> list = new ArrayList<>();
        list = Arrays.asList(args[0].split(""));
        Iterator<String> iterator = list.iterator();
        return iterator;
    }

    private String initializeAllButtons() {
        String allButtons = "0123456789";
        return deleteBrokenButtons(allButtons);
    }

    private String deleteBrokenButtons(String allButtons) {
        for (String brokenButton : args[1].split(",")) {
            if (allButtons.contains(brokenButton)) allButtons = allButtons.replaceAll(brokenButton, "");
        }
        return allButtons;
    }
}
