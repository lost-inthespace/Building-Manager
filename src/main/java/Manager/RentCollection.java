package Manager;


import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

public class RentCollection extends Helper {
    private LocalDate startRent;
    private double unitCharge;
    private int noOfPayment;
    private double paymentPerNoOfPayment;
    double paid;
    LocalDate payDate;
    String payType;
    private String Status;
    Scanner input = new Scanner(System.in);
    Helper helper = new Helper();

    public LocalDate getStartRent() {
        return startRent;
    }

    public void setPaymentPerNoOfPayment(double paymentPerNoOfPayment) {
        this.paymentPerNoOfPayment = paymentPerNoOfPayment;
    }

    double getUnitCharge() {
        return unitCharge;
    }
    private String getStatus() {
        return Status;
    }

    public int getNoOfPayment() {
        return noOfPayment;
    }

    public double getPaymentPerNoOfPayment() {
        return paymentPerNoOfPayment;
    }

    public double getPaid() {
        return paid;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public String getPayType() {
        return payType;
    }
    public RentCollection(String get){}

    public RentCollection() {
        while (true) {
            System.out.println("Enter tenant name:");
            String name = input.next();
            Tenant tenant = helper.getTenantByName(name);
            if (tenant == null) {
                System.out.println("Tenant with name [" + name + "] does not exist.");
                continue;
            }
            System.out.println("enter start rent date (yyyy-mm-dd) [2023-1-1]");
            String startRentDate = input.next();
            this.startRent = LocalDate.parse(startRentDate);
            System.out.println("enter unit charge");
            this.unitCharge = input.nextDouble();
            System.out.println("enter no of payment");
            this.noOfPayment = input.nextInt();
            if (noOfPayment == 0) {
                this.paymentPerNoOfPayment = this.unitCharge;
            } else {
                this.paymentPerNoOfPayment = this.unitCharge / this.noOfPayment;
            }
            tenant.rentCollectionHashMap.put(this.startRent, this);
            break;
        }
    }

    //"create a method that add all above to a string called rent"
    public RentCollection(int newPayment) { //add payment which is الدفعة
        System.out.println("enter tenant name: ");
        Tenant tenant = new Unit(0).getTenantByName(input.next());
        while (true) {
            if(newPayment != 0){
                System.out.println("[STREAM_ERROR] 0 must be a parameter to create a new payment");
                break;
            }
            this.paymentPerNoOfPayment = this.unitCharge / this.noOfPayment;
            int paymentNumber;
            while (true) {
                System.out.println("Enter payment number:");
                paymentNumber = input.nextInt();
                int lastIndex = (tenant.getPaymentSheet().size()) - 1;
                if (tenant.getPaymentSheet().isEmpty()) {
                    if (paymentNumber != 1) {
                        System.out.println("First payment number must be 1.");
                    }
                } else if (tenant.getPaymentSheet().containsKey(paymentNumber)) {
                    System.out.println("Payment has already been added. Please add the next payment.");
                } else if (paymentNumber > noOfPayment) {
                    System.out.println("Payment number cannot exceed the total number of payments.");
                } else if (paymentNumber - lastIndex >= 2) {
                    System.out.println("Payments must be added in order. You cannot skip a payment number.");
                }
                break;
            }
            helper.addTableOfPayment(paymentNumber, this);
            break;
        }
    }
    public void getStatue() {
        System.out.println("enter tenant name: ");
        String TenantName = input.next();
        Tenant tenant =helper.getTenantByName(TenantName);
        double left = getUnitCharge() - tenant.totalPaidRent();
        double leftPercent = (left / getUnitCharge()) * 100;
        String statue;
        //المنطق تحت مو منطقي لازم تضيف مساعدات تواريخ الخ عشان يفهم فاي شهر نحنا ،والمفروض ما يقبل تواريخ بعد now()!
        if (left == 0) {
            statue = "safe and sound";
        } else if (leftPercent < 100 && leftPercent > 80) {
            statue = "Good";
        } else if (leftPercent < 80 && leftPercent > 70) {
            statue = "Warning";
        } else {
            statue = "Late";
        }
        this.Status = ("[" + statue + "] Left to pay: " + left + " from: " + getUnitCharge() + " [" + leftPercent + "%]");
        System.out.println(this.getStatus());
    }
}
