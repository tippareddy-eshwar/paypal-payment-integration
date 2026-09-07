// AppConfig .java(Configuration Class)
package com.eshwar.config;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eshwar.dto.TranscationDTO;
import com.eshwar.entity.Transaction;
import com.eshwar.utility.modelmapper.converter.nametoid.PaymentMethodConverter;
import com.eshwar.utility.modelmapper.converter.nametoid.PaymentTypeConverter;
import com.eshwar.utility.modelmapper.converter.nametoid.ProviderMethodConverter;
import com.eshwar.utility.modelmapper.converter.nametoid.TransactionStatusConvertor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig 
{
	
	//Request Structure into DTO 
	 @Bean
     public ModelMapper modelMapper() 
	 {
    	 
		 log.info("AppConfig Class modelMapper() method is executed...");
		 //Creating ModelMapper Object
         ModelMapper modelMapper = new ModelMapper();
		 
		 //Use Strict Matching so that only properties with the exact name and type are mapped
		 modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 
		 
		    // Payment Method Converter, This converter will convert a String value into an Integer value.
	        Converter<String, Integer> paymentMethodConverter =new PaymentMethodConverter();
	        
	        // Provider Method Converter, This converter will convert a String value into an Integer value.
	        Converter<String, Integer> providerMethodConverter =new ProviderMethodConverter();
	        
	        // Provider Method Converter, This converter will convert a String value into an Integer value.
	        Converter<String, Integer> paymentTypeConverter =new PaymentTypeConverter();
	        
	        //Provider Method Converter, This converter will convert a String value into an Integer value.
	        Converter<String, Integer> transactionStatusConverter =new TransactionStatusConvertor();	        
	        
	        //modelMapper.addMappings(--) method will tells to the  ModelMapper about a custom mapping rule. Normally ModelMapper can automatically maps the  properties with matching names. But in this case fields types are different, So we need to we explicitly tell to it
            //that When mapping paymentMethod, use my custom converter.
	        modelMapper.addMappings(
	        		             //PropertyMap is a Abstract Class, This Class creates a mapping configuration between the CreateTranscationRequest(Source) and Transaction(Destination
	              new PropertyMap<TranscationDTO, Transaction>() 
	               {

	        	      //This is the method where we define our custom mapping rules. Inside this method we tell to the ModelMapper that Which source property?, Which Converter and Which destination property
	                  @Override
				      protected void configure()
				      {
					    log.info("AppConfig Class of modelMapper(--) of PropertyMap Class configure() method is Executed...");
					    using(paymentMethodConverter)// using(--) method is final protected method of PropertyMap Abstract Class which accepts Converter as the Parameter. This method tells that  ModelMapper will use our converter instead of trying normal automatic mapping.
                         .map(source.getPaymentMethod() ,destination.getPaymentMethodId());
					    
					    
					    using(providerMethodConverter).map(source.getProvider() ,destination.getProviderId());
					    
					    using(paymentTypeConverter).map(source.getPaymentType() ,destination.getPaymentTypeId());
					    
					    using(transactionStatusConverter).map(source.getTxnStatus() ,destination.getTxnStatusId());
					    
					    
				      }  
	        	
	              });
		 
		 
		 // Return the Model Mapper configured object
		 return  modelMapper;
	 }
}
