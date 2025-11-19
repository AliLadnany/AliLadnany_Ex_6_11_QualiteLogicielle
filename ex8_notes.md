### Exercise 8 - Notes

New unit tests have been added in order to test `BankAccount.java` :

```java
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

}
```

The first two tests succeed but the last one fails.

The test isn't wrong : a user should be able to withdraw money even if the amount equals to the withdraw limit.

`BankAccount.withdrawMoney` has been changed in order to resolve this issue.

```java
public boolean withdrawMoney(double withdrawAmount) {
		if (withdrawAmount >= 0 && balance >= withdrawAmount && withdrawAmount <= withdrawLimit
				&& withdrawAmount + amountWithdrawn <= withdrawLimit) {
			balance = balance - withdrawAmount;
			success = true;
			amountWithdrawn += withdrawAmount;
		} else {
			success = false;
		}
		return success;
	}
```

Following this change, all the tests have succeeded.