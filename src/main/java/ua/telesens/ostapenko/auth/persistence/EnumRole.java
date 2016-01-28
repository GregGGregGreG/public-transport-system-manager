package ua.telesens.ostapenko.auth.persistence;

/**
 * @author root
 * @since 26.01.16
 */
public enum EnumRole {
    USER("ROLE_USER");

    private final String name;

    EnumRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TodoRole{" +
                "name='" + name + '\'' +
                '}';
    }
}
