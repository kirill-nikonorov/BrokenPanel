package ru.nikonorovcompany.instructor;

import ru.nikonorovcompany.service.InstructionProvider;
import ru.nikonorovcompany.service.InstructionProviderCreator;

public class Instructor {
    public static void main(String[] args) {

        if (args.length==0){
            args = "2555 5".split(" ");
        }

        InstructionProvider provider = new InstructionProviderCreator(args).createInstructionProvider();

        System.out.println("требуемая комбинация = " + args[0]);
        System.out.println("ближайшая комбинация = " + provider.getNumberOfTheClosestCombinationResult());
        System.out.println("количество итераций к ней = " + provider.getTheCountOfIterationsToRequiredCombination());
    }
}
