package com.bwssb.water.util;

import java.lang.*;
import java.util.*;

public class InvalidInputException extends Exception
{
	String str1;
	public InvalidInputException(String str2)
	{
		str1 = str2;
	}
  public String toString()
  {
	 return ("MyException Occurred: "+str1);
  }
}
