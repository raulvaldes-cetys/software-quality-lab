package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParametizedCalculatorTest {
    // @test
    @DisplayName("Test with CVS Source")
    @ParameterizedTest
    @CsvSource({
            "10,10,20",
            "0,0,0",
            "-5,5,0"
    })
    void testSumWithCsvSource(int a, int b, int expected) {
        // arrange
        // act
        var sum = a + b;
        assertEquals(expected, sum);
    }

    final String nullString = null;

    @DisplayName("Validate String not empty") //null o vacia
    @ParameterizedTest
    @ValueSource(strings = {
            "hello",
            "world",
            "ximena"
    })
    void testValidateStringNotEmpty(String values) {
        var isNotEmpty = values.isEmpty();
        assertFalse(isNotEmpty);
    }

    @ParameterizedTest
    @DisplayName("Validate double of an interger with MethodSourrce")
    @MethodSource("provideNumbers")
    void testDouble(int a, int expected) {
        // act
        var doubleValue = a * 2;
        assertEquals(expected, doubleValue);
    }

    public static Stream<Object[]> provideNumbers() {
        return Stream.of(
                new Object[]{2, 4},
                new Object[]{5, 10}
                // new Object[]{"Hola", "HolaHola"}
        );
    }

    @ParameterizedTest
    @DisplayName("Validate pet is older than 10 years old")
    @MethodSource("providePets")
    void testDouble(Pet pet, boolean expected) {
        // act
        var isOlderThanTen = pet.age() > 10 ? true : false;
        assertEquals(expected, isOlderThanTen);
    }
    public static Stream<Object[]> providePets() {
        return Stream.of(
                new Object[]{new Pet("original", "negrito", 15, "Perrito"), true},
                new Object[]{new Pet(null, null, 10, null), false},
                new Object[]{new Pet(null, null, 9, null), false},
                new Object[]{new Pet(null, null, 11, null), true}
                // new Object[]{"Hola", "HolaHola"}
        );
    }
    // pojo - plain old java object: clase con getters y setters
    // records - pojo inmutable sin boilerplate
    private record Pet(String name, String color, int age, String race){}
}

