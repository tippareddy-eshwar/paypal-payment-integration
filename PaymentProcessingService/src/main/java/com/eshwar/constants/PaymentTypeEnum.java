//PaymentTypeEnum.java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum PaymentTypeEnum
{
   
	SALE(1,"SALE");//Enum Constant

   
    private final Integer id;
    private final String name;

    PaymentTypeEnum(Integer id,String name) 
    {
        
        this.id = id;
        this.name = name;
    }
}
