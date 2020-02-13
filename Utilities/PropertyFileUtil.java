package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {

	public static String  getValueForKey(String key) throws IOException{
		
		Properties configProperties=new Properties();
		
		FileInputStream fis=new FileInputStream("C:\\Users\\rangareddy.QEDGE\\Documents\\MyNewWorkspace2\\Stock_Accounting_Hybrid\\PropertiesFile\\Environment.properties");
		
		configProperties.load(fis);
				
	    return (String)configProperties.get(key);
		
	}
	
	
}
