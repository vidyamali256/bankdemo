package com.cjc.serviceimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cjc.model.Account;
import com.cjc.service.RBI;

public class BankOperation4 implements RBI {

	Scanner sc = new Scanner(System.in);
	Connection con;
	public BankOperation4() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try(Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");)
			{
				
			}
			catch(Exception e)
			{
				e.printStackTrace();

			}

		} catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		
	}

	/**
	 * This method creates an account and add to the list
	 */
	@Override
	public void createAccount() {
		Account a = new Account();
		try {
			System.out.println("Account no");
			
			a.setAcccountNo(sc.nextInt());
			System.out.println("enter adhar no");
			long j = sc.nextLong();
			a.setAdharNo(j);
			System.out.println("enter name");
			a.setName(sc.next());
			System.out.println("enter amount");
			a.setAmount(sc.nextDouble());
			System.out.println("enter panno");
			a.setPanNo(sc.next());
			System.out.println("enter address");
			a.setAddress(sc.next());
			System.out.println("enter  username");
			a.setUserName(sc.next());
			System.out.println("enter password");
			a.setPassword(sc.next());
			System.out.println("enter mobileno");
			long x = sc.nextLong();
			a.setMobileNo(x);
			if (ValidateAccount(a)) {
				
				Statement st=con.createStatement();
				String sql="insert into bank values("+a.getAcccountNo()+","+a.getAdharNo()+",'"+a.getName()+"'"
						+ ","+a.getAmount()+",'"+a.getPanNo()+"','"+a.getAddress()+"','"+a.getUserName()+"','"+a.getPassword()+"',"+a.getMobileNo()+")";
					
				st.execute(sql);
					System.out.println("your account created successfully");
				
				
			} else {
				System.out.println("account not created");
			}

		} catch (Exception e) {
			//This exception is to handle invalid values in aadhar no.
			//or in mobile number
			System.out.println("plz enter proper value");
			System.out.println(" account not created");
		}
	}

	private boolean ValidateAccount(Account a) {
		boolean isValidAccount = true;
		if (!(a.getMobileNo() <= 9999999999l && a.getMobileNo() >= 1000000000l)) {
			isValidAccount = false;
			System.out.println("invalid mobile no");
		}

		if (a.getAmount() < 500) {
			isValidAccount = false;
			System.out.println("amount should be greater than 500");
		}
		if (!(a.getAdharNo() <= 999999999999l && a.getAdharNo() >= 100000000000l)) {
			isValidAccount = false;
			System.out.println("invalid adhar no");
		}
		if ((a.getPassword().length() < 7)) {
			isValidAccount = false;
			System.out.println("password should have atleast 7 characters");
		}

		return isValidAccount;
	}

	@Override
	public void deposite() {
		System.out.println(" enter account no");
		int accountno = sc.nextInt();
		try(Statement st=con.createStatement();)
		{
			String sql="select * from bank where Accountno="+accountno ;
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println("enter amount");
				double amu = sc.nextDouble();
				if (amu > 0)
				{
					double bal = rs.getDouble("amount") + amu;
					String sql1="update bank set amount="+bal+"where Account no="+accountno;

				
					System.out.println("balance available=" +bal);
				} else
				{
					System.out.println("amount should be > 0");
				}
			
			}
			
		}
		
		
		Iterator <Account>itr = list.iterator(); 
		boolean isFound = false;
		while(itr.hasNext())
		{
			Account a=itr.next();
			if (accountno == a.getAcccountNo())
			{
				isFound=true;
				System.out.println("enter amount");
				double amu = sc.nextDouble();
				if (amu > 0)
				{
					double bal = a.getAmount() + amu;
					a.setAmount(bal);
					System.out.println("balance available=" + a.getAmount());
				} else
				{
					System.out.println("amount should be > 0");
				}
				break;
			}
				
		}
		if(isFound ==false)
		{
			System.out.println("no account found");
		}
	}
		
	@Override
	public void withdraw() {

		System.out.println(" enter account no");
		int accountno = sc.nextInt();
		System.out.println(" enter username");
		String Username = sc.next();

		System.out.println(" enter password");
		String password = sc.next();

		boolean isFound=false;
		Iterator<Account> itr=list.iterator();
		while(itr.hasNext())
		{
			
			Account a=itr.next();
			
			if (accountno == a.getAcccountNo() && password.equals(a.getPassword())
					&& Username.equals(a.getUserName())) {
				
				isFound =true;
				System.out.println("enter amount");
				double amu = sc.nextDouble();
				if (amu > 0) {

					if (amu < a.getAmount()) {
						double bal = a.getAmount() - amu;

						a.setAmount(bal);
						System.out.println("balance available=" + a.getAmount());
					} else {
						System.out.println("you don't have sufficient balance");
					}
				} else {
					System.out.println("amount should be > 0");

				}
				break;
			}

		}
		if(isFound==false)
		{
			System.out.println("plz enter correct credentials");
		}
		
	}
	
		
		@Override
	public void balanceEnquiry() {
		System.out.println(" enter account no");
		int accountno = sc.nextInt();
		System.out.println(" enter username");
		String Username = sc.next();

		System.out.println(" enter password");
		String password = sc.next();
		
		boolean isFound=false;
		Iterator<Account> itr=list.iterator();
		while(itr.hasNext())
		{
			
			Account a=itr.next();
			if (accountno == a.getAcccountNo() && password.equals(a.getPassword())
					&& Username.equals(a.getUserName())) {
				
				isFound =true;
				System.out.println("balance=" + a.getAmount());
				break;
			}
		}
			if(isFound==false)
			{
				System.out.println("plz enter correct credentials");
			}
		}
	@Override
	public void accountDetails() {
		System.out.println(" enter account no");
		int accountno = sc.nextInt();
		System.out.println(" enter username");
		String Username = sc.next();

		System.out.println(" enter password");
		String password = sc.next();
		
		boolean isFound=false;
		Iterator<Account> itr=list.iterator();
		while(itr.hasNext())
		{
			
			Account a=itr.next();
	
			if (accountno == a.getAcccountNo() && password.equals(a.getPassword())
					&& Username.equals(a.getUserName())) {
				System.out.println("1.id:" + a.getId());
				System.out.println("2.name:" + a.getName());
				System.out.println("3.address:" + a.getAddress());
				System.out.println("4.mobile no:" + a.getMobileNo());
				System.out.println("5.amount:" + a.getAmount());
				System.out.println("6.username:" + a.getUserName());
				System.out.println("7.password:" + a.getPassword());
				System.out.println("8.pan no:" + a.getPanNo());
				System.out.println("9.account no:" + a.getAcccountNo());
				System.out.println("10.adhar no:" + a.getAdharNo());

				break;

			}
		}
		if(isFound==false)
		{
			System.out.println("plz enter correct credentials");
		}
	
		
	}

}
