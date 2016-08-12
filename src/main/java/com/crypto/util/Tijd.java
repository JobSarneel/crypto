package com.crypto.util;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Tijd {
	
	public static String dts(LocalDateTime ldt) {
				 
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy, HH:mm");
		 return fmt.print(ldt);
		
	}

}
