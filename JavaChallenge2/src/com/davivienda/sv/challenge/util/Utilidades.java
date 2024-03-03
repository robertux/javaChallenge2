package com.davivienda.sv.challenge.util;

import java.util.HashMap;

public class Utilidades {
	
			public static final String DATE_PATTERN = "\\b\\d{1,2}\\s+(?:January|February|March|April|May|June|July|August|September|October|November|December)\\b";
    
		    private static final HashMap<Character, Integer> ROMAN_VALUES = new HashMap<>();
		    
		    static {
		        ROMAN_VALUES.put('I', 1);
		        ROMAN_VALUES.put('V', 5);
		        ROMAN_VALUES.put('X', 10);
		        ROMAN_VALUES.put('L', 50);
		        ROMAN_VALUES.put('C', 100);
		        ROMAN_VALUES.put('D', 500);
		        ROMAN_VALUES.put('M', 1000);
		    }
		    
		    public static int romanToInteger(String roman) {
		        int result = 0;
		        int prevValue = 0;
		        
		        for (int i = roman.length() - 1; i >= 0; i--) {
		            int value = ROMAN_VALUES.get(roman.charAt(i));
		            if (value < prevValue) {
		                result -= value;
		            } else {
		                result += value;
		            }
		            prevValue = value;
		        }
		        
		        return result;
		    }
		}
	
