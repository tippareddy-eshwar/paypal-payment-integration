//aymentMethodEnum,java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum PaymentMethodEnum
{

	APM(1,"APM");//Enum Constant

    private final Integer id;
    private final String name;

    PaymentMethodEnum(Integer id,String name)
    {
       
        this.id = id;
        this.name = name;
    }
}
