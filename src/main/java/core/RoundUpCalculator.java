package core;

import java.util.List;

public class RoundUpCalculator {
    private Integer result = 0;
    public RoundUpCalculator(List<Integer> transactions) {
        result = calculateRoundUpAmount(transactions);
    }

    private Integer calculateRoundUpAmount(List<Integer> transactions) {
        int calculatedValue = 0;
        for(int value : transactions) {
            String valueAsString = String.valueOf(value);
            int pennies = Integer.parseInt(valueAsString.substring(valueAsString.length()-2));
            if (pennies > 0) {
                calculatedValue += 100 - pennies;
            }
        }
        return calculatedValue;
    }

    public Integer getResult() {
        return result;
    }
}
