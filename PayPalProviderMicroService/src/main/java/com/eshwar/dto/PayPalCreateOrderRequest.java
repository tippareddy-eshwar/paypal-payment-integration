// PayPalCreateOrderRequest.java
package com.eshwar.dto;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayPalCreateOrderRequest //Outer Class
{
    
	    private String intent;

	    @JsonProperty("payment_source")
	    private PaymentSource paymentSource;

	    @JsonProperty("purchase_units")
	    private List<PurchaseUnit> purchaseUnits;


	    @Data
	    @Builder
	    public static class PaymentSource //static inner class
	    {

	        private Paypal paypal;
	    }


	    @Data
	    @Builder
	    public static class Paypal ////static inner class
	    {

	        @JsonProperty("experience_context")
	        private ExperienceContext experienceContext;
	    }


	    @Data
	    @Builder
	    public static class ExperienceContext //static inner class
	    {

	        @JsonProperty("payment_method_preference")
	        private String paymentMethodPreference;

	        @JsonProperty("landing_page")
	        private String landingPage;

	        @JsonProperty("shipping_preference")
	        private String shippingPreference;

	        @JsonProperty("user_action")
	        private String userAction;

	        @JsonProperty("return_url")
	        private String returnUrl;

	        @JsonProperty("cancel_url")
	        private String cancelUrl;
	    }


	    @Data
	    @Builder
	    public static class PurchaseUnit //static inner class
	    {

	        private Amount amount;
	    }


	    @Data
	    @Builder
	    public static class Amount //static inner class
	    {

	        @JsonProperty("currency_code")
	        private String currencyCode;

	        private String value;
	    }
}
