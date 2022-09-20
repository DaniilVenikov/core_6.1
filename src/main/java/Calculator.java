import java.util.function.*;

public class Calculator {
    static Supplier<Calculator> calculatorSupplier = Calculator::new;

    BinaryOperator<Double> plus = (x, y) -> x + y;
    BinaryOperator<Double> minus = (x, y) -> x - y;
    BinaryOperator<Double> multiply = (x, y) -> x * y;
    BinaryOperator<Double> division = (x, y) -> y > 0 ? (x / y) : 0;

    UnaryOperator<Double> power = x -> x * x;
    UnaryOperator<Double> abs = x -> x > 0 ? x : x * -1;

    Predicate<Integer> isPositive = x -> x > 0;

    Consumer<Integer> println = System.out::println;
}
