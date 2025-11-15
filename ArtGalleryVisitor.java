import java.util.ArrayList;

public abstract class ArtGalleryVisitor{
    //blueprint for sub classes
    protected int visitorId;
    protected String fullName;
    protected String gender;
    protected String contactNo;
    protected String registrationDate;
    protected double ticketCost;
    protected String ticketType;
    protected int visitCount;
    protected double rewardPoints;
    protected final int cancelLimit;
    protected int cancelCount;
    protected String cancellationReason;
    protected double refundableAmount;
    protected boolean isActive;
    protected boolean isBought;
    protected int buyCount;
    protected double finalPrice;
    protected double discountAmount;
    protected String artworkName;
    protected double artworkPrice;
    private ArrayList<String> artworkNames = new ArrayList<>();
    private ArrayList<Double> artworkPrices = new ArrayList<>();
    private boolean isEligibleForDiscountUpgrade;
    private final int visitLimit = 5;

    //CONSTRUCTOR PART
    public ArtGalleryVisitor(int visitorId,String fullName,String gender,String contactNo,String registrationDate,double ticketcost,String ticketType){
        //variable passed into a constructor is called parameter.
        this.visitorId = visitorId; //assigning parameter to object visitorid CONSTRUCTOR MA OBJECTS ARE BEING ASSIGNED TO A PARAMTETER VALUES
        this.fullName= fullName;
        this.gender= gender;
        this.contactNo= contactNo;
        this.registrationDate= registrationDate;
        this.ticketCost=ticketCost;
        this.ticketType=ticketType; 

        this.visitCount= 0;//initializing default values
        this.rewardPoints= 0;
        this.cancelCount=0;
        this.buyCount=0;
        this.discountAmount=0;
        this.finalPrice=0;
        this.refundableAmount= 0;

        this.isActive=false;
        this.isBought=false;
        this.cancellationReason= "EMPTY";
        this.cancelLimit=3;
    }
    //corresponding accesor method
    public int getvisitorId(){return visitorId;} //getter method is implemented where it access the value of the attribute in protected area
    public String getfullName() {return fullName;}

    public String getgender(){return gender;}

    public String getcontactNo(){return contactNo;}

    public String getregistrationDate(){return registrationDate;}

    public double getticketCost(){return ticketCost;}

    public String getticketType(){return ticketType;}

    public int getvisitCount(){return visitCount;}

    public double getrewardPints(){return rewardPoints;}

    public int getcancelLimit(){return cancelLimit;}

    public int getcancelCount(){return cancelCount;}

    public String getcancellationReason(){return cancellationReason;}

    public double getrefundableAmount(){return refundableAmount;}

    public boolean getisActive(){return isActive;}


    public int getbuyCount(){return buyCount;}

    public double getfinalPrice(){return finalPrice;}

    public double getdiscountAmount(){return discountAmount;}

    public String getartworkName(){return artworkName;}

    //setter in few attributes//
    public void setfullName(String fullName){
        this.fullName= fullName;
    }

    public void setgender(String gender){
        this.gender=gender;
    }

    public void setcontactNo(String contactNo){
        this.contactNo=contactNo;
    }

    public void logVisit(){
        this.visitCount +=1;//one increment is increased
        this.isActive=true;
    }

    public abstract String buyProduct(String artworkName, double artworkprice);

    public abstract double calculateDiscount();

    public abstract double calculateRewardPoints();

    public abstract String cancelProduct(String artworkName,String cancellationReason);

    public abstract void generateBill();  

    public void display(){
        System.out.println("VisitorId: "+visitorId);
        System.out.println("FullName:  "+fullName);
        System.out.println("Gender:  "+gender);
        System.out.println("Contact No:  "+contactNo);
        System.out.println("Reg Date:   "+registrationDate);
        System.out.println("Ticket Cost:  "+ticketCost);
        System.out.println("Ticket Type:  "+ticketType);
        System.out.println("Visit Count:  "+visitCount);
        System.out.println("Reward Points:  "+rewardPoints);
        System.out.println("Cancel Limit:   "+cancelLimit);
        System.out.println("Cancel Count:  "+cancelCount);
        System.out.println("Cancellation Reason:   "+cancellationReason);
        System.out.println("Refundable Amount:   "+refundableAmount);
        System.out.println("Active status:   "+isActive);
        System.out.println("Is it Bought?:   "+isBought);
        System.out.println("Buy Count:  "+buyCount);
        System.out.println("Final Price:   "+finalPrice);
        System.out.println("Discount Amount:  "+discountAmount);
        System.out.println("Artwork Name:    "+artworkName);
        System.out.println("Artwork Price:   "+artworkPrice);

    }
     public boolean getisBought() {
        return isBought;
    }
    public double getartworkPrice() {
        return artworkPrice;
    }
   

}
