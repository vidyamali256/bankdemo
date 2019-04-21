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

public class BankOperation3 implements RBI {

	Scanner sc = new Scanner(System.in);
	Connection con;

	public BankOperation3()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
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
				
					Statement st= con.createStatement();
					String sql="insert into Bank values("+a.getAcccountNo()+" , "
							+ ""+a.getAdharNo()+",'"+a.getName()+"',"+a.getAmount()+",'"+a.getPanNo()+"'"
									+ ",'"+a.getAddress()+"','"+a.getUserName()+"','"+a.getPassword()+"',"+a.getMobileNo()+")";
					
					st.execute(sql);
					st.close();
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
		
		String sql="select * from Bank where Accountno="+accountno;
		ResultSet rs =st.executeQuery(sql);
		if(rs.next())
		{
			System.out.println("enter amount");
			double amu = sc.nextDouble();
			if (amu > 0)
			{
				double bal = rs.getDouble("amount")+ amu;
				String sql1="update Bank set amount="+bal +"where Accountno="+accountno;
				st.executeUpdate(sql1);
				System.out.println("balance available=" + bal);
			} else
			{
				System.out.println("amount should be > 0");
			}
			
		}
		else
		{
			System.out.println("no account found");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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

		try(Statement st=con.createStatement();)
		{
		
		String sql="select * from Bank where Accountno="+accountno+" "
				+ "and username='"+Username+"' and password='"+password+"'";
		ResultSet rs=st.executeQuery(sql);
		if(rs.next())
		{
			System.out.println("enter amount");
				double amu = sc.nextDouble();
				if (amu > 0) {

					if (amu < rs.getDouble("amount")) {
						double bal = rs.getDouble("amount") - amu;
						String sql1="update Bank set amount="+bal +"where Accountno="+accountno;
						st.executeUpdate(sql1);

						
						System.out.println("balance available=" + bal);
					} else {
						System.out.println("you don't have sufficient balance");
					}
				} else {
					System.out.println("amount should be > 0");

				}
				
			}
		else
		{
			System.out.println("plz enter correct credentials");

		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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

			try(Statement st=con.createStatement();)
			{
			
			String sql="select * from Bank where Accountno="+accountno+" "
					+ "and username='"+Username+"' and password='"+password+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				System.out.println("balance available=" + rs.getDouble("amount"));
			} 
			else
			{
				System.out.println("plz enter correct credentials");

			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
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

			try(Statement st=con.createStatement();)
			{
			
			String sql="select * from Bank where Accountno="+accountno+" "
					+ "and username='"+Username+"' and password='"+password+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				System.out.println("1.accountno:" + rs.getDouble("Accountno"));
				System.out.println("2.name:" + rs.getString("name"));
				System.out.println("3.address:" + rs.getString("address"));
				System.out.println("4.mobile no:" + rs.getLong("mobileno"));
				System.out.println("5.amount:" + rs.getDouble("amount"));
				System.out.println("6.username:" + rs.getString("username"));
				System.out.println("7.password:" + rs.getString("password"));
				System.out.println("8.pan no:" + rs.getInt("panno"));
				System.out.println("9.adhar no:" + rs.getLong("adharno"));

			} 
			else
			{
				System.out.println("plz enter correct credentials");

			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		
			}

}
