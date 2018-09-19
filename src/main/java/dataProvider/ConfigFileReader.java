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
		else throw new RuntimeException("Correct Dataset File Path not specified in the Configuration.properties file for the Key:datasetCorrectFile");		
	}
	
	public String getjSonFilePath(){
		String jSonFile = properties.getProperty("jSonFile");
		if(jSonFile!= null) return jSonFile;
		else throw new RuntimeException("jSonFile Path not specified in the Configuration.properties file for the Key:jSonFile");		
	}
	
	public String getDownloadPath(){
		String downloadPath = properties.getProperty("downloadPath");
		if(downloadPath!= null) return downloadPath;
		else throw new RuntimeException("Download File Path not specified in the Configuration.properties file for the Key:downloadPath");		
	}
	
	public String getConsentIdAdmin(){
		String consentIdAdmin = properties.getProperty("consentIdAdmin");
		if(consentIdAdmin!= null) return consentIdAdmin;
		else throw new RuntimeException("Consent ID Admin not specified in the Configuration.properties file for the Key:consentIdAdmin");		
	}
	
	public String getConsentIdAdminDar(){
		String consentIdAdminDar = properties.getProperty("consentIdAdminDar");
		if(consentIdAdminDar!= null) return consentIdAdminDar;
		else throw new RuntimeException("Consent ID Admin DAR not specified in the Configuration.properties file for the Key:consentIdAdminDar");		
	}
	
	public String getConsentIdMember(){
		String consentIdMember = properties.getProperty("consentIdMember");
		if(consentIdMember!= null) return consentIdMember;
		else throw new RuntimeException("Consent ID Member not specified in the Configuration.properties file for the Key:consentIdMember");		
	}
	
	public String getDatasetId(){
		String datasetId = properties.getProperty("datasetId");
		if(datasetId!= null) return datasetId;
		else throw new RuntimeException("Dataset ID not specified in the Configuration.properties file for the Key:datasetId");		
	}
	
	public String getObjectIdAdmin(){
		String objectIdAdmin = properties.getProperty("objectIdAdmin");
		if(objectIdAdmin!= null) return objectIdAdmin;
		else throw new RuntimeException("Object ID Admin not specified in the Configuration.properties file for the Key:objectIdAdmin");		
	}
	
	public String getObjectIdMember(){
		String objectIdMember = properties.getProperty("objectIdMember");
		if(objectIdMember!= null) return objectIdMember;
		else throw new RuntimeException("Object ID Member not specified in the Configuration.properties file for the Key:objectIdMember");		
	}
	
	public String getStructuredDul(){
		String structuredDul = properties.getProperty("structuredDul");
		if(structuredDul!= null) return structuredDul;
		else throw new RuntimeException("structuredDul not specified in the Configuration.properties file for the Key:structuredDul");		
	}
	
	public String getDbUrl(){
		String dbUrl = properties.getProperty("dbUrl");
		if(dbUrl!= null) return dbUrl;
		else throw new RuntimeException("dbUrl not specified in the Configuration.properties file for the Key:dbUrl");		
	}
	
	public String getDbUser(){
		String dbUser = properties.getProperty("dbUser");
		if(dbUser!= null) return dbUser;
		else throw new RuntimeException("dbUser not specified in the Configuration.properties file for the Key:dbUser");		
	}
	
	public String getDbPass(){
		String dbPass = properties.getProperty("dbPass");
		if(dbPass!= null) return dbPass;
		else throw new RuntimeException("dbPass not specified in the Configuration.properties file for the Key:dbPass");		
	}
	
	public String getAdminId(){
		String adminId = properties.getProperty("adminId");
		if(adminId!= null) return adminId;
		else throw new RuntimeException("adminId not specified in the Configuration.properties file for the Key:adminId");		
	}
	
	public String getMember1Id(){
		String member1Id = properties.getProperty("member1Id");
		if(member1Id!= null) return member1Id;
		else throw new RuntimeException("member1Id not specified in the Configuration.properties file for the Key:member1Id");		
	}
	
	public String getmember2Id(){
		String member2Id = properties.getProperty("member2Id");
		if(member2Id!= null) return member2Id;
		else throw new RuntimeException("member2Id not specified in the Configuration.properties file for the Key:member2Id");		
	}
	
	public String getMember3Id(){
		String member3Id = properties.getProperty("member3Id");
		if(member3Id!= null) return member3Id;
		else throw new RuntimeException("member3Id not specified in the Configuration.properties file for the Key:member3Id");		
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("firefox")) return DriverType.FIREFOX;
		else if(browserName.equalsIgnoreCase("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("chrome_headless")) return DriverType.CHROME_HEADLESS;
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
	
	public String getMongoDbUrl(){
		String mongoDbUrl = properties.getProperty("mongoDbUrl");
		if(mongoDbUrl!= null) return mongoDbUrl;
		else throw new RuntimeException("mongoDbUrl not specified in the Configuration.properties file for the Key:mongoDbUrl");		
	}
	
	public String getMongoDbUser(){
		String mongoDbUser = properties.getProperty("mongoDbUser");
		if(mongoDbUser!= null) return mongoDbUser;
		else throw new RuntimeException("mongoDbUser not specified in the Configuration.properties file for the Key:mongoDbUser");		
	}
	
	public String getMongoDbPass(){
		String mongoDbPass = properties.getProperty("mongoDbPass");
		if(mongoDbPass!= null) return mongoDbPass;
		else throw new RuntimeException("mongoDbPass not specified in the Configuration.properties file for the Key:mongoDbPass");		
	}

}
