package core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundUpCalculatorTest {
    RoundUpCalculator calculator;

    @Test
    public void testWhenAddingSmallestPossibleTwoDecimalRemainders_ReturnsPointZeroTwo() {
        List<Integer> input = new ArrayList<>();
        input.add(199);
        input.add(299);
        calculator = new RoundUpCalculator(input);
        assertEquals(2, calculator.getResult());
    }

    @Test
    public void testWhenAddingLargestPossibleTwoDecimalRemainders_ReturnsOnePointNineEight() {
        List<Integer> input = new ArrayList<>();
        input.add(101);
        input.add(201);
        calculator = new RoundUpCalculator(input);
        assertEquals(198, calculator.getResult());
    }

    @Test
    public void testWhenAddingWholeNumbers_ReturnsZero() {
        List<Integer> input = new ArrayList<>();
        input.add(100);
        input.add(200);
        calculator = new RoundUpCalculator(input);
        assertEquals(0, calculator.getResult());
    }
}
