package com.uterm.toolbox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * ToolBoxTest
 * 
 * test methods in ToolBox class
 */
public class ToolBoxTest {
    
    @Test
    public void encodeLongToBase62() {
        String encoded = Base62.encode(1L);       
        assertEquals("A", encoded);
    }
}