package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	//Not for here - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//Not for here - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	
	//Just test size to see if built, also tested that order is descending
	@Test
	public void test() {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		
		//the first one in the table should be 800 and return 3.5
		assertEquals(rates.get(0).getiMinCreditScore(), 800, .1);		
		assertEquals(rates.get(0).getdInterestRate(), 3.50, .1);
		assertEquals(rates.get(1).getiMinCreditScore(), 750, .1);
	}

}
