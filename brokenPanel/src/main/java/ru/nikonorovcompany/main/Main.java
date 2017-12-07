package ru.nikonorovcompany.main;

import ru.nikonorovcompany.service.BrokenPanel;
import ru.nikonorovcompany.service.BrokenPanelCreator;

public class Main {
    public static void main(String[] args) {

        String[] inputData = "300 1,2,3,4,5,6,7,-8".split(" ");

        BrokenPanel panel = new BrokenPanelCreator(inputData).createBrokenPanel();
        System.out.println(panel.findCombination());


    }
}
