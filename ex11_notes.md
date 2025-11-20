### Exercise 11 - Notes

After implementing the cucumber test for the feature `bank_account_basic.feature`, I ran `mvn test` again and the test passes.

The scenario's details also appear in the command output :

```powershell
Scenario: A new account has zero balance # src/test/resources/features/bank_account_basic.feature:2
  Given I have a new bank account        # com.imt.mines.bank.bdd.BankAccountBasicSteps.i_have_a_new_bank_account()
  When I check its balance               # com.imt.mines.bank.bdd.BankAccountBasicSteps.i_check_its_balance()
  Then the balance should be 0           # com.imt.mines.bank.bdd.BankAccountBasicSteps.the_balance_should_be(java.lang.Double)
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.803 s -- in com.imt.mines.bank.bdd.RunBankAccountCucumberTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
```