// PayPalCreateOrderAPIConstants .java
package com.eshwar.constants;

public final class PayPalCreateOrderAPIConstants 
{

	//To Prevent the Object Creation From Outside
	  private PayPalCreateOrderAPIConstants ()
	  {
		  
	  }
	  
	  
	    public  static final String PAYPAL_REQUEST_ID= "PayPal-Request-Id";
	  
	    public static final String INTENT_CAPTURE = "CAPTURE";

	    public static final String PAYMENT_METHOD_IMMEDIATE = "IMMEDIATE_PAYMENT_REQUIRED";

	    public static final String LANDING_PAGE_LOGIN = "LOGIN";

	    public static final String SHIPPING_PREFERENCE_NO_SHIPPING = "NO_SHIPPING";

	    public static final String USER_ACTION_PAY_NOW = "PAY_NOW";
}
