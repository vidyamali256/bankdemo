package com.cjc.serviceimpl;

import java.util.Scanner;

import com.cjc.model.Account;
import com.cjc.service.RBI;

public class BankOpration1 implements RBI {

	Account arr[] = new Account[10];

	Scanner sc = new Scanner(System.in);
	int index = 0;

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
				try {
					arr[index] = a;
					index++;
					System.out.println("your account created successfully");
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("no  account can be created ");

				}
			} else {
				System.out.println("account not created");
			}

		} catch (Exception e) {
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
		int i;
		for (i = 0; i < index; i++) {
			if (accountno == arr[i].getAcccountNo()) {
				System.out.println("enter amount");
				double amu = sc.nextDouble();
				if (amu > 0) {
					double bal = arr[i].getAmount() + amu;
					arr[i].setAmount(bal);
					System.out.println("balance available=" + arr[i].getAmount());
				} else {
					System.out.println("amount should be > 0");
				}
				break;
			}

		}
		if (i == index) {
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

		int i;
		for (i = 0; i < index; i++) {
			if (accountno == arr[i].getAcccountNo() && password.equals(arr[i].getPassword())
					&& Username.equals(arr[i].getUserName())) {
				System.out.println("enter amount");
				double amu = sc.nextDouble();
				if (amu > 0) {

					if (amu < arr[i].getAmount()) {
						double bal = arr[i].getAmount() - amu;

						arr[i].setAmount(bal);
						System.out.println("balance available=" + arr[i].getAmount());
					} else {
						System.out.println("you don't have sufficient balance");
					}
				} else {
					System.out.println("amount should be > 0");

				}
				break;
			}
		}
		if (i == index) {
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
		int i;
		for (i = 0; i < index; i++) {
			if (accountno == arr[i].getAcccountNo() && password.equals(arr[i].getPassword())
					&& Username.equals(arr[i].getUserName())) {
				System.out.println("balance=" + arr[i].getAmount());
				break;
			}

		}
		if (i == index) {
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
		int i;
		for (i = 0; i < index; i++) {
			if (accountno == arr[i].getAcccountNo() && password.equals(arr[i].getPassword())
					&& Username.equals(arr[i].getUserName())) {
				System.out.println("1.id:" + arr[i].getId());
				System.out.println("2.name:" + arr[i].getName());
				System.out.println("3.address:" + arr[i].getAddress());
				System.out.println("4.mobile no:" + arr[i].getMobileNo());
				System.out.println("5.amount:" + arr[i].getAmount());
				System.out.println("6.username:" + arr[i].getUserName());
				System.out.println("7.password:" + arr[i].getPassword());
				System.out.println("8.pan no:" + arr[i].getPanNo());
				System.out.println("9.account no:" + arr[i].getAcccountNo());
				System.out.println("10.adhar no:" + arr[i].getAdharNo());

				break;

			}
		}
		if (i == index) {
			System.out.println("plz enter correct credentials");
		}

	}

}
