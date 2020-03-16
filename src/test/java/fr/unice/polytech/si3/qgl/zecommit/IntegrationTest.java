package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.engine.Engine;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Nathan
 */
public class IntegrationTest {

    @Category(IntegrationTest.class)
    @Test
    public void week7Test(){
        String str[] = {" "};
        assertDoesNotThrow(() -> Engine.main(str));
    }
}
