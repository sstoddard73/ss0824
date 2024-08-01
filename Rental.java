import java.util.Scanner;

class Rental {
    public static void main(String []argv) {
        // Create and populate a checkout request.
        CheckoutRequest request = new CheckoutRequest();
        request.populateFromCommandLine(argv);
        //request.populateFromInput();

        // Display the request.
        System.out.print(request.toString());


        // Test the tool class.
        Tool tool = new Tool("TOOL", "type", "brand", 2353, true, false, true);
        System.out.print("\n");
        System.out.print(tool.toString());
    }
};