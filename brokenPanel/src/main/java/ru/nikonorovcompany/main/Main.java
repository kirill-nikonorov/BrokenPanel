package ru.nikonorovcompany.main;

import ru.nikonorovcompany.service.InstructionProvider;
import ru.nikonorovcompany.service.InstructionProviderCreator;

public class Main {
    public static void main(String[] args) {

        String[] inputData = "555555 0,1,2,3,4,5,6,7,8".split(" ");

        InstructionProvider provider = new InstructionProviderCreator(inputData).createInstructionProvider();
        System.out.println(provider.findCombination());


    }
}
