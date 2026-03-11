package mx.edu.cetys.software_quality_lab.validators;

public class EmailValidatorService {
    private static final String VOWELS = "aeiou";
    private static final String USER_SPECIALS = ".-_+";
    private static final int MAX_TOTAL_LENGTH = 47;
    private static final int MIN_DOMAIN_LENGTH = 1;
    private static final int MAX_DOMAIN_LENGTH = 5;

    public boolean isValid(String email) {
        if (email == null || email.isEmpty()) return false;
        if (!hasValidTotalLength(email)) return false;
        if (!containsFour(email)) return false;
        if (!hasExactlyOneSeparator(email)) return false;

        String[] parts = email.split("#", -1);
        String user = parts[0];
        String provider = parts[1];

        if (!userHasValidCharacters(user)) return false;
        if (!providerHasValidCharacters(provider)) return false;
        if (!hasValidDomainLength(provider)) return false;
        if (!hasNoConsecutiveVowels(email)) return false;

        return true;
    }

    private boolean hasValidTotalLength(String email) {
        return email.length() <= MAX_TOTAL_LENGTH;
    }

    private boolean containsFour(String email) {
        return email.indexOf('4') >= 0;
    }

    private boolean hasExactlyOneSeparator(String email) {
        int count = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '#') count++;
        }
        return count == 1;
    }

    private boolean userHasValidCharacters(String user) {
        for (int i = 0; i < user.length(); i++) {
            char c = user.charAt(i);
            if (isLetterOrDigit(c)) continue;
            if (USER_SPECIALS.indexOf(c) >= 0) continue;
            return false;
        }
        return true;
    }

    private boolean providerHasValidCharacters(String provider) {
        for (int i = 0; i < provider.length(); i++) {
            char c = provider.charAt(i);
            if (isLetterOrDigit(c)) continue;
            if (c == '.') continue;
            return false;
        }
        return true;
    }

    private boolean hasValidDomainLength(String provider) {
        int lastDot = provider.lastIndexOf('.');
        if (lastDot < 0) return false;
        String domain = provider.substring(lastDot + 1);
        int len = domain.length();
        return len >= MIN_DOMAIN_LENGTH && len <= MAX_DOMAIN_LENGTH;
    }

    private boolean hasNoConsecutiveVowels(String email) {
        for (int i = 0; i < email.length() - 1; i++) {
            if (isVowel(email.charAt(i)) && isVowel(email.charAt(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean isLetterOrDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
    }

    private boolean isVowel(char c) {
        return VOWELS.indexOf(c) >= 0;
    }
}
