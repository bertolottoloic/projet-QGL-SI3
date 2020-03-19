package fr.unice.polytech.si3.qgl.zecommit;

import fr.unice.polytech.si3.qgl.zecommit.engine.Engine;
import fr.unice.polytech.si3.qgl.zecommit.engine.settings.*;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Nathan
 */
public class IntegrationTest {

    @Category(IntegrationTest.class)
    @Test
    public void week6Test(){
        String str[] = {" "};
        Engine.showWindow = false;
        Engine.engineSettings = new EngineSettingsWeek6();
        assertDoesNotThrow(() -> Engine.main(str));
    }

    @Category(IntegrationTest.class)
    @Test
    public void week7Test(){
        String str[] = {" "};
        Engine.showWindow = false;
        Engine.engineSettings = new EngineSettingsWeek7();
        assertDoesNotThrow(() -> Engine.main(str));
    }

    @Category(IntegrationTest.class)
    @Test
    @Disabled
    public void week8Test(){
        String str[] = {" "};
        Engine.showWindow = false;
        Engine.engineSettings = new EngineSettingsWeek8();
        assertDoesNotThrow(() -> Engine.main(str));
    }
}
