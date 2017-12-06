import java.util.ArrayList;
import java.util.Arrays;

public class BrokenPanelCreator {
    private String[] args;

    public BrokenPanelCreator(String[] args) {
        this.args = args;
    }

    public BrokenPanel createBrokenPanel() {
        Integer[] requiredChannelsNumbers = extractRequiredNumbers();
        String[] allButtons = initializeAllButtons();

        return new BrokenPanel(allButtons, requiredChannelsNumbers);
    }

    private Integer[] extractRequiredNumbers() {
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(args[0].split("")).forEach(s -> list.add(Integer.parseInt(s)));
        return  list.toArray(new Integer[list.size()]);
    }

    private String[] initializeAllButtons() {
        String[] allButtons = new String[10];
        for (int i = 0; i < 10; i++) {
            allButtons[i] = "on";
        }
        return deleteBrokenButtons(allButtons);
    }

    private String[] deleteBrokenButtons(String[] allButtons) {
        Arrays.stream(args[1].split(",")).map(Integer::parseInt).forEach(brokenButton ->
                allButtons[brokenButton] = "off");
        return allButtons;

    }
}
