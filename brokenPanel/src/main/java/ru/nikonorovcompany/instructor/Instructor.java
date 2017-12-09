package ru.nikonorovcompany.instructor;

import ru.nikonorovcompany.service.InstructionProvider;
import ru.nikonorovcompany.service.InstructionProviderCreator;

public class Instructor {
    public static void main(String[] args) {

        String[] inputData = "2555 5".split(" ");

        InstructionProvider provider = new InstructionProviderCreator(inputData).createInstructionProvider();

        System.out.println("требуемая комбинация = " + inputData[0]);
        System.out.println("ближайшая комбинация = " + provider.getTheClosestCombination());
        System.out.println("количество итераций к ней = " + provider.getTheCountOfIterationsToRequiredCombination());
    }
}
