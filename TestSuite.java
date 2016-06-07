import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   NeuralNetworkTests.class,
   
   InputLayerTest.class,
   OutputLayerTest.class,
   
   InputPerceptronTest.class,
   HiddenPerceptronTest.class,
   OutputPerceptronTest.class,
   
   InputLinksTest.class,
   OutputLinkTest.class,
   
})

public class TestSuite {   
} 