package ru.nikonorovcompany.main;

import ru.nikonorovcompany.service.InstructionProvider;
import ru.nikonorovcompany.service.InstructionProviderCreator;

public class Main {
    public static void main(String[] args) {

        String[] inputData = "20 2,0".split(" ");

        InstructionProvider provider = new InstructionProviderCreator(inputData).createInstructionProvider();
        System.out.println("требуемая комбинация = " + inputData[0]);
        System.out.println("ближайшая комбинация = " + provider.findCombination());
        System.out.println("количество итераций к ней = " + provider.findTheCountOfIterationsToRequiredNutton());
    }
}
