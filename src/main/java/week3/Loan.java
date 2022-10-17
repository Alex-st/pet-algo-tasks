package week3;//package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.String.format;

public class Loan {

    public static final double PRECISION = 0.000001;
    /*
    Auto dealerships frequently advertise tempting loan offers in order to make it easier for people to afford the "car of their dreams". A typical sales tactic is to show you various cars, and then talk in terms of what your monthly payment would be, to say nothing of how much you are actually paying for the car, how much interest you pay, or how long you have to make payments.
    A typical auto loan is calculated using a fixed interest rate, and is set up so that you make the same monthly payment for a set period of time in order to fully pay off the balance. The balance of your loan starts out as the sticker price of the car. Each month, the monthly interest is added to your balance, and the amount of your payment is subtracted from your balance.
    (The payment is subtracted after the interest is added.) The monthly interest rate is 1/12 of the yearly interest rate. Thus, if your annual percentage rate is 12%, then 1% of the remaining balance would be charged as interest each month.
    You have been checking out some of the cars at your local dealership. An excited salesman has just approached you, shouting about how you can have the car you are looking at for a payment of only monthlyPayment for only loanTerm months! You are to find the annual percentage rate of the loan, assuming that the initial balance of the loan is price.
    Because of the way interest is compounded monthly, the actual interest accrued over the course of a year is not necessarily the same as (balance * yearly interest rate). In fact, it's usually more.

    Input Format
    A single line of input contains 2 real numbers price and monthlyPayment and a single integer number loanTerm.

    Output Format
    Print a single number — the resulting yearly interest rate. Your answer must be within 10-6 absolute error of the actual result.

    Sample Input
    6800.00 100.00 68

    Sample Output 0
    0.000000000000133

    Explanation 0

    Noting that 68 payments of 100 equals the total price of 6800, so there is no interest.
    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String paramString = reader.readLine();
        Double balance = Double.valueOf(paramString.split(" ")[0]);
        Double monthlyPayment = Double.valueOf(paramString.split(" ")[1]);
        Integer term = Integer.valueOf(paramString.split(" ")[2]);
        String res = format("%f", getAnualInterest(balance, monthlyPayment, term));
        System.out.println(res);
    }

    private static Double getAnualInterest(Double balance, Double monthlyPayment, Integer term) {
        double bad = 0;
        double good = 1;

        while (good - bad > PRECISION) {
            double middle = (good + bad)/2.;
            System.out.println("Bad:" + bad + "/Good:" + good + "/Middle:" + middle);
            if (goodEnough2(middle, balance, monthlyPayment, term)) {
                bad = middle;
            } else {
                good = middle;
            }
        }
        return good * 100;
    }

    private static boolean goodEnough(double middle, double balance, double monthlyPayment, int term) {
        int counterMonth = 0;
        Double curBallance = balance;
        while (curBallance > PRECISION) {
            curBallance = curBallance * (1 + middle/12.) - monthlyPayment;
            counterMonth++;
            System.out.println("CurBallance:" + curBallance + "/counter:" + counterMonth);
        }
        return (counterMonth < term);
    }

    private static boolean goodEnough2(double middle, double balance, double monthlyPayment, int term) {
        int counterMonth = 0;
        Double curBallance = balance;
        while (term > counterMonth) {
            counterMonth++;
            curBallance = curBallance * (1 + middle/12.) - monthlyPayment;
            System.out.println("CurBallance:" + curBallance + "/counter:" + counterMonth);
        }
        return (curBallance < 0.);
    }
}
