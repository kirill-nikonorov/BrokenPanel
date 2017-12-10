import org.junit.Test;
import ru.nikonorovcompany.service.InstructionProviderCreator;

import static junit.framework.Assert.assertEquals;


public class InstructionProviderTest {
    @Test
    public void shouldFindTheClosestCombinationWithOneBrokenButton() {
        String[] inputData = "153 1".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(200, result);
    }

    @Test
    public void shouldFindTheClosestCombinationWithTwoBrokenButton() {
        String[] inputData = "153 1,5".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(200, result);
    }

    @Test
    public void shouldFindTheClosestCombinationThatMachMoreSmallerThenRequiredNumber() {
        String[] inputData = "1113 1,2".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(999, result);
    }

    @Test
    public void shouldFindTheClosestCombinationWhenRequiredNumberIsZeroAndZerIsTheBrokenButton() {
        String[] inputData = "0 0".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(1, result);
    }

    @Test
    public void shouldFindTheClosestCombinationWhenOnlyOneVariantIsReachable() {
        String[] inputData = "25 0,2,3,4,5,6,7,8,9".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(11, result);
    }

    @Test
    public void shouldFindTheClosestCombinationToAnotherVariant() {
        String[] inputData = "1567 6,7".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(1559, result);
    }

    @Test
    public void shouldFindTheClosestCombinationToAnotherVariant2() {
        String[] inputData = "159753 7,9".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(160000, result);
    }

    @Test
    public void shouldFindTheClosestCombinationToAnotherVariant3() {
        String[] inputData = "159753 7,9,6".split(" ");
        int result = new InstructionProviderCreator(inputData).createInstructionProvider().getNumberOfTheClosestCombinationResult();
        assertEquals(160000, result);
    }


}
