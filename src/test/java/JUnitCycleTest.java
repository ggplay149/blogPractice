import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() { System.out.println("@1"); }

    @Test
    public void Test2() {
        System.out.println("@2");
    }

    @Test
    public void Test3() {
        System.out.println("@3");
    }

    @AfterAll
    static void AfterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach
    public void AfterEach() {
        System.out.println("@AfterEach");
    }


}
