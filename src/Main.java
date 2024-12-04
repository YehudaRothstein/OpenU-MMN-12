public class Main
{
    public static void main(String[] args) {
        System.out.println("********** Test Baby - Started **********");

        //=====================================================================
        System.out.println("\n\n1. Testing Constructors and toString:");
        Baby b1=new Baby("Don","Abraham","123456789",3,5,2024,3600);
        System.out.print("\nbaby b1 is:\n"+b1);
        Baby b2=new Baby(b1);
        System.out.print("\nbaby b2 is:\n"+b2);

        //=====================================================================
        System.out.println("\n\n2. Testing accessors and mutators:");
        Weight w1 = new Weight(4040);
        b1.setCurrentWeight(w1);
        System.out.println("\nbaby b1 is:\n"+b1);
        System.out.println("\nfirst name of b1:"+b1.getFirstName());
        System.out.println("last name of b1:"+b1.getLastName());
        System.out.println("id of b1:"+b1.getId());
        System.out.println("date of birth of b1:"+b1.getDateOfBirth());
        System.out.println("birth weight of b1:"+b1.getBirthWeight());
        System.out.println("current weight of b1:"+b1.getCurrentWeight());

        //=====================================================================
        System.out.println("\n\n3. Testing equals method:");
        System.out.println("\nbaby b1 is:\n"+b1);
        System.out.println("\nbaby b2 is:\n"+b2);
        if(b1.equals(b2))
            System.out.println("\nb1 is the same baby as b2");
        else
            System.out.println("\nb1 isn't the same baby as b2");

        //=====================================================================
        System.out.println("\n4. Testing areTwins method:");
        if(b1.areTwins(b2))
            System.out.println("\tb1 and b2 are Twins");
        else
            System.out.println("\tb1 and b2 are not Twins");

        //=====================================================================
        System.out.println("\n5. Testing heavier method:");
        if(b1.heavier(b2))
            System.out.println("\tb1 is heavier b2");
        else
            System.out.println("\tb1 is not heavier b2");

        //=====================================================================
        System.out.println("\n6. Testing updateCurrentWeight method - adding 440 grams to b2:");
        b2.updateCurrentWeight(440);
        System.out.println("\nbaby b2 is:\n"+b2);

        //=====================================================================
        System.out.println("\n7. Testing older method:");
        if(b1.older(b2))
            System.out.println("\tb1 is older b2");
        else
            System.out.println("\tb1 is not older b2");

        //=====================================================================
        System.out.println("\n8. Testing isWeightInValidRange method:");
        System.out.println("\tThe result of weight in valid of a baby that was born in: " +b1.getDateOfBirth());
        System.out.println("\tand his weight when he was born was: " + b1.getBirthWeight() + " and now after 183 days, his weight is " + b1.getCurrentWeight() + " is : "+ b1.isWeightInValidRange(183));

        System.out.println("\n\tThe result of weight in valid of a baby that was born in: " +b1.getDateOfBirth());
        System.out.println("\tand his weight when he was born was: " + b1.getBirthWeight() + " and now after 30 days, his weight is " + b1.getCurrentWeight() + " is : "+ b1.isWeightInValidRange(30));

        System.out.println("\n********** Test Baby - Finished **********\n");
    }
}