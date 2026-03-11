package mx.edu.cetys.software_quality_lab.validators;

import mx.edu.cetys.software_quality_lab.validators.EmailValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoListTest {
    @Mock
    List<String> mockList;
    @Mock
    EmailValidatorService mockEmailValidator;

    @Test
    void shouldReturnCustomSizeWhenMocked(){
        when(mockList.size()).thenReturn(67).thenReturn(3).thenReturn(99).thenThrow(new RuntimeException());

        assertEquals(67,mockList.size());
        assertEquals(3,mockList.size());
        assertEquals(99,mockList.size());
        assertThrows(RuntimeException.class,() -> mockList.size());
    }

    @Test
    void shouldMockListWithParameters(){
        when(mockList.get(0)).thenReturn("Hello").thenReturn("Profe").thenReturn("Cetys");
        when(mockList.get(1)).thenReturn("World");

        assertEquals("Hello",mockList.get(0));
        assertEquals("World",mockList.get(1));
        assertEquals("Profe",mockList.get(0));
        assertEquals("Cetys",mockList.get(0));
    }

    @Test
    void shouldMockEmailValidatorWithArgumentMatchers(){
        when(mockEmailValidator.isValid(anyString())).thenReturn(true);
        when(mockEmailValidator.isValid(isNull())).thenReturn(true);

        assertTrue(mockEmailValidator.isValid(null));
        assertTrue(mockEmailValidator.isValid("a"));
    }
}
