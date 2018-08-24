package ryber;


import org.springframework.core.env.PropertySource;

public final class EncryptedProperties extends PropertySource {

    public EncryptedProperties(String name) {
        super(name);
    }

    public EncryptedProperties(String name, String source) {
        super(name, source);
    }


    @Override
    public String getProperty(String name) {
        return decode((String) super.getSource());
    }

    private String decode(String encodedValue) {
        if (isEncryptedValue(encodedValue)) {
            return decrypt(encodedValue);
        } else {
            return encodedValue;
        }
    }

    private String decrypt(String encodedValue) {
        System.out.print("[[DECRYPTING]] " + encodedValue);
        String value = unwrap(encodedValue);
        System.out.println(" => " + value);
        return value;
    }

    public boolean isEncryptedValue(String value) {
        if (value == null) {
            return false;
        } else {
            String trimmedValue = value.trim();
            return trimmedValue.startsWith("ENC(") && trimmedValue.endsWith(")");
        }
    }

    String unwrap(String value) {
        return value.substring("ENC(".length(), value.length() - ")".length());
    }
}
