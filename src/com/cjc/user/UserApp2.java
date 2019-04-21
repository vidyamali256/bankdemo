package com.cjc.user;

import java.util.Scanner;

import com.cjc.service.RBI;
import com.cjc.serviceimpl.BankOPeration;
import com.cjc.serviceimpl.BankOperation3;
import com.cjc.serviceimpl.BankOpration1;
import com.cjc.serviceimpl.BankOpration2;

public class UserApp2 
{
public static void main(String[] args) 
{
	Scanner sc= new Scanner(System.in);
	   
	
	RBI r= new BankOperation3();
	
	boolean condition= true;
	
	while(condition)
	{  
		try {
		System.out.println();
		System.out.println("select operation");
		System.out.println("1.Create acount");
		System.out.println("2.Deposite");
		System.out.println("3.withdraw");
		System.out.println("4.Balance Enquiry");
		System.out.println("5.Account Details");
		System.out.println("6.Exit");
		
		System.out.println();
	   System.out.println(" enter your choice");
	   
		int choice = sc.nextInt();
		
		switch(choice)
		{
		case 1:
			
			r.createAccount();
			break;
		case 2:
			
			r.deposite();
			break;
		case 3:
			
			r.withdraw();
			break;
		case 4:


			r.balanceEnquiry();
			break;
		case 5:
		    
		    r.accountDetails();
		   	break;
		case 6:
			condition = false;
			break;
		default:
			System.out.println("plz enter proper choice");
	   	
		}
		}
		catch(Exception e)
		{
			System.out.println("wrong value: " + sc.next());
			e.printStackTrace();
		}
}
}
}