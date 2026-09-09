//CreatePaymentResponse .java(POJO Class)
package com.eshwar.pojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//this annotation is a Jackson annotation used to control which fields are included when a Java object is converted into JSON.
public class CreatePaymentResponse 
{

	 private String txnReference;
	 private String txnStatus;
	 private String providerReference;
	 private String redirectURL;
}
