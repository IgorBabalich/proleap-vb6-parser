package com.microsoft.msdn.statements;

import java.io.File;

import org.junit.Test;
import io.proleap.vb6.runner.VbParseTestRunner;
import io.proleap.vb6.runner.impl.VbParseTestRunnerImpl;

public class CloseTest {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/com/microsoft/msdn/statements/Close.cls");
		final VbParseTestRunner runner = new VbParseTestRunnerImpl();
		runner.parseFile(inputFile);
	}
}