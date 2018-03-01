package com.uterm.toolbox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * ToolBoxTest
 * 
 * test methods in ToolBox class
 */
public class Base62Tests {
    
    @Test
    public void encodeLongToBase62() {
        assertEquals("a", Base62.encode(0));
        assertEquals("99999999", Base62.encode((long)Math.pow(62, 8) - 1));
    }

    @Test
    public void decodeBase62ToLong() {
        assertEquals(0, Base62.decode("a"));
        assertEquals((long)Math.pow(62, 8) - 1, Base62.decode("99999999"));
    }

    @Test
    public void encodeAndDecode() {
        for (long i=0; i < (long)Math.pow(62, 2); i++) {
            String encodedText = Base62.encode(i);
            long decodedNumber = Base62.decode(encodedText);

            assertEquals(i, decodedNumber);
        }
    }
}