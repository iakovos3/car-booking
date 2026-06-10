package com.iakovos.car;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class CarDao {

    private Car[] cars = {
            new Car(UUID.fromString("b9e1e851-80d6-49be-8c64-d55e4cdbd4d8"),
                    "KBT-4821",
                    BigDecimal.valueOf(40.00),
                    Brand.AUDI,
                    "A6",
                    false
            ),
            new Car(UUID.fromString("ff50a75b-3b6b-4d98-894b-f580b82ffea8"),
                    "LMX-7364",
                    BigDecimal.valueOf(90.00),
                    Brand.BMW,
                    "iX",
                    true
            ),
            new Car(UUID.fromString("00007cd5-6b43-4d43-9fe6-221d6e8c90f7"),
                    "WPJ-1593",
                    BigDecimal.valueOf(40.00),
                    Brand.MERCEDES,
                    "C-Class",
                    false
            ),
            new Car(UUID.fromString("4846760d-682f-413b-83f0-25651234cc04"),
                    "GRN-8047",
                    BigDecimal.valueOf(40.00),
                    Brand.TESLA,
                    "Model 3",
                    true
            ),
            new Car(UUID.fromString("de2706d5-6dcc-40ea-85be-af81997f98a9"),
                    "ZKD-2716",
                    BigDecimal.valueOf(40.00),
                    Brand.TOYOTA,
                    "Corolla",
                    false
            )

    };

    public Car[] findAllCars() {
        return this.cars;
    }

    public Car[] findAllElectricCars() {
        int numOfElectricCars = 0;
        for (Car c : cars) {
            if (c.isElectric()) {
                numOfElectricCars++;
            }
        }

        Car[] electricCars = new Car[numOfElectricCars];
        int electricCarsIndex = 0;
        for (Car c : cars) {
            if (c.isElectric()) {
                electricCars[electricCarsIndex++] = c;
            }
        }

        return electricCars;
    }

    public Optional<Car> findCarById(UUID id) {
        for (Car car : cars) {
            if (car.id().equals(id))
                return Optional.of(car);
        }

        return Optional.empty();
    }
}
