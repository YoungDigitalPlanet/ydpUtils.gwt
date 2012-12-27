package eu.ydp.gwtutil;

import eu.ydp.gwtutil.test.AbstractMockingTestBase;

public abstract class AbstractTestBase extends AbstractMockingTestBase<TestGuiceModule> {

	public AbstractTestBase() {
		super(TestGuiceModule.class);
	}


}
