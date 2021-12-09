package henrys.grocery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestProduct {
    @Test
    public void compareByName() {
        Product a = new Product("some-name", 1.0);
        Product b = new Product("some-name", 2.0);

        assertEquals(a, b);
    }
}
