package com.bwssb.water.service;

import com.bwssb.water.bean.*;
import com.bwssb.water.dao.*;
import com.bwssb.water.util.*;
import java.lang.String;

public class Administrator 
{
	   int consumerNumber;
       String billMonth;
       String year;
       long startReading;
       long currentReading;
       String connectionType;
       long tC;
       double min;
	   double s1,s2,s3,s4,s5;
	   double vcharges,tcharges,scharges,bcharges;
	   double sc1,sc2,sc3,sc4,sc5;
	   double total;
       
	public String addWaterBill(WaterBillBean waterBill)
	{
		int a = 0;
		long l = 0;
		String r;
		String d = "Domestic";
		String i = "Industrial";
		boolean t = true;
		boolean t1;
		 String h1="FAIL";
		
		 WaterBillBean o = waterBill;
		 
		 consumerNumber = o.getconsumerNumber();
		 billMonth = o.getbillMonth();
		 year = o.getYear();
		 startReading = o.getstartReading();
		 currentReading = o.getcurrentReading();
		 connectionType = o.getconnectionType();	 	 
		 try
			{
				if(waterBill == null)
				{
					throw new InvalidInputException("INVALID INPUT");
				}
				else if	(consumerNumber <= a )
				{
					throw new InvalidInputException("INVALID INPUT IN THE CONSUMER NUMBER FIELD");
				}
				else if	( billMonth == null || billMonth.isEmpty() || year == null || year.isEmpty() || connectionType == null || connectionType.isEmpty() )	
				{
					throw new InvalidInputException("INVALID INPUT IN MONTH, YEAR OR CONNECTION TYPE FIELD");
				}
				else if( startReading <= l )
				{
					throw new InvalidInputException("INVALID INPUT IN THE START READING FIELD");
				}
				else if( currentReading <= l )
				{
						throw new InvalidInputException("INVALID INPUT IN THE CURRENT READING FIELD");
				}
				else if( connectionType == null || connectionType.isEmpty()) 
				{
					throw new InvalidInputException("CONNECTION TYPE FIELD IS EMPTY");
				}
				else if( (!connectionType.equalsIgnoreCase(d) || connectionType.equalsIgnoreCase(i))
						&&  (connectionType.equalsIgnoreCase(d) || !connectionType.equalsIgnoreCase(i)) ) 
				{
					throw new InvalidInputException("INVALID INPUT IN CONNECTION TYPE FIELD ");
				}
				else if( currentReading < startReading )
				{
					throw new InvalidInputException("INVALID INPUT IN THE READINGS FIELD ");
				}
				else
				{
					WaterBillDAO w1 = new WaterBillDAO();	
					t1 = w1.waterBillExists(consumerNumber,billMonth,year);	
					
					if( t1 == t )
					{
						throw new InvalidInputException("BILL ALREADY EXISTS");
					}		
			
			tC = currentReading - startReading;
			
			o.settotalConsumption(tC);
			
			if(connectionType.equalsIgnoreCase(d))		
			{
				  o.setfixedCharges(56);
				  o.setboreWellCharges(100);
				     
				 min=o.getfixedCharges();
				 bcharges=o.getboreWellCharges();
			    
				  sc1=0.007;
			      sc2=0.011;
			      sc3=0.026;
			      sc4=0.045;
			      
			      if(tC<0.0)
			    {
			       tcharges=min;
			    }
			      else if(tC>=0.0 && tC<=8000.0)
			    {
			      vcharges=tC*sc1;
			      tcharges=vcharges;
			      scharges=14.0;
			    }
			     else if(tC>=8001.0 && tC<=25000.0)
			    {
			       s1=8000.0;
			       s2=tC-s1;
			       vcharges=(s1*sc1)+(s2*sc2);
			       tcharges=vcharges;
			       scharges=(25.0/100.0)*tcharges;
			    }
			    else if(tC>=25001.0 && tC<50000.0)
			    {
			      s1=8000.0;
			      s2=17000.0;
			      s3=tC-(s1+s2);
			      vcharges=(s1*sc1)+(s2*sc2)+(s3*sc3);
			      tcharges=vcharges;
			      scharges=(25.0/100.0)*tcharges;
			    }
			    else if(tC>=50000.0)
			    {
			       s1=8000.0;
			       s2=17000.0;
			       s3=25000.0;
			       s4=tC-(s1+s2+s3);
			       vcharges=(s1*sc1)+(s2*sc2)+(s3*sc3)+(s4*sc4);
			       tcharges=vcharges;
			       scharges=(25.0/100.0)*tcharges;
			    }
			      o.setvariableCharges(tcharges);
			      o.setsanitaryCharges(scharges);
			    
			      total = tcharges + scharges + bcharges;
			      o.settotalCharges(total);
			      
					WaterBillDAO w3 = new WaterBillDAO();
				      
					 h1 = w3.createWaterBill(waterBill);
				      System.out.println(h1);  
			}
			
			else if(connectionType.equalsIgnoreCase(i))
			{
				o.setfixedCharges(500);
				  o.setboreWellCharges(500);
					 min=o.getfixedCharges();
					 bcharges=o.getboreWellCharges();
			      sc1=0.05;
			      sc2=0.057;
			      sc3=0.065;
			      sc4=0.076;
			      sc5=0.087;
			
			     if(tC<0.0)
			    {
			       tcharges=min;
			    }
			    else if(tC>=0.0 && tC<=10000.0)
			    {
			      vcharges=tC*sc1;
			      tcharges=vcharges;
			      scharges=(25.0/100.0)*tcharges;
			    }
			     else if(tC>=10001.0 && tC<=25000.0)
			    {
			       s1=10000.0;
			       s2=tC-s1;
			       vcharges=(s1*sc1)+(s2*sc2);
			       tcharges=vcharges;
			       scharges=(25.0/100.0)*tcharges;
			    }
			    else if(tC>=25001.0 && tC<=50000.0)
			    {
			      s1=10000.0;
			      s2=15000.0;
			      s3=tC-(s1+s2);
			      vcharges=(s1*sc1)+(s2*sc2)+(s3*sc3);
			      tcharges=vcharges;
			      scharges=(25.0/100.0)*tcharges;
			    }
			    else if(tC>=50001.0 && tC<75000.0)
			    {
			       s1=10000.0;
			       s2=15000.0;
			       s3=25000.0;
			       s4=tC-(s1+s2+s3);
			       vcharges=(s1*sc1)+(s2*sc2)+(s3*sc3)+(s4*sc4);
			       tcharges=vcharges;
			       scharges=(25.0/100.0)*tcharges;
			    }
			    else if(tC>=75000.0)
			   {
			       s1=10000.0;
			       s2=15000.0;
			       s3=25000.0;
			       s4=25000.0;
			       s5=tC-(s1+s2+s3+s4);
			       vcharges=(s1*sc1)+(s2*sc2)+(s3*sc3)+(s4*sc4)+(s5*sc5);
			       tcharges=vcharges;
			       scharges=(25.0/100.0)*tcharges;   
			   }
			     
			     o.setvariableCharges(tcharges);
			     o.setsanitaryCharges(scharges);
				    
			      total = tcharges + scharges + bcharges;
			      o.settotalCharges(total);

					WaterBillDAO w3 = new WaterBillDAO();
				      
					 h1 = w3.createWaterBill(waterBill);
				      System.out.println(h1);  
			     
			  }
			   	
			}
			
		}
		catch(InvalidInputException e)
		{
			System.out.println(e.toString());
		}
		 return h1;
		
	}
	
	public WaterBillBean viewWaterBill(int consumerNumber,String billMonth,String year)
	{
		   this.consumerNumber = consumerNumber;
		   this.billMonth = billMonth;
		   this.year = year;
		
		 WaterBillBean o1 = new WaterBillBean();
		 
		 WaterBillDAO w4 = new WaterBillDAO();
		     o1 = w4.fetchWaterBill(consumerNumber,billMonth,year);
		     return o1;
		
	}

}
