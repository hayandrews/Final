package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//Done and working - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//Done and working - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void getRatetest() throws RateException {
		assertEquals(RateBLL.getRate(770), 3.75, .01);
		assertEquals(RateBLL.getRate(800), 3.5, .01);
		assertEquals(RateBLL.getRate(670), 4.5, .01);
	}
	
	@Test(expected=RateException.class)
	public void RateExceptiontest() throws RateException{
		RateBLL.getRate(599);
	}
	
	@Test
	public void gatPaymenttest() {
		double testnumber = RateBLL.getPayment(4, 360, 300000, 0, false);
		assertEquals(testnumber, 1432.25, .01);
	}

}
