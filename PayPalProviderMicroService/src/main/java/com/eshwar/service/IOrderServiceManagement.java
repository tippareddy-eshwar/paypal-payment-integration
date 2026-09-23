//IOrderServiceManagement 
package com.eshwar.service;
import com.eshwar.pojo.CreateOrderRequest;
import com.eshwar.pojo.CreateOrderResponse;

public interface IOrderServiceManagement 
{
   public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
   public String captureOrder(String providerReference);
}
