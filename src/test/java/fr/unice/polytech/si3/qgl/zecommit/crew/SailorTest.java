package fr.unice.polytech.si3.qgl.zecommit.crew;

import fr.unice.polytech.si3.qgl.zecommit.crew.Sailor;
import fr.unice.polytech.si3.qgl.zecommit.entite.Entity;
import fr.unice.polytech.si3.qgl.zecommit.entite.Oar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

public class SailorTest {

    Sailor sailor1;

    @BeforeEach
    void setUp() {
        sailor1 = new Sailor(1, 6, 0, "sailor1");
    }


    @Test
    void distanceToNearestEntityTest(){
        
        Oar o1 = new Oar(2,0);
        Oar o2 = new Oar(2,3);
        Oar o3 = new Oar(7,0);
        Oar o4 = new Oar(9,3);
        List<Entity> e = Arrays.asList(new Entity[]{o1,o2,o3,o4});
        assertEquals(1,sailor1.distanceToNearestEntity(e));
    }




}