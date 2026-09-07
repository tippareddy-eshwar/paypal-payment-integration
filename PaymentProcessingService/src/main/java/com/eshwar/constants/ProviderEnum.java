//ProviderEnum.java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum ProviderEnum
{
   
	PAYPAL(1,"PAYPAL");//Enum Constant

    private final Integer id;//1
    private final String name;//PAYPAL

    ProviderEnum(Integer id,String name)  
    {
        
        this.id = id;
        this.name = name;
    }
    
 // Get enum object by ID
    public static ProviderEnum getById(Integer id)
    {

    	//ProviderEnum.values() method will returns all enum constants:
        for (ProviderEnum provider : ProviderEnum.values()) 
        {

            if (provider.getId().equals(id)) 
            {
                return provider;            }
        }

        return null;
    }
    
    // Get enum object by Name
    public static ProviderEnum getByName(String name)
    {

    	//ProviderEnum.values() method will returns all enum constants:
        for (ProviderEnum provider : ProviderEnum.values()) 
        {

            if (provider.getName().equals(name)) 
            {
                return provider;
            }
        }

        return null;
    }
    
    
}
