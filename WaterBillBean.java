package com.bwssb.water.bean;

import com.bwssb.water.bean.*;
import com.bwssb.water.dao.*;
import com.bwssb.water.service.*;
import com.bwssb.water.servlets.*;
import com.bwssb.water.util.*;
import java.util.*;

public class WaterBillBean implements java.io.Serializable
{
  
  private int consumerNumber;
  private String billMonth;
  private String year;
  private long startReading;
  private long currentReading;
  private long totalConsumption;
  private String connectionType;
  private double fixedCharges;
  private double variableCharges;  
  private double totalCharges;
  
  
  public void setconsumerNumber(int consumerNumber)
  {
	  this.consumerNumber = consumerNumber;
  }
  public int getconsumerNumber()
  {
	  return consumerNumber;
  }
  public void setbillMonth(String billMonth)
  {
	  this.billMonth = billMonth;
  }
  public String getbillMonth()
  {
	  return billMonth;
  }
  public void setYear(String year)
  {
	  this.year = year;
  }
  public String getYear()
  {
	  return year;
  }
  public void setstartReading(long startReading)
  {
	  this.startReading = startReading;
  }
  public long getstartReading()
  {
	  return startReading;
  }
  public void setcurrentReading(long currentReading)
  {
	  this.currentReading = currentReading;
  }
  public long getcurrentReading()
  {
	  return currentReading;
  }
  public void settotalConsumption(long totalConsumption)
  {
	  this.totalConsumption = totalConsumption;
  }
  public long gettotalConsumption()
  {
	  return totalConsumption;
  }
  public void setconnectionType(String connectionType)
  {
	  this.connectionType = connectionType;
  }
  public String getconnectionType()
  {
	  return connectionType;
  }
  public void setfixedCharges(double fixedCharges)
  {
	  this.fixedCharges = fixedCharges;
  }
  public double getfixedCharges()
  {
	  return fixedCharges;
  }
  public void setvariableCharges(double variableCharges)
  {
	  this.variableCharges = variableCharges;
  }
  public double getvariableCharges()
  {
	  return variableCharges;
  }
  public void settotalCharges(double totalCharges)
  {
	  this.totalCharges = totalCharges;
  }
  public double gettotalCharges()
  {
	  return totalCharges;
  }
  
}
