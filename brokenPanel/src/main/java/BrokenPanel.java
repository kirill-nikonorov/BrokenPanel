import java.util.Arrays;

public class BrokenPanel {

    private String[] allButtons;
    private Integer[] requiredChannelNumbers;

    public BrokenPanel(String[] allButtons, Integer[] requiredChannelNumbers) {
        this.allButtons = allButtons;
        this.requiredChannelNumbers = requiredChannelNumbers;
    }

    public void compute() {
        Integer theNearestCombination = findTheNearestCombination();
        System.out.println(theNearestCombination);
    }


    private Integer findTheNearestCombination() {

        StringBuilder theNearestCombinationAboveOfRequiredNumber = new StringBuilder();
        StringBuilder theNearestCombinationUnderOfRequiredNumber = new StringBuilder();

        for (Integer requiredNumber : requiredChannelNumbers) {
            theNearestCombinationAboveOfRequiredNumber.append(findTheNearestUnder(requiredNumber));
            theNearestCombinationUnderOfRequiredNumber.append(findTheNearestAbove(requiredNumber));
        }

        return findTheClosestNumber(theNearestCombinationAboveOfRequiredNumber.toString(), theNearestCombinationUnderOfRequiredNumber.toString());
    }

    private Integer findTheClosestNumber(String aboveString, String underString) {
        Integer above = Integer.parseInt(aboveString);
        Integer under = Integer.parseInt(underString);

        String requiredString = Arrays.stream(requiredChannelNumbers).map(i -> String.valueOf(i)).reduce((s1, s2) -> s1 + s2).get();
        Integer required = Integer.parseInt(requiredString);

        return getTheClosest(required , above , under);
    }
    private Integer getTheClosest(Integer required , Integer above , Integer under)
    {
        Integer differenceBeetweenAboveAndRequired = (Math.abs(above -required));
        Integer differenceBeetweenUnderAndRequired = (Math.abs(under -required));
        return differenceBeetweenAboveAndRequired>differenceBeetweenUnderAndRequired ? under : above;
    }


    private String findTheNearestUnder(Integer requiredNumber) {
        String theNearestButtonUnder = "";
        String buttonNumber;
        for (int i = 0; i < requiredNumber; i++) {
            buttonNumber = allButtons[i];
            if (!buttonNumber.equals("off")) theNearestButtonUnder = String.valueOf(i);
        }
        return theNearestButtonUnder;

    }

    private String findTheNearestAbove(Integer requiredNumber) {
        String theNearestButtonAbove = "";
        String buttonNumber;
        for (int i = 9; i >= requiredNumber; i--) {
            buttonNumber = allButtons[i];
            if (!buttonNumber.equals("off")) theNearestButtonAbove = String.valueOf(i);
        }
        return theNearestButtonAbove;

    }


   /* private String findTheNearestNumbers() {
        StringBuilder theNearestCombinationAboveRequiredNumber = new StringBuilder();
        String theNearestButtonAbove = "";
        for (int i = 0; i < requiredChannelNumbers.length; i++) {
            Integer requiredNumber = Integer.valueOf(requiredChannelNumbers[i]);
            String buttonNumber;
            for (int j = 9; j >= requiredNumber; j--) {
                buttonNumber = allButtons[j];
                if (buttonNumber != null && j >= requiredNumber) theNearestButtonAbove += buttonNumber;
            }
            theNearestCombinationAboveRequiredNumber.append(theNearestButtonAbove);
        }
        return theNearestCombinationAboveRequiredNumber.toString();
    }*/


}
