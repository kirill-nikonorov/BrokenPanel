import org.junit.Assert;
import org.junit.Test;
import ru.nikonorovcompany.service.BrokenPanelCreator;

import static junit.framework.Assert.assertEquals;


public class BrokenPanelTest {
    @Test
    public void shouldFindTheClosestCombinationWithOneBrokenButton() {
        String[] inputData = "153 1".split(" ");
        int result = new BrokenPanelCreator(inputData).createBrokenPanel().findCombination();
        assertEquals(200, result);
    }
    @Test
    public void shouldFindTheClosestCombinationWithTwoBrokenButton() {
        String[] inputData = "153 1,5".split(" ");
        int result = new BrokenPanelCreator(inputData).createBrokenPanel().findCombination();
        assertEquals(200, result);
    }
    @Test
    public void shouldFindTheClosestCombinationThatMachMoreSmallerThenRequiredNumber() {
        String[] inputData = "1113 1,2".split(" ");
        int result = new BrokenPanelCreator(inputData).createBrokenPanel().findCombination();
        assertEquals(999, result);
    }
    @Test
    public void shouldFindTheClosestCombinationWhenRequiredNumberIsZeroAndZerIsTheBrokenButton() {
        String[] inputData = "0 0".split(" ");
        int result = new BrokenPanelCreator(inputData).createBrokenPanel().findCombination();
        assertEquals(1, result);
    }


}
