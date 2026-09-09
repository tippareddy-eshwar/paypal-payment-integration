//PaymentTypeIdToNameConverter.java
package com.eshwar.utility.modelmapper.converter.idtoname;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.PaymentTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//I am creating a custom ModelMapper converter that converts a  Integer into String
public class PaymentTypeIdToNameConverter  extends AbstractConverter<Integer,String>
{

	@Override
	protected String convert(Integer source) //source=1
	{
		
		log.info("PaymentTypeIdToNameConverter Class convert(---) method is executed, source : {} ", source);//1
		if(source==null)
		{
			return null;
		}
		
	     PaymentTypeEnum paymentTypeById= PaymentTypeEnum.getById(source); //returns 1
	     log.info("PaymentTypeEnum.getById(---) method is returning , paymentTypeById : {} ",paymentTypeById);//1
		 return paymentTypeById==null ? null : paymentTypeById.getName();//SALE
				   
	}

}
