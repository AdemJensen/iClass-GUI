import java.util.Scanner;
public class Main
{

    private final int MAX =6;
    private int facevalue;
    public Main()
    {
     facevalue = 1 ;

    }
     public int roll()
     {
         facevalue = (int)(Math.random() * MAX) +1 ;

         return facevalue;

     }
     public void setFaceValue( int value)
     {
        facevalue = value ;
     }

     public int getFaceValue()
     {
         if ((facevalue > 0)&&(facevalue < 7))
             return facevalue;
         else
             return 0;


     }
     public String toString()
        {
        String result = Integer.toString(facevalue);

        return result;
        }
    public static void main( String []args )
    {

        //tested program
        Main die1, die2;
        int sum;

        die1 = new Main();
        die2 = new Main();

        die1.roll();
        die2.roll();

        System.out.println("Die1 = " + die1 +" Die2 = " + die2 );

        die1.roll();
        die2.setFaceValue(4);

        sum= die1.getFaceValue() + die2.getFaceValue() ;
        if(die2.getFaceValue() == 0){
            System.out.println("you mother fucker, your input is illegal");
        }
        else {
            System.out.println("Die1 = " + die1 + " Die2 = " + die2 + " \n so sum is  " + sum);
        }

        sum = die1.roll() + die2.roll();
        System.out.println("die1 is : " + die1 + " die2 is " + die2 +"\n So sum is :" + sum);

//data build
        Scanner player1 = new Scanner(System.in), player2 = new Scanner(System.in);
        System.out.println("choose which you want to be as a player");
        System.out.println("input '1' or '2' or '3' or '4' " );
        byte action = player1.nextByte();


//game program
        //data develop
        int wins = 0, losts = 0, equals = 0;


        for(int times = 10; times > 0; times--) {
            //value get
            die1.roll();
            die2.roll();
            int result1 = die1.getFaceValue(), result2 = die2.getFaceValue();

            //value judge
            if (((action == 1 || action == 3) && (result1 > result2)) || ((action == 2 || action == 4) && (result1 < result2))) {
                System.out.println("you wins.");
                wins++;
            }
            else if (((action == 1 || action == 3) && (result1 < result2)) || ((action == 2 || action == 4) && (result1 > result2))) {
                System.out.println("you lost");
                losts++;
            }
            else {
                System.out.println("equal");
                equals++;
            }

        }


        System.out.println("you won :" + wins +" times");
        System.out.println("you lost :" + losts + " times");
        System.out.println("equal times :" + equals + " times");

        if(wins > losts)
            System.out.println("you won at last");
        else
            if (losts > wins )
                System.out.println("you  lost at last");
            else
                System.out.println("equal at last");
        System.out.println("GAME OVER");

        for (int i = 0; i < 10; i++) {
            final int spent_money = 0;
            if(spent_money > 0)
                System.out.println("Gold ship was built!!!");
            final boolean isEuropean = false;
            if(isEuropean) {
                System.out.println("Marine legend was built!!!!!");
            }
            else
            System.out.println("White ship was built!!!");
        }


    }
}
