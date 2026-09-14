//IOrderServiceManagement 
package com.eshwar.service;
public interface IOrderServiceManagement 
{
   public String createOrder();
   public String captureOrder(String providerReference);
}
