package src.test;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	src.test.NodeTest.class, 
	src.test.MinPriorityQueueTest.class,
	src.test.HuffmanTest.class
})
public class AllMyTests {
  //nothing
}