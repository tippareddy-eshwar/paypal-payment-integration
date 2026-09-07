//aymentMethodEnum,java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum PaymentMethodEnum
{

	APM(1,"APM");//Enum Constant

    private final Integer id;//1
    private final String name;//APM	

    PaymentMethodEnum(Integer id,String name)
    {
       
        this.id = id;
        this.name = name;
    }
    
    
    // Get enum object by ID
    public static PaymentMethodEnum getById(Integer id)
    {

    	//PaymentMethodEnum.values() method will returns all enum constants:
        for (PaymentMethodEnum paymentMethod : PaymentMethodEnum.values()) 
        {

            if (paymentMethod.getId().equals(id)) 
            {
                return paymentMethod;            }
        }

        return null;
    }
    
    // Get enum object by Name
    public static PaymentMethodEnum getByName(String name)
    {

    	//PaymentMethodEnum.values() method will returns all enum constants:
        for (PaymentMethodEnum paymentMethod : PaymentMethodEnum.values()) 
        {

            if (paymentMethod.getName().equals(name)) 
            {
                return paymentMethod;
            }
        }

        return null;
    }
    
    
	
}
