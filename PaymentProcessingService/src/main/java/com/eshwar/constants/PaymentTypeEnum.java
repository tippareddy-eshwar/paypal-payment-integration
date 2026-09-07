//PaymentTypeEnum.java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum PaymentTypeEnum
{
   
	SALE(1,"SALE");//Enum Constant

   
    private final Integer id;//1
    private final String name;//SALE

    PaymentTypeEnum(Integer id,String name) 
    {
        
        this.id = id;
        this.name = name;
    }
    
    
    // Get enum object by ID
    public static PaymentTypeEnum getById(Integer id)
    {

    	//PaymentTypeEnum.values() method will returns all enum constants:
        for (PaymentTypeEnum paymentType : PaymentTypeEnum.values()) 
        {

            if (paymentType.getId().equals(id)) 
            {
                return paymentType;            }
        }

        return null;
    }
    
    // Get enum object by Name
    public static PaymentTypeEnum getByName(String name)
    {

    	//PaymentTypeEnum.values() method will returns all enum constants:
        for (PaymentTypeEnum paymentType :PaymentTypeEnum.values()) 
        {

            if (paymentType.getName().equals(name)) 
            {
                return paymentType;
            }
        }

        return null;
    }
    
    
	
}
