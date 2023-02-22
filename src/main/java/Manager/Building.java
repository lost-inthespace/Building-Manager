package Manager;
//imported classes
import java.util.HashMap;
import java.util.Scanner;
public class Building extends Helper {
    //default object
    Scanner input = new Scanner(System.in);
    Helper helper = new Helper();
    //variables
    private String buildingName;
    private String buildingAddress;
    private int buildingDocId;
    private int buildingReference;
    //collections that store Building and Unit
    static HashMap<Integer,Unit> units = new HashMap<>();
    static HashMap<String,Building> BuildingList = new HashMap<>();
    //setter and getter for ArrayList Obj of Building
    public Building getBuildingInList(String buildingName) {
        if(BuildingList.containsKey(buildingName)) {
            return BuildingList.get(buildingName);
        } else{
            System.out.println("not found");
            return null;
        }
    }
    //Constructor
    public Building(int c){} // get the methods
    public Building() {
        System.out.println("enter Building name: ");
        setBuildingName(input.next());
        System.out.println("enter Building address: ");
        setBuildingAddress(input.next());
        System.out.println("enter Building docId: ");
        setBuildingDocId(input.nextInt());
        System.out.println("enter Building reference: ");
        setBuildingReference(input.nextInt());
        BuildingList.put(this.buildingName,this);
    }

    //this method to edit / set the values of the building

    //setter building values
    void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    void setBuildingDocId(int buildingDocId) {
        this.buildingDocId = buildingDocId;
    }

    void setBuildingReference(int buildingReference) {
        this.buildingReference = buildingReference;
    }

    //getter methods
    public Building getBuildingByName(String name) {
        if(BuildingList.containsKey(name)) {
            return BuildingList.get(name);
        } else{
            System.out.println("not found");
            return null;
        }
    }

    //data manipulation
    public void addUnit(int unitNumber, Unit unit) {
        units.put(unitNumber,unit);
    }

    public void removeUnit() {
        System.out.println("type unit number to remove");
        int unitNumber = input.nextInt();
        Unit unit = getUnitByUnitNumber(unitNumber);
        units.remove(unitNumber,unit);
    }

    //getter building values

    String getBuildingName() {
        return buildingName;
    }

    String getBuildingAddress() {
        return buildingAddress;
    }

    int getBuildingDocId() {
        return buildingDocId;
    }

    int getBuildingReference() {
        return buildingReference;
    }

    public Unit getUnitByUnitNumber(int unitNumber) {
        return Building.units.get(unitNumber);
    } //get return type object Unit

    //checking methods
    public boolean checkUnitNumber(int unitNumber) {
        if (units.containsKey(unitNumber)) {
            return true;
        }
        else{
            System.out.println("not found");
            return false;
        }
    }

    //IO save and read
    public void save() {
        helper.saveToFile(this.toString());
    }
}


