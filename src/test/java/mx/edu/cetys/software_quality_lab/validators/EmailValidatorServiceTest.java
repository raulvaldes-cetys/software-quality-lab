package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorServiceTest {

    @Test
    void shouldReturnFalseWhenEmailIsNull() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid(null));
    }

    @Test
    void shouldReturnFalseWhenEmailIsEmpty() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid(""));
    }

    @Test
    void shouldReturnFalseWhenEmailHasUppercase() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("User4#provider.co"));
    }

    @Test
    void shouldReturnFalseWhenEmailHasInvalidCharacter() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("u4ser?name#provider.co"));
    }

    @Test
    void shouldReturnTrueWhenEmailHasOnlyValidCharacters() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertTrue(emailValidator.isValid("u4ser.name#provider.co"));
    }

    // #2 Caracteres especiales en user: . - _ +
    @Test
    void shouldReturnTrueWhenUserHasAllowedSpecials() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertTrue(emailValidator.isValid("u.4s-er_name+tag#provider.co"));
    }

    // #2.1 Solo . en provider
    @Test
    void shouldReturnFalseWhenProviderHasInvalidSpecial() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("u4ser#pro-vider.co"));
    }

    // #3 Separador # obligatorio, exactamente uno
    @Test
    void shouldReturnFalseWhenNoSeparator() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("u4serprovider.co"));
    }

    @Test
    void shouldReturnFalseWhenTwoSeparators() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("u4#ser#provider.co"));
    }

    @Test
    void shouldReturnTrueWhenExactlyOneSeparator() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertTrue(emailValidator.isValid("u4ser#provider.co"));
    }

    // #4 No dos vocales consecutivas
    @Test
    void shouldReturnFalseWhenTwoConsecutiveVowels() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("u4sea#provider.co"));
    }

    @Test
    void shouldReturnTrueWhenNoConsecutiveVowels() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertTrue(emailValidator.isValid("u4srx#prov.co"));
    }

    // #5 Dominio (después del último .): longitud 1 a 5
    @Test
    void shouldReturnFalseWhenDomainTooLong() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("u4ser#provider.longdom"));
    }

    @Test
    void shouldReturnFalseWhenDomainEmpty() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("u4ser#provider."));
    }

    @Test
    void shouldReturnTrueWhenDomainLengthValid() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertTrue(emailValidator.isValid("u4ser#provider.co"));
    }

    // #6 Longitud total máxima 47
    @Test
    void shouldReturnFalseWhenEmailTooLong() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String longEmail = "a4".repeat(22) + "#x.co"; // 44 + 5 = 49 caracteres
        assertFalse(emailValidator.isValid(longEmail));
    }

    @Test
    void shouldReturnTrueWhenEmailWithinMaxLength() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        String okEmail = "u4ser#provider.co";
        assertTrue(okEmail.length() <= 47);
        assertTrue(emailValidator.isValid(okEmail));
    }

    // #7 Debe contener al menos un 4
    @Test
    void shouldReturnFalseWhenNoDigitFour() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertFalse(emailValidator.isValid("user#provider.co"));
    }

    @Test
    void shouldReturnTrueWhenContainsFour() {
        EmailValidatorService emailValidator = new EmailValidatorService();
        assertTrue(emailValidator.isValid("u4ser#provider.co"));
    }
}

