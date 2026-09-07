// PaymentMethodConverter.java
package com.eshwar.utility.modelmapper.converter.nametoid;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.PaymentMethodEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//I am creating a custom ModelMapper converter that converts a String into an Integer
public class PaymentMethodConverter extends AbstractConverter<String,Integer>
{

	@Override
	protected Integer convert(String source) //source=APM
	{
		
		log.info("PaymentMethodConverter Class convert method(---) is executed , source : {} ", source);
		if(source==null)
		{
			return null;
		}
		
		 PaymentMethodEnum paymentMethod = PaymentMethodEnum.getByName(source);// will return PaymentMethodEnum.APM
		 log.info("PaymentMethodEnum.getByName(---) method is returning , paymentMethod : {} ",paymentMethod);
		 return paymentMethod==null ?null : paymentMethod.getId();//1
				       
	}

}
