import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BullOrBear {

    public static List<Pair<String, String>> bullOrBear(List<Double> data, double threshold) {
        List<Pair<String, String>> changeInTrend = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            double currentValue = data.get(i);
            double previousValue = data.get(i - 1);
            double valueChange = currentValue - previousValue;

            if (Math.abs(valueChange) >= threshold) {
                if (valueChange > 0) {
                    changeInTrend.add(new Pair<>("Increasing", Double.toString(data.get(i))));
                } else {
                    changeInTrend.add(new Pair<>("Decreasing", Double.toString(data.get(i))));
                }
            }
        }

        return changeInTrend;
    }

    public static void main(String[] args) {
        List<Double> data = new ArrayList<>();

        // Read data from CSV file and populate the 'data' list
        try (BufferedReader br = new BufferedReader(new FileReader("BTC-USD.csv"))) {
            String line;
            br.readLine(); // skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(Double.parseDouble(values[4])); // Assuming 'Close' column is at index 4
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double threshold = 5.0;
        List<Pair<String, String>> swings = bullOrBear(data, threshold);

        // Print detected direction changes
        for (Pair<String, String> change : swings) {
            System.out.println("Direction: " + change.getFirst() + ", Value: " + change.getSecond());
        }
    }

    static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }
}
