 //ProviderMethodConverter.java
package com.eshwar.utility.modelmapper.converter.nametoid;
import org.modelmapper.AbstractConverter;
import com.eshwar.constants.ProviderEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//I am creating a custom ModelMapper converter that converts a String into an Integer
public class ProviderMethodConverter extends AbstractConverter<String, Integer>
{

	@Override
	protected Integer convert(String source) //source=PAYPAL
	{
		log.info("ProviderMethodConverter Class convert(---) method is Executed , source : {} ", source);
		if(source==null)
		{
			return null;
		}
		
		 ProviderEnum provider = ProviderEnum.getByName(source);//returns ProviderEnum.PAYPAL
		 
		 log.info("ProviderEnum.getByName(---) method is returning , provider : {} ", provider);
		 return provider==null ? null : provider.getId();//1
	}

}
