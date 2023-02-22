package Manager;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Helper {
    Scanner input = new Scanner(System.in);

    public void saveToFile(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Building saved to " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    // helper for Building class
    //methode to get either all or specific info of building
    public void viewBuildingInfo() {
        System.out.println("enter Building name:");
        String buildingName = input.next();
        Building building = new Building(0).getBuildingByName(buildingName);
        String res = "";
        int ans = 0;
        while (ans != 5) {
            res = "";
            System.out.println("type number to get info\n0.show all 1.name 2.address 3.docId 4.reference 5.exit");
            ans = input.nextInt();
            if (ans == 1 || ans == 0) {
                if (!Objects.equals(building.getBuildingName(), "")) {
                    res += ("Building Name: " + building.getBuildingName() + "\n");
                } else {
                    System.out.println("its empty or might not been assigned");
                }
            }
            if (ans == 2 || ans == 0) {
                if (!Objects.equals(building.getBuildingAddress(), "")) {
                    res += ("Building Address: " + building.getBuildingAddress() + "\n");
                } else {
                    System.out.println("its empty or might not been assigned");
                }
            }
            if (ans == 3 || ans == 0) {
                if (building.getBuildingDocId() != 0) {
                    res += ("Building Document Id: " + building.getBuildingDocId() + "\n");
                } else {
                    System.out.println("its empty or might not been assigned");
                }
            }
            if (ans == 4 || ans == 0) {
                if (building.getBuildingReference() != 0) {
                    res += ("Building Reference: " + building.getBuildingReference() + "\n");
                } else {
                    System.out.println("its empty or might not been assigned");
                }
            }
            System.out.println(res);
        }
    }


    //checking method


    // helper for Unit class
    // get specifics


    //helper for Tenant class

    public int getValidUnitNumber(Scanner input, Building building) {
        while (true) {
            System.out.println("Enter unit number (3 digits):");
            int unitNumber = input.nextInt();
            if (String.valueOf(unitNumber).length() == 3 && !building.checkUnitNumber(unitNumber)) {
                return unitNumber;
            } else {
                System.out.println("Invalid unit number. Please enter a 3-digit number that has not been assigned.");
            }
        }
    }

    public String getValidUnitType(Scanner input) {
        String unitType;
        System.out.println("Enter unit type (small/big/studio-roof/outside/storage):");
        while (true) {
            unitType = input.next();
            if (Arrays.asList("small", "big", "studio/roof", "outside", "storage").contains(unitType)) {
                break;
            }
            System.out.println("Invalid unit type. Please enter one of the following: 'small', 'big', 'studio-roof', 'outside', 'storage'.");
        }
        return unitType;
    }
    public Unit getUnitByUnitNumber(int unitNumber) {
        return new Building(0).getUnitByUnitNumber(unitNumber);
    }
//    public static boolean isValidName(String name) {
//        if (name.length() < 2 || name.length() > 50) {
//            return false;
//        }
//        for (int i = 0; i < name.length(); i++) {
//            char c = name.charAt(i);
//            if (!Character.isLetter(c) && c != '-' && c != '\'') {
//                return false;
//            }
//        }
//        return Character.isUpperCase(name.charAt(0));
//    }
    public boolean testPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^(966|00966|5|05)\\d+$");
        return pattern.matcher(number).matches();
    }

    public boolean testStringStart(String input) {
        return input.startsWith("1") || input.startsWith("2");
    }
    public Tenant getTenantByName(String tenantName) {
        Tenant tenant = new Unit(0).getTenantByName(tenantName);
        if (tenant == null) {
            System.out.println("not found");
            return null;
        } else {
            return tenant;
        }
    }

    //---
    public void removeUnit() {
        System.out.println("type unit number to remove");
        int unitNumber = input.nextInt();
        Unit unit = getUnitByUnitNumber(unitNumber);
        Building.units.remove(unitNumber,unit);
    }
    public void showTenantInfo(){
        System.out.println("enter tenant name: ");
        String tenantName = input.next();
        Tenant tenant = new Unit(0).getTenantByName(tenantName);
        System.out.println("name: "+tenant.getTenantName());
        System.out.println("id: "+tenant.getTenantUniqId());
        System.out.println("Phone: "+tenant.getTenantPhoneNumber());
        System.out.println("Official No: "+tenant.getTenantGovernmentId());
        System.out.println("Unit No: "+tenant.getTenantLinkedUnitNumber());
    }
    public void printAllPayments() {
        System.out.println("Enter tenant name: ");
        String tenantName = input.next();
        Tenant tenant = getTenantByName(tenantName);
        System.out.println(getRentCollectionInfo()+"\n");
        System.out.println(tenant.getPaymentTable());
    }
    public String getPaymentTable() {
        String table = "";
        for (RentCollection info : Tenant.paymentTable.values()) {
            table += "payment due: " + info.getPaymentPerNoOfPayment() + " | paid: " + info.getPaid() + " | date: " + info.getPayDate() + " | type: " + info.getPayType() + "\n";
        }
        return table;
    }
    public String getRentCollectionInfo() {
        System.out.println("enter start rent: ");
        LocalDate startRent = LocalDate.parse(input.next());
        RentCollection rentCollection = Tenant.rentCollectionHashMap.get(startRent);
        return "start: " + rentCollection.getStartRent() + " | Charge: " + rentCollection.getUnitCharge() + " | payment times: " + rentCollection.getNoOfPayment() + "\n";
    }
    public void addTableOfPayment(int paymentNumber, RentCollection rentCollection) {
        rentCollection.setPaymentPerNoOfPayment(rentCollection.getPaymentPerNoOfPayment());
        System.out.println("Enter paid Amount: ");
        rentCollection.paid = input.nextInt();
        System.out.print("Enter pay date (yyyy-MM-dd): ");
        String payDate = input.next();
        rentCollection.payDate = LocalDate.parse(payDate);
        System.out.println("Enter pay type:");
        rentCollection.payType = input.next();
        if (!(rentCollection.payType.equalsIgnoreCase("wire") || rentCollection.payType.equalsIgnoreCase("cash") || rentCollection.payType.equalsIgnoreCase("Platform"))) {
            System.out.println("inValid enter of pay type");
        }
        if (paymentNumber == 0) {
            System.out.println("cannot be zero");
        } else {
            Tenant.paymentTable.put(paymentNumber, rentCollection);
        }
    }

    public void editBuildingInfo() {
        System.out.println("Enter Building name:");
        String buildingName = input.next();
        Building building = new Building(0).getBuildingByName(buildingName);
        System.out.println("type number to edit\n1. name 2. address 3. docId 4. reference");
        int ans = input.nextInt();
        if (ans == 1) {
            if (building.getBuildingName() != null) {
                System.out.println("enter new name: ");
                String newName = input.next();
                building.setBuildingName(newName);
            } else {
                System.out.println("its empty or might be not been assigned, do you want to proceed?(y/n)");
                String s = input.next();
                if (s.equals("y")) {
                    System.out.println("enter new name: ");
                    String newName = input.next();
                    building.setBuildingName(newName);
                }
            }
        }
        if (ans == 2) {
            if (building.getBuildingAddress() != null) {
                System.out.println("enter new address: ");
                String newAddress = input.next();
                building.setBuildingAddress(newAddress);
            } else {
                System.out.println("its empty or might be not been assigned, do you want to proceed?(y/n)");
                String s = input.next();
                if (s.equals("y")) {
                    System.out.println("enter new address: ");
                    String newAddress = input.next();
                    building.setBuildingAddress(newAddress);
                }
            }
        }
        if (ans == 3) {
            if (building.getBuildingDocId() != 0) {
                System.out.println("enter new docId: ");
                int newDocId = input.nextInt();
                building.setBuildingDocId(newDocId);
            } else {
                System.out.println("its empty or might be not been assigned, do you want to proceed?(y/n)");
                String s = input.next();
                if (s.equals("y")) {
                    System.out.println("enter new docId: ");
                    int newDocId = input.nextInt();
                    building.setBuildingDocId(newDocId);
                }
            }
        }
        if (ans == 4) {
            if (building.getBuildingReference() != 0) {
                System.out.println("enter new reference: ");
                int newReference = input.nextInt();
                building.setBuildingReference(newReference);
            } else {
                System.out.println("its empty or might be not been assigned, do you want to proceed?(y/n)");
                String s = input.next();
                if (s.equals("y")) {
                    System.out.println("enter new reference: ");
                    int newReference = input.nextInt();
                    building.setBuildingReference(newReference);
                }
            }
        }
    }
}
