### Exercise 9 - Notes

After adding `JaCoCo` into `pom.xml` and running `mvn clean test`, JaCoCo generates a coverage report in `target/site/jacoco/index.html`.

I noticed that the `BankAccount` class only has 32% coverage in terms of instructions.

So I added a new test that utilizes a non-covered function :

```java
public void jacoco_coverage_extension() {
	BankAccount account = new BankAccount();
	account.setWithdrawLimit(1000);
	assertTrue(account.getBalance()==0);
	account.depositMoney(300);
	assertTrue(account.withdrawMoney(200));
	assertTrue(account.getBalance()==100);
	assertTrue(account.getAmountWithdrawn()==200);		
}
```

I ran `mvn clean test` again and noticed that the coverage increased to 33%.