package com.bwssb.water.dao;

import com.bwssb.water.bean.*;
import com.bwssb.water.dao.*;
import com.bwssb.water.servlets.*;
import com.bwssb.water.util.*;

import java.sql.*;
import java.util.*;
import java.lang.String;


public class WaterBillDAO 
{
	
	   int consumerNumber;
	   String billMonth;
	   String year;
	   long startReading;
	   long currentReading;
	   long totalConsumption;
	   String connectionType;
	   double fixedCharges;
	   double variableCharges;  
	   double totalCharges;
	   
   
   public String createWaterBill(WaterBillBean waterBill)
   {      
	   
	   Connection myConn = DButil.getDBconnection();
	    
       WaterBillBean wb = waterBill;

       consumerNumber = wb.getconsumerNumber();
       billMonth = wb.getbillMonth();
       year = wb.getYear();
       startReading = wb.getstartReading();
       currentReading = wb.getcurrentReading();
       totalConsumption = wb.gettotalConsumption();
       connectionType = wb.getconnectionType();
       fixedCharges = wb.getfixedCharges();
       variableCharges = wb.getvariableCharges();
       totalCharges = wb.gettotalCharges();

       
       try 
  	 {
  		  
  	   String query = "insert into WATERBILL_TB (CONSUMER_NO, MONTH , YEAR , START_READING , CURRENT_READING , TOTAL_CONSUMPTION , CONNECTION_TYPE , FIXED_CHARGES , VARIABLE_CHARGES , TOTAL_CHARGES ) VALUES(?,?,?,?,?,?,?,?,?,?)";
  		       
  		  	PreparedStatement ps = myConn.prepareStatement(query);
  		  	

		  	ps.setInt(1, wb.getconsumerNumber());
			ps.setString(2, wb.getbillMonth());
		  	ps.setString(3, wb.getYear());
		  	ps.setLong(4, wb.getstartReading());
		  	ps.setLong(5, wb.getcurrentReading());
		  	ps.setLong(6, wb.gettotalConsumption());
		  	ps.setString(7, wb.getconnectionType());
		  	ps.setDouble(8, wb.getfixedCharges());
		  	ps.setDouble(9, wb.getvariableCharges());
		  	ps.setDouble(10, wb.gettotalCharges());
		  	
		  	 int i = ps.executeUpdate();
		      if(i == 1)
		      {
		        return "SUCCESS";
		      }
		} 
	 
	     catch(SQLException e) 
	     {
		        e.printStackTrace();
		 }
		    return "FAIL";
   }
	   

   public WaterBillBean fetchWaterBill(int consumerNumber,String billMonth,String year)
   {
          try 
		  {
			  Connection myConn = DButil.getDBconnection();
	          
	          String q1 = "select * from WATERBILL_TB WHERE CONSUMER_NO = ? AND MONTH = ? AND YEAR = ? ";
	          
	          PreparedStatement myStmt= myConn.prepareStatement(q1);
	          myStmt.setInt(1, consumerNumber);
	          myStmt.setString(2, billMonth);
	          myStmt.setString(3, year);
	          ResultSet myRs = myStmt.executeQuery();
	     
	         while(myRs.next())
	         {
	    	   
        	    WaterBillBean wb = new WaterBillBean();
    	       
                wb.setconsumerNumber(myRs.getInt("CONSUMER_NO"));
                wb.setbillMonth(myRs.getString("MONTH"));
                wb.setYear(myRs.getString("YEAR"));
                wb.setstartReading(myRs.getLong("START_READING"));
                wb.setcurrentReading(myRs.getLong("CURRENT_READING"));
                wb.settotalConsumption(myRs.getLong("TOTAL_CONSUMPTION"));
                wb.setconnectionType(myRs.getString("CONNECTION_TYPE"));
                wb.setfixedCharges(myRs.getDouble("FIXED_CHARGES"));
                wb.setvariableCharges(myRs.getDouble("VARIABLE_CHARGES"));
                wb.settotalCharges(myRs.getDouble("TOTAL_CHARGES"));
           
                return wb;
	         }	       
	       }
	      catch (SQLException e) 
	      {
	            e.printStackTrace();
	      }
          
          return null;
	}
	  
   
   public boolean waterBillExists(int consumerNumber,String billMonth,String year)
   {
	   return fetchWaterBill(consumerNumber, billMonth, year) != null;
   }
   
}


