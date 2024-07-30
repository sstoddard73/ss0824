import java.util.Scanner;

class Rental {
    public static void main(String []argv) {
        // Create and populate a rental request.
        RentalRequest request = new RentalRequest();
        request.populateFromCommandLine(argv);
        //request.populateFromInput();

        // Display the request.
        request.display();
    }
};