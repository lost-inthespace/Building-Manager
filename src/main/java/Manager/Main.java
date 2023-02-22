package Manager;


import java.util.Scanner;

public class Main extends Helper{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Helper helper = new Helper();
        System.out.println("Welcome to the system");
        System.out.println("Log in [ENTER] to continue");
        scanner.nextLine();

        //System.out.println("default is 'admin'");
        new Authentication.login();

        System.out.println("create your first Building!");
        new Building();
        while(true){
            System.out.println("""
            1.new Unit
            2.new Tenant
            3.new year plan
            4.add payment
            5.view Building Info
            6.edit Building Info
            7.delete a Unit
            8.view Tenant Info
            9.view payment summary
            10.view Tenant Statue
            11.exit program""");

            int number = scanner.nextInt();
            switch (number) {
                case 1 -> {
                    System.out.println("-New Unit-");
                    new Unit();
                }
                case 2 -> {
                    System.out.println("-New tenant-");
                    new Tenant();
                }
                case 3 -> {
                    System.out.println("--New Year Plan--");
                    new RentCollection();
                }
                case 4 -> {
                    System.out.println("--add payment--");
                    new RentCollection(0);
                }
                // I still need to add [view / edit / remove] methods to other classes like below
                // new Building(0) this construct 0 mean for methods [view / edit / remove]
                case 5 -> {
                    System.out.println("--view Building Info--");
                    helper.viewBuildingInfo();
                }
                case 6 -> {
                    System.out.println("--edit Building Info--");
                    helper.editBuildingInfo();
                }
                case 7 -> {
                    System.out.println("--remove Unit--");
                    helper.removeUnit();
                }
                case 8 -> {
                    System.out.println("--view Tenant Info--");
                    helper.showTenantInfo();
                }
                case 9 -> {
                    System.out.println("--view Payment Summary--");
                    helper.printAllPayments();
                }
                case 10 -> {
                    System.out.println("--view Tenant Rent Statue");
                    new RentCollection("").getStatue();
                }
                case 11 -> System.exit(0);
                default -> System.out.println("Other");
            }
        }
    }
}
