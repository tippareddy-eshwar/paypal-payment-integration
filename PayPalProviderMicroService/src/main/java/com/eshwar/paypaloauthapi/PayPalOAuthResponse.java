//PayPalOAuthResponse .java
package com.eshwar.paypaloauthapi;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PayPalOAuthResponse 
{

	   @JsonProperty("access_token")
	   private String accessToken;
	   
	   @JsonProperty("expires_in")
	   private long expiresIn;
}
