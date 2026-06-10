package com.iakovos.car;

import java.util.Optional;
import java.util.UUID;

public class CarService {

    private final CarDao carDao = new CarDao();


    public Car[] getAllCars() {
        return carDao.findAllCars();
    }

    public Car[] getAllElectricCars() {
        return carDao.findAllElectricCars();
    }

    public Optional<Car> getCarById(UUID id) {
        return carDao.findCarById(id);
    }

}
