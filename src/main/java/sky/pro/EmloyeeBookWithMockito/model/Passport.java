package sky.pro.EmloyeeBookWithMockito.model;

import java.util.Objects;

public class Passport {

    private final int passportNumber;

    public Passport(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport passport)) return false;
        return getPassportNumber() == passport.getPassportNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassportNumber());
    }

    @Override
    public String toString() {
        return "Passport{" +
                "passportNumber=" + passportNumber +
                '}';
    }
}
