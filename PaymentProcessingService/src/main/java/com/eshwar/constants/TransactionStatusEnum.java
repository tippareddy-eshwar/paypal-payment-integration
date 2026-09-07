//TransactionStatusEnum .java(Enum Class)
package com.eshwar.constants;
import lombok.Getter;

@Getter
public enum TransactionStatusEnum 
{
	
	CREATED(1,"CREATED"),//Enum Constant
	INITIATED(2,"INITIATED"),//Enum Constant
	APPROVED(3,"APPROVED"),//Enum Constant
	PENDING(4,"PENDING"),//Enum Constant
	SUCCESS(5,"SUCCESS"),//Enum Constant
	FAILED(6,"FAILED");//Enum Constant
	
    private final Integer id;
	private final String name;

	TransactionStatusEnum(Integer id,String name) 
	 {
	      
		 
	        this.id = id;
	        this.name = name;
	}
	
	  // Get enum object by ID
    public static TransactionStatusEnum getById(Integer id)
    {

    	//TransactionStatusEnum.values() method will returns all enum constants:
        for (TransactionStatusEnum status : TransactionStatusEnum.values()) 
        {

            if (status.getId().equals(id)) 
            {
                return status;
            }
        }

        return null;
    }
    
    // Get enum object by Name
    public static TransactionStatusEnum getByName(String name)
    {

    	//TransactionStatusEnum.values() method will returns all enum constants:
        for (TransactionStatusEnum status : TransactionStatusEnum.values()) 
        {

            if (status.getName().equals(name)) 
            {
                return status;
            }
        }

        return null;
    }
    
    
	
}
