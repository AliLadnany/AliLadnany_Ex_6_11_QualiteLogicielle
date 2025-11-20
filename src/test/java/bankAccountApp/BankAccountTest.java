package bankAccountApp;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void happy_path() {
		BankAccount bankAcc = new BankAccount();
		bankAcc.depositMoney(500);
		assertTrue(bankAcc.getBalance()==500);
	}
	
	@Test
	public void edge_case() {
		BankAccount bankAcc = new BankAccount();
		bankAcc.depositMoney(500);
		bankAcc.setWithdrawLimit(1000);
		bankAcc.withdrawMoney(760);
		assertTrue(bankAcc.getBalance()>=0);
	}
	
	@Test
	public void edge_case_2() {
	    BankAccount account = new BankAccount(100.0, 100.0, "2025-11-19", null);
	    // Withdrawing at the limit
	    assertTrue(account.withdrawMoney(100.0));
	    assertEquals("Balance should be reduced correctly", 0.0, account.getBalance(), 0.001);
	}
	
	@Test
	public void jacoco_coverage_extension() {
		BankAccount account = new BankAccount();
		account.setWithdrawLimit(1000);
		assertTrue(account.getBalance()==0);
		account.depositMoney(300);
		assertTrue(account.withdrawMoney(200));
		assertTrue(account.getBalance()==100);
		assertTrue(account.getAmountWithdrawn()==200);
	}

}
