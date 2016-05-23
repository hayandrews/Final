package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//Done, compares to rates as long as order is descending 
		// RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		//OderBy in the HQL, credit scores in descending order
		
		//Done returns the rate - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		
		//Note to self: Get the list of domain model, since credit score is sorted from max to min
		//you can compare that if a credit score is greater than one of the numbers it should be 
		//the rate returned in a for each statement
		
		RateDomainModel rdm = null;
		ArrayList<RateDomainModel> rdmlist = _RateDAL.getAllRates();
		double ir = 0;
		
		for(RateDomainModel ratedm: rdmlist){
			if(GivenCreditScore >= ratedm.getiMinCreditScore()){
				rdm = ratedm;
				ir = ratedm.getdInterestRate();
				break;
			}
		}
		
		if (ir == 0) {
			throw new RateException(rdm);
		}
		else {
			return ir;
		}
	
		
	}
	
	
	//Done, made it so rate is already divided by 100 and 12 and positive 
	// RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	
	//Note to self: r-rate, n- num of periods, p= present value, f-future value , t- type (true = pmt at end)
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return (Math.abs(FinanceLib.pmt(r/100/12, n, p, f, t)));
	}
}
