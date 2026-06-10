package com.iakovos.booking;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class CarBookingDao {

    private CarBooking[] carBookings = new CarBooking[10];
    private int currentIndex = 0;

    public CarBooking[] getAllCarBookings() {

        int numOfBookings = 0;
        for (CarBooking cb : carBookings) {
            if (cb != null) {
                numOfBookings++;
            }
        }

        CarBooking[] bookings = new CarBooking[numOfBookings];
        int carBookingsIndex = 0;
        for (CarBooking cb : carBookings) {
            if (cb != null) {
                bookings[carBookingsIndex++] = cb;
            }
        }

        return bookings;
    }

    public Optional<CarBooking> getCarBookingById(UUID id) {
        for (CarBooking cb : carBookings) {
            if (cb.getId().equals(id)) {
                return Optional.of(cb);
            }
        }
        return Optional.empty();
    }

    public CarBooking save(CarBooking carBooking) {

        if(currentIndex == carBookings.length){
            carBookings = Arrays.copyOf(carBookings, carBookings.length * 2);
        }
        carBookings[currentIndex++] = carBooking;

        return carBooking;
    }



    public boolean cancel(UUID id) {
        for (CarBooking cb : carBookings) {
            if (cb.getId().equals(id) &&
                    cb.getStatus().equals(CarBookingStatus.ACTIVE)) {
                cb.setStatus(CarBookingStatus.CANCELLED);
                return true;
            }
        }
        return false;
    }

    public boolean delete(UUID id) {

        for (int i = 0; i < carBookings.length; i++) {
            if (carBookings[i].getId().equals(id)) {
                carBookings[i] = null;
                return true;
            }
        }

        return false;
    }

    public CarBooking[] findAllCarBookingsByUserId(UUID id) {

        int numOfUserBookings = 0;
        for (CarBooking cb : carBookings) {
            if (cb.getUser().id().equals(id))
                numOfUserBookings++;
        }

        CarBooking[] userCarBookings = new CarBooking[numOfUserBookings];
        int userCarBookingsIndex = 0;
        for (CarBooking cb : carBookings) {
            if (cb.getUser().id().equals(id)) {
                userCarBookings[userCarBookingsIndex++] = cb;
            }
        }

        return userCarBookings;
    }


}
