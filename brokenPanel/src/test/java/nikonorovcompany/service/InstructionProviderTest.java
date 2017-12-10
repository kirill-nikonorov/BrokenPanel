package nikonorovcompany.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.nikonorovcompany.service.InstructionProvider;
import ru.nikonorovcompany.service.InstructionProviderCreator;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class InstructionProviderTest {
    private String inputString;
    private Integer expected;

    @Parameterized.Parameters(name = "{index}: условие : ({0}) ожидается ={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0 0", 1},
                {"9 9", 8},
                {"99 9", 100},
                {"101 0", 99},
                {"101 0,9", 111},
                {"111 0,1,2", 99},
                {"151 1,5", 200},
                {"1101 0,9", 1111},
                {"1113 1,2", 999},
                {"25 0,2,3,4,5,6,7,8,9", 11},
                {"1567 6,7", 1559},
                {"159753 7,9", 160000},
                {"159753 6,7,9", 158888},
                {"159753 4,5,6,7,9", 180000},
                {"99999 1,2,9,", 88888},
        });
    }

    public InstructionProviderTest(String inputString, int expected) {
        this.inputString = inputString;
        this.expected = expected;
        ;
    }

    @Test
    public void paramTest() {
        InstructionProvider provider = new InstructionProviderCreator(inputString.split(" ")).createInstructionProvider();
        assertEquals(expected, provider.getNumberOfTheClosestCombinationResult());
    }
}