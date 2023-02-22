package Manager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Tenant extends Helper {
    Helper helper = new Helper();

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    // variables
    private String tenantName;
    private final String tenantUniqId;
    private String tenantPhoneNumber;
    private String tenantGovernmentId;
    private String tenantLinkedUnitNumber;
    static HashMap<Integer, RentCollection> paymentTable = new HashMap<>();
    static HashMap<LocalDate, RentCollection> rentCollectionHashMap = new HashMap<>();

    public HashMap<Integer, RentCollection> getPaymentSheet() {
        return paymentTable;
    }
    public Tenant() {
        Unit unit;
        int unitNumber;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter unit number: ");
            unitNumber = input.nextInt();
            unit = helper.getUnitByUnitNumber(unitNumber);
            Unit unitTest = helper.getUnitByUnitNumber(unitNumber);
            String unitNumb = "";
            if(unitTest != null){
                unitNumb = unitTest.getUnitNumber()+"";
            }
            setTenantLinkedUnitNumber(unitNumb);
            if (unit == null) {
                System.out.println("the [" + unitNumber + "] is invalid or not found");
            } else {
                break;
            }
        }
//        while (true) {
//            try {
//                System.out.println("Enter your name:");
//                String name = input.next();
//                if (isValidName(name)) {
//                    setTenantName(name);
//                    break;
//                }
//                // the name is valid, do something with it
//                else{
//                    System.out.println(("Invalid name. Please enter a valid name."));
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//                // ask the user to enter their name again
//            }
//        }
        System.out.println("Enter your name:");
        String name = input.next();
        setTenantName(name);
        Random rand = new Random();
        int randomNumber = rand.nextInt(10000);
        this.tenantUniqId = (unit.getUnitByUnitNumber(unitNumber).getUnitNumber() + String.valueOf(randomNumber));
        while (true) {
            System.out.println("enter tenant phone number: ");
            String tenantPhoneNumber = input.next();
            if (testPhoneNumber(tenantPhoneNumber)) {
                setTenantPhoneNumber(tenantPhoneNumber);
                break;
            } else {
                System.out.println("phone number is invalid");
            }
        }
        while (true) {
            System.out.println("enter tenant government id: ");
            String tenantGovernmentId = input.next();
            if (testStringStart(tenantGovernmentId)) {
                setTenantGovernmentId(tenantGovernmentId);
                break;
            } else {
                System.out.println("government id is invalid");
            }
        }
        System.out.println("new uniq id created: " + getTenantUniqId());
        unit.addTenantList(this.getTenantName(), this);
    }

    public double totalPaidRent() {
        double total = 0;
        for (RentCollection info : Manager.Tenant.paymentTable.values()) {
            total += info.getPaid();
        }
        return total;
    }
    // setter and getter
    public String getTenantUniqId() {
        return tenantUniqId;
    }

    public String getTenantLinkedUnitNumber() {
        return tenantLinkedUnitNumber;
    }

    public void setTenantLinkedUnitNumber(String tenantLinkedUnitNumber) {
        this.tenantLinkedUnitNumber = tenantLinkedUnitNumber;
    }

    public String getTenantName() {
        return tenantName;
    }
    public void setTenantPhoneNumber(String tenantPhoneNumber) {
        this.tenantPhoneNumber = tenantPhoneNumber;
    }

    public void setTenantGovernmentId(String tenantGovernmentId) {
        this.tenantGovernmentId = tenantGovernmentId;
    }

    public String getTenantPhoneNumber() {
        return tenantPhoneNumber;
    }

    public String getTenantGovernmentId() {
        return tenantGovernmentId;
    }
}