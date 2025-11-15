import java.util.ArrayList;

public class StandardVisitor extends ArtGalleryVisitor{
    private boolean isEligibleForDiscountUpgrade;
    private final int visitLimit=5;
    private float discountPercent;
    private double discountAmount;
    private double finalPrice;
    private double refundableAmount;
   private ArrayList<String> artworkNames = new ArrayList<>();
private ArrayList<Double> artworkPrices = new ArrayList<>();



    public StandardVisitor(int visitorId,String fullName,String gender,String contactNo,String registrationDate,double ticketCost,String ticketType){
        super(visitorId,fullName,gender,contactNo,registrationDate,ticketCost,ticketType);
        
      this.isEligibleForDiscountUpgrade=false;
        this.discountPercent=0.10f;

        this.visitorId = visitorId; //assigning parameters
        this.fullName= fullName;
        this.gender= gender;
        this.contactNo= contactNo;
        this.registrationDate= registrationDate;
        this.ticketCost=ticketCost;
        this.ticketType=ticketType;
    }
    //corresponding accessor methods
    public int getvisitorId(){return visitorId;} //each class with accessor method 
    public String getfullName() {return fullName;}
    //getter method is implemented.
    public String getgender(){return gender;}

    public String getcontactNo(){return contactNo;}

    public String getregistrationDate(){return registrationDate;}

    public double getticketcost(){return ticketCost;}

    public String getticketType(){return ticketType;}
public double getdiscountAmount(){return discountAmount;}
    public boolean isEligibleForDiscountUpgrade(){return isEligibleForDiscountUpgrade;}

    public int getVisitLimit(){return visitLimit;}

    public float getDiscountPercent(){return discountPercent;}  

    public boolean checkDiscountUpgrade(){
        if (visitCount>=visitLimit){
            isEligibleForDiscountUpgrade=true;
            discountPercent=0.15f;
        }
        return isEligibleForDiscountUpgrade;       
    }
    //buyProduct() method is implemented
 @Override
    public String buyProduct(String artworkName, double artworkPrice) {
        if (!isActive) {
            return "Please login to activate your account.";
        }
        if (artworkNames.contains(artworkName)) {
            return "Artwork has already been purchased";
        }
        artworkNames.add(artworkName);
        artworkPrices.add(artworkPrice);
        buyCount++;
        isBought = true;
        this.artworkName = artworkName;
        this.artworkPrice = artworkPrice;
        calculateDiscount();
        return "Artwork " + artworkName + " purchase was a success for Rs." + artworkPrice;
    }  
    //calculateDiscount() method is implemented
    @Override
    public double calculateDiscount() {
        if (!isBought) {
            discountAmount = 0;
        } else {
            checkDiscountUpgrade();
            discountAmount = artworkPrice * discountPercent;
            finalPrice = artworkPrice - discountAmount;
        }
        return discountAmount;
    }

    //calculateRewardPoints() method is implemented
    @Override
    public double calculateRewardPoints(){
        if (isBought==true){
            rewardPoints+=finalPrice*5;
        }
        return rewardPoints;
    }
    //generateBill() method is implemented
    @Override
    public void generateBill(){
        if (isBought==false){
            System.out.println("No purchase made to generate a bill.");
        }else{           
            System.out.println("Customer Bill");
            System.out.println("Visitor ID: " +visitorId);
            System.out.println("Visitor Name: " +fullName);
            System.out.println("Artwork Name: " +artworkName);
            System.out.println("Artwork Price: " +artworkPrice);
            System.out.println("Discount: " +discountAmount);
            System.out.println("Final Price: " +finalPrice);
        }
    }
    //terminateVisitor() method is implemented
    private void terminateVisitor(){ //private access 
        isActive=false;
        isEligibleForDiscountUpgrade=false;
        visitCount=0;
        cancelCount=0;
        rewardPoints=0;
    }
    //cancelProduct method was implemented
    @Override
    public String cancelProduct(String artworkName,String cancellationReason){
        if(cancelCount>=3){
            terminateVisitor();
            return"Account has been terminated due to repeated cancellation";
        }
        if(buyCount==0){
            return"Cancellation is not accepted";
        }
        if(this.artworkName!=null && this.artworkName.equals(artworkName)){
            this.cancellationReason=cancellationReason;
            isBought=false;
            refundableAmount=artworkPrice-(artworkPrice*0.10);
            cancelCount++;
            buyCount--;
            this.artworkName=" ";
            this.finalPrice=0;
            this.discountAmount=0;
            rewardPoints-=finalPrice*5;
            return"Artwork"+artworkName+"has been cancelled with refunded amount of rs. "+refundableAmount;
        }
        if (buyCount==0){
            return"Product has not been cancelled.";
        }
        else{return"The name of artwork is incorrect.";}
    }
    //Implemention of display() method
    public void display(){
        super.display();
        System.out.println("EligibleForDiscountUpgrade:  "+isEligibleForDiscountUpgrade);
        System.out.println("VisitLimit:    "+visitLimit);
        System.out.println("DiscountPercent:   "+discountPercent);
    }
    //For Activation Output this is used.It can be erased to have not active output.
    public void activate() {
        this.isActive = true;
    } 
public void logVisit() {
    this.visitCount += 1;
    this.isActive = true;
    checkDiscountUpgrade(); 
}
    public boolean getisBought(){
        return isBought;
    }
    public double getartworkPrice() {
        return artworkPrice;
    }
 
}

    

