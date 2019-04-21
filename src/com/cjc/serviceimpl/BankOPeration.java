package com.cjc.serviceimpl;

import java.util.Scanner;

import com.cjc.model.Account;
import com.cjc.service.RBI;

public class BankOPeration implements RBI {

	Account[] arr= new Account[1];
	Scanner sc = new Scanner(System.in);
	
	
	public void createAccount() 
	{
		int index = 0;
		int i;
		for(i=0; i<arr.length;i++) 
		{
			if(arr[i] == null) 
			{
				index = i;
				break;
			}    
		}
		if(i == arr.length) {
			System.out.println("No more space for new account");
			return;
		}
		
		Account a = new Account();
		System.out.println();
		System.out.println("enter Name");
			String  w=sc.next();	
		System.out.println("enter id");
		int x = sc.nextInt();
		
		System.out.println("enter PanNo");
		String y = sc.next();
		
		System.out.println("enter address");
		String z = sc.next();
		
		System.out.println("enter mobile no");
		Long a1 = sc.nextLong();
		System.out.println("enter user username");
		String c = sc.next();
			System.out.println("enter password");
		String d = sc.next();
		
		System.out.println("enter account no");
		int h = sc.nextInt();
		
		System.out.println("enter adhar no");
		long j = sc.nextLong();
	
		System.out.println("enter Amount");
		Double b = sc.nextDouble();
			
		if(b>500)
		{
			a.setAcccountNo(h);
			a.setAdharNo(j);
			a.setAmount(b);
			a.setPassword(d);
			a.setUserName(c);
			a.setMobileNo(a1);
			a.setAddress(z);
			a.setPanNo(y);
			a.setId(x);
			a.setName(w);

		}
		else
		{
			System.out.println("Min amount should be 500");
		}
		arr[index]=a;
		index++;
	
	}

	public void deposite() {
		System.out.println("Enter account number");
		int acc = sc.nextInt();
		int i;
		int index=0;
		for(i=0;i<arr.length;i++) {
			if(arr[i] != null && arr[i].getAcccountNo() == acc) {
				index = i;
				break;
			}
		}
		
		if(i == arr.length) {
			System.out.println("No account found");
		}
		else
		{
			System.out.println("enter deposite amount");
			double bal=sc.nextDouble();
			bal=arr[index].getAmount()+bal;
			arr[index].setAmount(bal);
		}
	}

	@Override
	public void withdraw() {
		System.out.println("Enter account number");
		int id = sc.nextInt();
		int i;
		int ind=0;
		for(i=0;i<arr.length;i++) {
			if(arr[i] != null && arr[i].getAcccountNo() == id) {
				ind = i;
				break;
			}
		}
		if(i == arr.length) {
			System.out.println("No account found");
		}
		else
		{
			System.out.println("enter withdraw amount");
			
			double bal=sc.nextDouble();
			if(bal<=arr[ind].getAmount())
			{	
				bal=arr[ind].getAmount()-bal;
				arr[ind].setAmount(bal);
			}
			else
			{
				System.out.println("you don't have sufficient balance");
			}
		}
	}

	@Override
	public void balanceEnquiry() {
		System.out.println("Enter account number");
		int id = sc.nextInt();
		int i;
		int ind=0;
		for(i=0;i<arr.length;i++) {
			if(arr[i] != null && arr[i].getAcccountNo() == id) {
				ind = i;
				break;
			}
		}
		if(i == arr.length) {
			System.out.println("No account found");
		}
		else
		{
			System.out.println("balance:"+arr[ind].getAmount());
		}
		
		
	}

	@Override
	public void accountDetails() 
	{
		System.out.println("Enter account number");
		int id = sc.nextInt();
		int i;
		int ind=0;
		for(i=0;i<arr.length;i++) {
			if(arr[i] != null && arr[i].getAcccountNo() == id) {
				ind = i;
				break;
			}
		}
		if(i == arr.length) {
			System.out.println("No account found");
		}
		else
		{
			String s = arr[ind].toString();
			System.out.println(s);
//			System.out.println("1.id:" + arr[ind].getId());
//			System.out.println("2.name:" + arr[ind].getName());
//			System.out.println("3.address:" + arr[ind].getAddress());
//			System.out.println("4.mobile no:" + arr[ind].getMobileNo());
//			System.out.println("5.amount:" + arr[ind].getAmount());
//			System.out.println("6.username:" + arr[ind].getUserName());
//			System.out.println("7.password:" + arr[ind].getPassword());
//			System.out.println("8.pan no:"+arr[ind].getPanNo());
//			System.out.println("9.account no:"+arr[ind].getAcccountNo());
//			System.out.println("10.adhar no:"+arr[ind].getAdharNo());
		}
	}

}
