package Manager;

import java.util.HashMap;
import java.util.Scanner;

class Unit extends Helper {
    Scanner input = new Scanner(System.in);

    public int unitNumber;
    public String unitType;
    //collections
    static HashMap<String, Tenant> TenantList = new HashMap<>();

    public int getUnitNumber() {
        return unitNumber;
    }
    public Unit(int c){}
    public Unit() {
        boolean checked = false;
        do {
            try {
                System.out.println("Enter building name:");
                String buildingName = input.next();
                Building building = new Building(0).getBuildingInList(buildingName);
                if (building == null) {
                    throw new IllegalArgumentException("Building [" + buildingName + "] does not exist.");
                }
                unitNumber = getValidUnitNumber(input, building);
                unitType = getValidUnitType(input);

                System.out.println("Information has been added to building [" + building.getBuildingName() + "] successfully.");
                building.addUnit(unitNumber, this);

                checked = true; // Set checked to true to exit the loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!checked);
    }

    public void addTenantList(String tenantName, Tenant tenant) {
        TenantList.put(tenantName, tenant);
    }

    public Tenant getTenantByName(String tenantUniqId) {
        return Unit.TenantList.get(tenantUniqId);
    }
}