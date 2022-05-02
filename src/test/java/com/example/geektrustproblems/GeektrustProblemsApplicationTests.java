package com.example.geektrustproblems;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.geektrustproblems.service.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

@RunWith(MockitoJUnitRunner.class)
public class GeektrustProblemsApplicationTests {

	@InjectMocks
	InputLoaderService inputLoaderService;

	@Mock
	BalanceInputService balanceInputService;

	@Mock
	LoanInputService loanInputService;

	@Mock
	PaymentInputService paymentInputService;

	@Mock
	InputLoaderFactory factory;

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Test
	public void testInput1() throws Exception {

		String classpath = System.getProperty("user.dir");

		String file1 = classpath + "/src/main/resources/test1.txt";

		when(factory.loadInputService(anyString())).thenReturn(loanInputService);

		String [] str = {"LOAN IDIDI Dale 5000 1 6"};
		when(loanInputService.process(str)).thenReturn(str[0]);


		List<String> expectedOutput = inputLoaderService.loadInputFile(file1);

		Assert.assertEquals(0, expectedOutput.size());

	}

	@Test
	public void testInput2() throws Exception {


		String classpath = System.getProperty("user.dir");

		String file1 = classpath + "/src/main/resources/test2.txt";

		when(factory.loadInputService(anyString())).thenReturn(paymentInputService);

		String [] str = {"PAYMENT IDIDI Dale 1000 5"};
		when(paymentInputService.process(str)).thenReturn(str[0]);

		List<String> expectedOutput = inputLoaderService.loadInputFile(file1);

		Assert.assertEquals(0, expectedOutput.size());

	}

	@Test
	public void testInput3() throws Exception {


		String classpath = System.getProperty("user.dir");

		String file1 = classpath + "/src/main/resources/test3.txt";

		when(factory.loadInputService(anyString())).thenReturn(balanceInputService);

		String [] str = {"BALANCE","MBI", "Harry", "0"};
		when(balanceInputService.process(str)).thenReturn(str[0]);

		List<String> expectedOutput = inputLoaderService.loadInputFile(file1);

		Assert.assertEquals(1, expectedOutput.size());
		Assert.assertEquals(str[0], expectedOutput.get(0));

	}

	@Test
	public void serviceTests() throws Exception {


		String [] str = {"LOAN IDIDI Dale 5000 1 6"};

		when(loanInputService.process(str)).thenReturn(str[0]);
		String s = loanInputService.process(str);

		Assert.assertEquals(s, "LOAN IDIDI Dale 5000 1 6");

	}


}
