import java.util.Scanner;

class Rental {
    public static void main(String []argv) {
        // Create and populate a checkout request.
        CheckoutRequest request = new CheckoutRequest();
        request.populateFromCommandLine(argv);
        //request.populateFromInput();

        // Display the request.
        System.out.print(request.toString());




        // Test the Tool class.
        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid");
        System.out.print("\n");
        System.out.print(tool.toString());

        // Test the PriceRules class.
        PriceRules priceRules = new PriceRules("Jackhammer", 2353, true, false, true);
        System.out.print("\n");
        System.out.print(priceRules.toString());

        // Test the registries.
        System.out.println("\ninfo about tool JAKR:\n");
        ToolRegistry toolRegistry = ToolRegistry.getInstance();
        System.out.print(toolRegistry.getToolByCode("JAKR").toString());
    
        System.out.println("\ninfo about tool type Jackhammer:\n");
        PriceRegistry priceRegistry = PriceRegistry.getInstance();
        System.out.print(priceRegistry.getPriceRulesForToolType("Jackhammer").toString());
    }
};