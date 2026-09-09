//PaymentMethodIdToNameConverter.java
package com.eshwar.utility.modelmapper.converter.idtoname;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.PaymentMethodEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//I am creating a custom ModelMapper converter that converts a Integer into an String
public class PaymentMethodIdToNameConverter extends AbstractConverter<Integer,String>
{

	@Override
	protected String convert(Integer source) //source=1
	{
		log.info("PaymentMethodIdToNameConverter Class convert method(---) is executed , source : {} ", source);//1
		
		if(source==null)
		{
			return null;
		}
		
		PaymentMethodEnum paymentMethodbyId = PaymentMethodEnum.getById(source);
		log.info("PaymentMethodEnum.getById(---) method is returning , paymentMethodbyId : {} ",paymentMethodbyId);//1
		return paymentMethodbyId==null ? null : paymentMethodbyId.getName();//APM
		
	}

}
