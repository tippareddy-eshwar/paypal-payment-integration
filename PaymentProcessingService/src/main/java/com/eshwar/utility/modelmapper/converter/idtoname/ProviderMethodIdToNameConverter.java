 //ProviderMethodIdToNameConverter.java
package com.eshwar.utility.modelmapper.converter.idtoname;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.ProviderEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//I am creating a custom ModelMapper converter that converts a String into an Integer
public class ProviderMethodIdToNameConverter extends AbstractConverter<Integer, String>
{

	@Override
	protected String convert(Integer source) //source=1
	{
		log.info("ProviderMethodIdToNameConverter Class convert(---) method is Executed , source : {} ", source);//1
		if(source==null)
		{
			return null;
		}
		
		 ProviderEnum provider = ProviderEnum.getById(source);
		 
		 log.info("ProviderEnum.getById(---) method is returning , provider : {} ", provider);
		 
		 return provider==null ? null : provider.getName();//PAYPAL
	}

}
