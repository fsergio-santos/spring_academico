package com.academico.models.service.components;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;


public class OtpGenerate {
	
	private String id;               
    private String otpValue;         
    private LocalDateTime timeStamp;          
    private byte numberOfRetry;  
    
    public OtpGenerate() {
        this.numberOfRetry = 0;   
	}
    
    public OtpGenerate(String id) {
    	 this.id = id;
         Random random = new Random();
         int value = 100000 + random.nextInt(900000); 
         this.otpValue = String.valueOf(value);
         this.timeStamp = LocalDateTime.now().plusMinutes(5);       
         this.numberOfRetry = 0;   
    }

	public String getId() {
		return id;
	}

	public String getOtpValue() {
		return otpValue;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public byte getNumberOfRetry() {
		return numberOfRetry;
	}
  	
	public boolean validate(String value, LocalDateTime current ) {
	
	  if (value.length() != 6 || !value.matches("\\d+")) {
	        numberOfRetry++;         
	        return false;
	   }

	   if (numberOfRetry > 3) {
	       return false;
	   }

	   if (Duration.between(getTimeStamp(), current.minusMinutes(5)).toMinutes() > 5) { 
		    return false; 
		}

	   if (otpValue.equals(value)) {
	       return true;              
	   }

	   numberOfRetry++;              
	   return false;                 
	    
	}

	public void setOtpValue(String otpValue) {
		this.otpValue = otpValue;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
