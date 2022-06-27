package ch11;

import java.util.Optional;

public class Car {
    private String description = "MY CAR";
    String name = null;
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "description='" + description + '\'' +
                '}';
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = Optional.ofNullable(insurance);

    }
}
