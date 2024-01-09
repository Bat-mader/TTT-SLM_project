package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {
    @Test
    public void getMarkercharTest1(){
        Player player = new Player('x');
        assertEquals('x', player.getMarker());
    }
    @Test
    public void getMarkercharTest2(){
        Player player = new Player('o');
        assertEquals('o', player.getMarker());
    }


}
