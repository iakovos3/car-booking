import com.iakovos.booking.CarBooking;
import com.iakovos.booking.CarBookingService;
import com.iakovos.car.Car;
import com.iakovos.car.CarService;
import com.iakovos.exception.InvalidInputException;
import com.iakovos.user.User;
import com.iakovos.user.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        final UserService userService = new UserService();
        final CarService carService = new CarService();
        final CarBookingService carBookingService = new CarBookingService();

        Scanner userInput = new Scanner(System.in);
        int userChoice = 0;
        do {
            System.out.println("1 - Book Car");
            System.out.println("2 - Cancel Booking");
            System.out.println("3 - View All User Booked Cars");
            System.out.println("4 - View All Bookings");
            System.out.println("5 - View Available Cars");
            System.out.println("6 - View Available Electric Cars");
            System.out.println("7 - View All Users");
            System.out.println("8 - Exit");


            if (!userInput.hasNextInt()) {
                System.out.println("Invalid choice! Please pick a number between 1 and 8.");
                userInput.next();
                continue;
            }

            userChoice = userInput.nextInt();
            UUID userId;
            switch (userChoice) {

                case 1:
                    try {
                        System.out.println("Enter User id:");
                        userId = UUID.fromString(userInput.next());

                        System.out.println("Enter Car id:");
                        UUID carId = UUID.fromString(userInput.next());

                        System.out.println("Enter starting date (yyyy-MM-dd):");
                        LocalDate startingDate = LocalDate.parse(userInput.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        System.out.println("Enter ending date (yyyy-MM-dd):");
                        LocalDate endingDate = LocalDate.parse(userInput.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        CarBooking carBooking = carBookingService.bookCar(
                                userId,
                                carId,
                                startingDate,
                                endingDate);

                        System.out.println(carBooking.toString() + "\n");
                    } catch (DateTimeParseException ex) {
                        System.out.println("Error: Invalid date format! Type a date with the following format: 'yyyy-MM-dd' \n");
                    } catch (InvalidInputException ex) {
                        System.out.println("Error: " + ex.getMessage() + "\n");
                    }
                    break;

                case 2:
                    System.out.println("Enter Booking id:");
                    UUID bookingId = UUID.fromString(userInput.next());

                    try {
                        boolean carBookingCancelled = carBookingService.cancelBooking(bookingId);
                        if(carBookingCancelled){
                            System.out.println("Booking has been cancelled successfully\n");
                        }else{
                            System.out.println("Booking cancellation failed\n");
                        }
                    } catch (InvalidInputException ex) {
                        System.out.println("Error: " + ex.getMessage() + "\n");
                    }
                    break;

                case 3:
                    System.out.println("Enter User id:");
                    userId = UUID.fromString(userInput.next());

                    CarBooking[] allCarBookingsByUserId = carBookingService.getAllCarBookingsByUserId(userId);
                    if (allCarBookingsByUserId.length > 0) {
                        System.out.println("User's bookings:");
                        for (CarBooking cb : allCarBookingsByUserId) {
                            System.out.println(cb.toString());
                        }
                    } else {
                        System.out.println("User has no bookings yet");
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println("List of All Bookings: \n");
                    CarBooking[] allCarBookings = carBookingService.getAllCarBookings();
                    if (allCarBookings.length == 0) {
                        System.out.println("No car bookings found!\n");
                        break;
                    }

                    for (CarBooking cb : allCarBookings) {
                        if(cb != null)
                            System.out.println(cb);
                    }
                    System.out.println();
                    break;

                case 5:
                    System.out.println("List of All Cars: \n");
                    Car[] allCars = carService.getAllCars();
                    if (allCars.length == 0)
                        System.out.println("No cars found!\n");

                    for (Car c : allCars) {
                        System.out.println(c.toString());
                    }
                    System.out.println();
                    break;

                case 6:
                    System.out.println("List of All Electric Cars: \n");
                    Car[] allElectricCars = carService.getAllElectricCars();
                    if (allElectricCars.length == 0)
                        System.out.println("No electrical cars found!\n");

                    for (Car c : allElectricCars) {
                        System.out.println(c.toString());
                    }
                    System.out.println();
                    break;

                case 7:
                    System.out.println("List of Users: \n");
                    User[] allUsers = userService.getAllUsers();
                    if (allUsers.length == 0)
                        System.out.println("No users found!\n");

                    for (User u : allUsers) {
                        System.out.println(u.toString());
                    }
                    System.out.println();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    userInput.close();
                    break;

                default:
                    System.out.println("Please select between 1 and 8.");
            }


        } while (userChoice != 8);

    }
}

