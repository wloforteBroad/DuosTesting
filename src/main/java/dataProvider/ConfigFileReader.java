package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;
import enums.EnvironmentType;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath= "configs/Configurations.properties";
	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;		
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("urlLocal");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public String getAdminUserName() {
		String userName = properties.getProperty("userNameAdmin");
		if(userName != null) return userName;
		else throw new RuntimeException("adminUserName not specified in the Configuration.properties file.");
	}
	
	public String getAdminPassword() {
		String password = properties.getProperty("passwordAdmin");
		if(password != null) return password;
		else throw new RuntimeException("adminPassword not specified in the Configuration.properties file.");
	}
	
	public String getMemberUserName1() {
		String userName = properties.getProperty("userName1");
		if(userName != null) return userName;
		else throw new RuntimeException("memberUserName not specified in the Configuration.properties file.");
	}
	
	public String getMemberUserName2() {
		String userName = properties.getProperty("userName2");
		if(userName != null) return userName;
		else throw new RuntimeException("memberUserName not specified in the Configuration.properties file.");
	}
	
	public String getMemberUserName3() {
		String userName = properties.getProperty("userName3");
		if(userName != null) return userName;
		else throw new RuntimeException("memberUserName not specified in the Configuration.properties file.");
	}
	
	public String getMockUserName() {
		String mockUserName = properties.getProperty("mockUserName");
		if(mockUserName != null) return mockUserName;
		else throw new RuntimeException("mockUserName not specified in the Configuration.properties file.");
	}
	
	public String getMockUserMail() {
		String mockUserMail = properties.getProperty("mockUserMail");
		if(mockUserMail != null) return mockUserMail;
		else throw new RuntimeException("mockUserMail not specified in the Configuration.properties file.");
	}
	
	public String getMemberPassword() {
		String password = properties.getProperty("password");
		if(password != null) return password;
		else throw new RuntimeException("memberPassword not specified in the Configuration.properties file.");
	}
	
	public String getReportConfigPath(){
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public String getCorrectDatasetPath(){
		String datasetCorrectFile = properties.getProperty("datasetCorrectFile");
		if(datasetCorrectFile!= null) return datasetCorrectFile;
		else throw new RuntimeException("Correct Dataset File Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public String getConsentId(){
		String consentId = properties.getProperty("consentId");
		if(consentId!= null) return consentId;
		else throw new RuntimeException("Consent ID not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public String getDatasetId(){
		String datasetId = properties.getProperty("datasetId");
		if(datasetId!= null) return datasetId;
		else throw new RuntimeException("Dataset ID not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("firefox")) return DriverType.FIREFOX;
		else if(browserName.equalsIgnoreCase("chrome")) return DriverType.CHROME;
		else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
		else if(browserName.equals("safari")) return DriverType.SAFARI;
		else if(browserName.equals("firefox_headless")) return DriverType.FIREFOX_HEADLESS;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
 
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
		else if(environmentName.equals("dev")) return EnvironmentType.DEV;
		else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}

}
