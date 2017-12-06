public class Main {
    public static void main(String[] args) {

        String[] attempt = "555 5".split(" ");
        BrokenPanel panel = new BrokenPanelCreator(attempt).createBrokenPanel();
        panel.compute();


    }
}
