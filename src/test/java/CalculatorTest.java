import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator sut;

    @BeforeEach
    public void init(){
        System.out.println("test started");
        sut = Calculator.calculatorSupplier.get();
    }

    @BeforeAll
    public static void started(){
        System.out.println("tests started");
    }

    @AfterEach
    public void finished(){
        System.out.println("test completed");
        sut = null;
    }

    @AfterAll
    public static void finishedAll(){
        System.out.println("tests completed");
    }


    @ParameterizedTest
    @ValueSource(doubles = {1, -3, 0, -2.5})
    public void absTest(double x){

        boolean actual = sut.abs.apply(x) >= 0;

        assertTrue(actual);
    }

    @Test
    public void zeroDivision(){
        double x = 1, y = 0, expected = 0;

        double actual = sut.division.apply(x, y);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    public void nullDivision(Double argument){
        double y = 1;

        var expected = NullPointerException.class;

        assertThrowsExactly(expected,
                () -> sut.division.apply(argument, y));
    }

    @ParameterizedTest
    @MethodSource("sourceForDivision")
    public void divisionTest(double x, double y, double expected){

        double actual = sut.division.apply(x,y);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> sourceForDivision(){
        return Stream.of(Arguments.of(5,2,2.5), Arguments.of(10,5,2), Arguments.of(5.6,2.8,2));
    }

    @ParameterizedTest
    @MethodSource("sourceForMultiply")
    public void multiplyTest(double x, double y, double expected){

        double actual = sut.multiply.apply(x,y);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> sourceForMultiply(){
        double y = 3.1;
        return Stream.of(Arguments.of(2.8,y,8.68), Arguments.of(4.6,y,14.26), Arguments.of(5.8,y,17.98));
    }

}
