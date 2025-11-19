### Exercise 7 - Notes

---

- `mvn clean`

Based on the output of the execution, Maven appears to have looked for a Maven project, and has found `BankApplication` using the `pom.xml` file. Then, it proceeded to wipe any files found within `target\`.

---

- `mvn test`

Same as before, Maven searched for a project and found it.
And then, it attempted to copy any assets it found in `src\main\resources` onto `target\classes`. (There are none in this case).

Maven proceeds to compile all source files with `javac` and saves the output files in `target\classes`. 

It did the same with the test files. This time, the output files are saved in `target\test-classes`.

It moved on and downloaded all the dependencies that were specified in `pom.xml`

Maven ran all the tests and has documented the results :
```powershell
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running bankAccountApp.ACHServiceTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.595 s -- in bankAccountApp.ACHServiceTest
[INFO] Running bankAccountApp.AllTests
[INFO] Tests run: 31, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.031 s -- in bankAccountApp.AllTests
[INFO] Running bankAccountApp.BankAccountTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s -- in bankAccountApp.BankAccountTest
[INFO] Running bankAccountApp.BankTest
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s -- in bankAccountApp.BankTest
[INFO] Running bankAccountApp.PersonTest
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s -- in bankAccountApp.PersonTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 62, Failures: 0, Errors: 0, Skipped: 0
```

Additionally, Maven created the following folders within `target\` :
- `classes\` : This is where Maven placed the output `*.class` files after compiling the source files.

- `generated-sources\` : Contains an empty folder named `annotations`. 

- `generated-test-sources\` : Contains an empty folder named `test-annotations`.

- `maven-status\` : I haven't been able to get any information concerning this folder, however there appears to be some `*.lst` files that list the paths for source files and their corresponding `*.class` files.

- `surefire-reports\` : Detailed reports of the test results are saved here.

- `test-classes\` : `*.class` files that correspond to the tests.

---

- `mvn package`

This command goes through all the steps that Maven went through in `mvn test`. Maven also builds the project into a `.jar` file : `target\bank-application-1.0-SNAPSHOT.jar`.


---

- `mvn verify`

After running this command, and based on the output received, Maven appears to have gone through all the steps that it went through after running `mvn package`. My guess would be that verify wouldn't have bothered to build the `.jar` file if the tests didn't succeed. Let's verify that by adding a test that fails every time :

```java
	@Test
	public void fail_on_purpose() {
		// Given
		
		// Then
		assertEquals(false, true);
	}
```

Running `mvn test` causes a build failure for this exact reason.
```powershell
[ERROR] Tests run: 13, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.001 s <<< FAILURE! -- in bankAccountApp.PersonTest
[ERROR] bankAccountApp.PersonTest.fail_on_purpose -- Time elapsed: 0 s <<< FAILURE!
java.lang.AssertionError: expected:<false> but was:<true>
```

After running both `mvn package` and `mvn verify`, I notice the same results. None of these two commands have led to a new `.jar` file.


I consulted the documentation for more info on `mvn verify` :
- `verify` - run any checks on results of integration tests to ensure quality criteria are met

My hypothesis is that :

- Given that running `mvn verify` runs every preceeding phase of its lifecycle in a sequencial manner.

- `mvn package` has the same output as `mvn verify`.

I think that the difference is that there are no integration tests, and thus both `package` and `verify` have the same output. Also, `test` never yields a new `.jar` file regardless of test results.
