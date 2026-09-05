//ProviderEnum.java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum ProviderEnum
{
   
	PAYPAL(1,"PAYPAL");//Enum Constant

    private final Integer id;
    private final String name;

    ProviderEnum(Integer id,String name)  
    {
        
        this.id = id;
        this.name = name;
    }
}
