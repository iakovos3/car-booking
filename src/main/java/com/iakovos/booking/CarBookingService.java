package com.iakovos.booking;

import com.iakovos.car.Car;
import com.iakovos.car.CarService;
import com.iakovos.exception.InvalidInputException;
import com.iakovos.user.User;
import com.iakovos.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CarBookingService {

    private final CarBookingDao carBookingDao = new CarBookingDao();
    private final UserService userService = new UserService();
    private final CarService carService = new CarService();

    public CarBooking[] getAllCarBookings() {
        return carBookingDao.getAllCarBookings();
    }

    public CarBooking[] getAllCarBookingsByUserId(UUID userId) {
        return carBookingDao.findAllCarBookingsByUserId(userId);
    }

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) throws InvalidInputException{
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new InvalidInputException("User not found!"));

        Car car = carService.getCarById(carId)
                .orElseThrow(() -> new InvalidInputException("Car not found!"));

        if (startDate.isBefore(LocalDate.now()) || startDate.isAfter(endDate)) {
            throw new InvalidInputException("Dates are invalid!");
        }

        double daysOfBooking = (double) ChronoUnit.DAYS.between(startDate, endDate);
        BigDecimal totalPriceOfBooking = (daysOfBooking == 0) ?
                car.rentalPricePerDay() :
                BigDecimal.valueOf(car.rentalPricePerDay().doubleValue() * daysOfBooking);


        CarBooking[] allCarBookings = carBookingDao.getAllCarBookings();
        for (CarBooking cb : allCarBookings) {
            if (cb != null && cb.getCar().id().equals(carId) &&
                    cb.getStatus().equals(CarBookingStatus.ACTIVE)) {
                throw new InvalidInputException("Car is not available!");
            }
        }


        CarBooking newCarBooking = new CarBooking(
                UUID.randomUUID(),
                user,
                car,
                startDate,
                endDate,
                totalPriceOfBooking,
                CarBookingStatus.ACTIVE,
                LocalDateTime.now()
        );

        return carBookingDao.save(newCarBooking);
    }

    public boolean cancelBooking(UUID bookingId) throws InvalidInputException{
        carBookingDao.getCarBookingById(bookingId)
                .orElseThrow(() -> new InvalidInputException("Booking not found!"));


        return carBookingDao.cancel(bookingId);
    }

}
