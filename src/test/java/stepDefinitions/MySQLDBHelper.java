package stepDefinitions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import managers.FileReaderManager;

public class MySQLDBHelper {
	// JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = FileReaderManager.getInstance().getConfigReader().getDbUrl();

	// Database credentials
	private static final String USER = FileReaderManager.getInstance().getConfigReader().getDbUser();
	private static final String PASS = FileReaderManager.getInstance().getConfigReader().getDbPass();
	private static final String DATABASE = "consent";

	private Connection conn = null;
	private Statement stmt = null;

	public MySQLDBHelper() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void open() {
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void close() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private List<Integer> getMembersId(Integer roleId) {
		List<Integer> memberIds = new ArrayList<>();
		try {
			String sql = "SELECT user_role.dacUserId FROM consent.dacuser INNER JOIN consent.user_role ON dacuser.dacUserId = user_role.dacUserId WHERE user_role.roleId = "
					+ roleId + "";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				memberIds.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
		return memberIds;
	}

	private List<Integer> getElectionIds(String referenceId) {
		List<Integer> electionIds = new ArrayList<>();
		try {
			String sql = "SELECT electionId FROM " + DATABASE + ".election WHERE referenceId = '" + referenceId + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				electionIds.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
		return electionIds;
	}

	private void createElection(String referenceId, String status, String type) {
		try {
			// Add Election
			String query1 = "INSERT INTO `consent`.`election` (`status`, `createDate`, `referenceId`, `useRestriction`, `translatedUseRestriction`, `electionType`, `dataUseLetter`, `dulName`, `archived`, `version`) VALUES ('"
					+ status + "', '2018-08-28 11:49:48', '" + referenceId
					+ "', '{\\\"type\\\":\\\"or\\\",\\\"operands\\\":[{\\\"type\\\":\\\"named\\\",\\\"name\\\":\\\"http://purl.obolibrary.org/obo/DUO_0000015\\\"},{\\\"type\\\":\\\"everything\\\"}]}', 'Samples are restricted for use under the following conditions:<br>Data is limited for health/medical/biomedical research. [HMB]<br>Future use for population origins or ancestry research is prohibited. [POA]<br>Commercial use prohibited. [NCU]<br>Data use for methods development research irrespective of the specified data use limitations is not prohibited.<br>Future use as a control set for any type of health/medical/biomedical study is not prohibited.<br>Other restrictions: OTHER TERMS.', '"
					+ type
					+ "', 'https://storage.googleapis.com/broad-dsde-dev-consent/57146b0d-34b4-4788-aaac-b01549084730.pdf', 'dulNameConsent.pdf', 0, 1)";
			PreparedStatement preparedStmt = conn.prepareStatement(query1);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void addMembersVotes(String consentId, Integer vote, Integer electionId) {
		List<Integer> memberIds = new ArrayList<>();
		memberIds = getMembersId(1);
		memberIds.forEach(memberId -> {
			try {
				String query1 = "INSERT INTO `" + DATABASE
						+ "`.`vote` (`vote`, `dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES ("
						+ vote + ", " + memberId + ", ?, 0, 'DAC', 0)";
				PreparedStatement preparedStmt = conn.prepareStatement(query1);
				preparedStmt.setInt(1, electionId);
				preparedStmt.executeUpdate();
			} catch (SQLException e) {
				Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
			}
		});
	}

	private void addChairVotes(String consentId, Integer vote, String type, Integer electionId) {
		List<Integer> memberIds = new ArrayList<>();
		memberIds = getMembersId(2);
		memberIds.forEach(memberId -> {
			try {
				String query1 = "INSERT INTO `" + DATABASE
						+ "`.`vote` (`vote`, `dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES ("
						+ vote + ", " + memberId + ", ?, 0, '" + type + "', 0)";
				PreparedStatement preparedStmt = conn.prepareStatement(query1);
				preparedStmt.setInt(1, electionId);
				preparedStmt.executeUpdate();
			} catch (SQLException e) {
				Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
			}
		});
	}

	private Map<Integer, String> createPropertiesMap(String objectId) {

		Map<Integer, String> properties = new TreeMap<Integer, String>();
		properties.put(2, "DNA, whole genome");
		properties.put(3, "human");
		properties.put(4, "muc-1, kidney disease");
		properties.put(5, "34");
		properties.put(6, "muc-1 patients that developed cancer , 5 weeks after treatment");
		properties.put(7, "http://....");
		properties.put(8, "Jane Doe");
		properties.put(9, "Jane Smith");
		properties.put(10, "" + objectId + "");
		return properties;
	}

	private void addPropertiesToDataset(Integer datasetId, String objectId) {
		try {
			Map<Integer, String> properties = createPropertiesMap(objectId);
			for (Map.Entry<Integer, String> entry : properties.entrySet()) {
				String property = "INSERT INTO `consent`.`datasetproperty` (`dataSetId`, `propertyKey`, `propertyValue`, `createDate`) VALUES ("
						+ datasetId + ", " + entry.getKey() + ", '" + entry.getValue() + "', '2018-09-05 12:22:05')";
				PreparedStatement preparedStmt = conn.prepareStatement(property);
				preparedStmt.executeUpdate();
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void createAssociation(String objectId, String consentId) {
		try {
			String association = "INSERT INTO " + DATABASE
					+ ".`consentassociations` (`consentId`, `associationType`, `objectId`) VALUES ('" + consentId
					+ "', 'sampleSet', '" + objectId + "')";
			System.out.println(association);
			PreparedStatement preparedStmt = conn.prepareStatement(association);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void createDataset(String objectId, Integer approval) {
		try {
			String dataset = "INSERT INTO " + DATABASE
					+ ".`dataset` (`name`, `createDate`, `objectId`, `active`, `needs_approval`) VALUES ('testName', '2018-09-04 16:39:28', '"
					+ objectId + "', 1, "+approval+")";
			System.out.println(dataset);
			PreparedStatement preparedStmt = conn.prepareStatement(dataset);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private int getDatasetByObjectId(String objectId) {
		Integer id = 0;
		try {
			String sql = "SELECT dataSetId FROM " + DATABASE + ".dataset WHERE objectId = '" + objectId + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}

	public void addDulToDelete() {
		open();
		try {
			String sql = "SELECT consentId FROM " + DATABASE + ".consents WHERE consentId = 'idMock'";
			ResultSet rs = stmt.executeQuery(sql);
			boolean empty = true;
			while (rs.next()) {
				empty = false;
			}
			if (empty) {
				String query = "INSERT INTO `" + DATABASE
						+ "`.`consents` (`consentId`, `requiresManualReview`, `useRestriction`, `active`, `dataUseLetter`, `name`, `dulName`, `createDate`, `lastUpdate`, `sortDate`, `translatedUseRestriction`, `valid_restriction`, `dataUse`, `groupName`, `updated`) VALUES ('idMock', 0, '{\"type\":\"everything\"}', 1, 'urlMock', 'ORSP-Delete', 'dulNameConsent.pdf', '2018-07-13 12:05:02', NULL, '2018-07-13 12:05:02', 'Translated Mock', 1, '{\"generalUse\":true}', 'GroupName / Mock', 0);";
				PreparedStatement preparedSmt = conn.prepareStatement(query);
				preparedSmt.executeUpdate();
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

	public void deletedULIfNotDeleted() {
		open();
		try {
			String sql = "SELECT consentId FROM `" + DATABASE + "`.`consents` WHERE `consentId`='idMock'";
			ResultSet rs = stmt.executeQuery(sql);
			boolean empty = true;
			while (rs.next()) {
				empty = false;
			}
			if (!empty) {
				String delete = "DELETE FROM `" + DATABASE + "`.`consents` WHERE `consentId`='idMock'";
				PreparedStatement preparedStmt = conn.prepareStatement(delete);
				preparedStmt.executeUpdate();
			}

		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

	public void addElection(String referenceId, String status, String type, Integer vote) {
		open();
		try {
			createElection(referenceId, status, type);
			List<Integer> electionIds = new ArrayList<>();
			electionIds = getElectionIds(referenceId);
			if (type == "RP") {
				addMembersVotes(referenceId, vote, electionIds.get(1));
				addChairVotes(referenceId, vote, "DAC", electionIds.get(1));
				addChairVotes(referenceId, vote, "CHAIRPERSON", electionIds.get(1));
			} else {
				addMembersVotes(referenceId, vote, electionIds.get(0));
				addChairVotes(referenceId, vote, "DAC", electionIds.get(0));
				addChairVotes(referenceId, vote, "CHAIRPERSON", electionIds.get(0));
			}
		} catch (Exception e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

	public void deleteElection(String referenceId) {
		open();
		List<Integer> electionIds = new ArrayList<>();
		electionIds = getElectionIds(referenceId);
		if (!electionIds.isEmpty()) {
		electionIds.forEach(electionId -> {
			try {
				String deleteVotes = "DELETE FROM `" + DATABASE + "`.`vote` WHERE `electionId`='" + electionId + "'";
				String deleteElection = "DELETE FROM `" + DATABASE + "`.`election` WHERE `electionId`='" + electionId
						+ "'";
				PreparedStatement preparedStmt1 = conn.prepareStatement(deleteVotes);
				PreparedStatement preparedStmt2 = conn.prepareStatement(deleteElection);
				preparedStmt1.executeUpdate();
				preparedStmt2.executeUpdate();
			} catch (SQLException e) {
				Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
			}
		});
		}
		close();
	}

	public String checkRationale(String consentId) {
		open();
		List<Integer> electionIds = new ArrayList<>();
		String rationale = "";
		try {
			electionIds = getElectionIds(consentId);
			String getRationale = "SELECT rationale FROM " + DATABASE + ".vote WHERE electionId=" + electionIds.get(0)
					+ " AND updateDate<>''";
			ResultSet rs = stmt.executeQuery(getRationale);
			while (rs.next()) {
				rationale = rs.getString(1);
			}
			conn.close();
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
		return rationale;
	}

	public void changeRoles() {
		open();
		try {
			String sql = "SELECT roleId FROM " + DATABASE + ".user_role WHERE dacUserId = 5555";
			ResultSet rs = stmt.executeQuery(sql);
			Integer id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			if (id == 1) {
				String query1 = "UPDATE " + DATABASE
						+ ".`user_role` SET `roleId`=3 WHERE `roleId`=1 and `dacUserId`=5555";
				PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
				preparedStmt1.executeUpdate();
			} else {
				String query2 = "UPDATE " + DATABASE
						+ ".`user_role` SET `roleId`=1 WHERE `roleId`=3 and `dacUserId`=5555";
				PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
				preparedStmt2.executeUpdate();
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

	public void addDataset(String objectId, String consentId, Integer approval) {
		open();
		try {
			createAssociation(objectId, consentId);

			createDataset(objectId, approval);

			Integer id = getDatasetByObjectId(objectId);

			addPropertiesToDataset(id, objectId);

		} catch (Exception e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

	public void deleteDataset(String objectId) {
		open();
		try {
			Integer id = getDatasetByObjectId(objectId);
			String deleteProperties = "DELETE FROM `" + DATABASE + "`.`datasetproperty` WHERE `datasetId`='" + id + "'";
			String deleteDataset = "DELETE FROM `" + DATABASE + "`.`dataset` WHERE `objectId`='" + objectId + "'";
			String deleteAssociation = "DELETE FROM `" + DATABASE + "`.`consentassociations` WHERE `objectId`='" + objectId + "'";
			PreparedStatement preparedStmt1 = conn.prepareStatement(deleteProperties);
			PreparedStatement preparedStmt2 = conn.prepareStatement(deleteDataset);
			PreparedStatement preparedStmt3 = conn.prepareStatement(deleteAssociation);
			preparedStmt1.executeUpdate();
			preparedStmt2.executeUpdate();
			preparedStmt3.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

	public void deleteUser() {
		open();
		try {
			String sql = "SELECT dacUserId FROM " + DATABASE + ".dacuser WHERE email = '"
					+ FileReaderManager.getInstance().getConfigReader().getMockUserMail() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Integer id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			if (id != null) {
				String query = "DELETE FROM `" + DATABASE + "`.`user_role` WHERE `dacUserId`='" + id + "'";
				String query1 = "DELETE FROM `" + DATABASE + "`.`dacuser` WHERE `dacUserId`='" + id + "'";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
				preparedStmt.executeUpdate();
				preparedStmt1.executeUpdate();
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

}
