//PaymentTypeConverter.java
package com.eshwar.utility.modelmapper.converter.nametoid;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.PaymentTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//I am creating a custom ModelMapper converter that converts a String into an Integer
public class PaymentTypeConverter  extends AbstractConverter<String, Integer>
{

	@Override
	protected Integer convert(String source) //source=SALE
	{
		
		log.info("PaymentTypeConverter Class convert(---) method is executed, source : {} ", source);
		if(source==null)
		{
			return null;
		}
		
	     PaymentTypeEnum paymentType= PaymentTypeEnum.getByName(source); //returns PaymentTypeEnum.SALE
	     log.info("PaymentTypeEnum.getByName(---) method is returning , paymentType : {} ", paymentType);
		 return paymentType==null ? null : paymentType.getId();//1
				   
	}

}
