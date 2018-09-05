package stepDefinitions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import managers.FileReaderManager;

public class MySQLDBHelper {
	// JDBC driver name and database URL
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private final String DB_URL = FileReaderManager.getInstance().getConfigReader().getDbUrl();
    
    //  Database credentials
    private static final String USER = FileReaderManager.getInstance().getConfigReader().getDbUser();
    private static final String PASS = FileReaderManager.getInstance().getConfigReader().getDbPass();
    private static final String DATABASE = "consent";
    
    private Connection conn = null;
    private Statement stmt = null;
    
    public MySQLDBHelper() {
        //Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch(Exception e) {
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
	
	private Integer getDulElectionId() {
		Integer electionId = 0;
		try {
		String sql = "SELECT electionId FROM " +DATABASE+".election WHERE referenceId = \"ORSP-628\"";
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			electionId = rs.getInt(1);
		}
		}catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
		return electionId;
	}
	
	private List<Integer> getDarElectionId(String objectId) {
		List<Integer> electionIds = new ArrayList<>();
		try {
		String sql = "SELECT electionId FROM " +DATABASE+".election WHERE referenceId = \""+objectId+"\" AND status=\"Open\"";
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			electionIds.add(rs.getInt(1));
		}
		}catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		}
		return electionIds;
	}
	
	public void addDulToDelete() {
		open();
		try {
			String sql = "SELECT consentId FROM " +DATABASE+".consents WHERE consentId = 'idMock'";
			ResultSet rs = stmt.executeQuery(sql);
			boolean empty = true;
			while( rs.next() ) {
			    empty = false;
			}
			if (empty) {
			String query = "INSERT INTO `"+DATABASE+"`.`consents` (`consentId`, `requiresManualReview`, `useRestriction`, `active`, `dataUseLetter`, `name`, `dulName`, `createDate`, `lastUpdate`, `sortDate`, `translatedUseRestriction`, `valid_restriction`, `dataUse`, `groupName`, `updated`) VALUES ('idMock', 0, '{\"type\":\"everything\"}', 1, 'urlMock', 'ORSP-Delete', 'dulNameConsent.pdf', '2018-07-13 12:05:02', NULL, '2018-07-13 12:05:02', 'Translated Mock', 1, '{\"generalUse\":true}', 'GroupName / Mock', 0);";
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
			String sql = "SELECT consentId FROM `"+DATABASE+"`.`consents` WHERE `consentId`='idMock'";
			ResultSet rs = stmt.executeQuery(sql);
			boolean empty = true;
			while( rs.next() ) {
			    empty = false;
			}
			if (!empty){
			String delete = "DELETE FROM `"+DATABASE+"`.`consents` WHERE `consentId`='idMock'";
			PreparedStatement preparedStmt = conn.prepareStatement(delete);
			preparedStmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}


	public void addDulElectionOpened() {
		open();
		Integer electionId = 0;
		try {
			// Add Election
			String query1 = "INSERT INTO `consent`.`election` (`status`, `createDate`, `referenceId`, `useRestriction`, `translatedUseRestriction`, `electionType`, `dataUseLetter`, `dulName`, `archived`, `version`) VALUES ('Open', '2018-08-28 11:49:48', 'ORSP-628', '{\\\"type\\\":\\\"or\\\",\\\"operands\\\":[{\\\"type\\\":\\\"named\\\",\\\"name\\\":\\\"http://purl.obolibrary.org/obo/DUO_0000015\\\"},{\\\"type\\\":\\\"everything\\\"}]}', 'Samples are restricted for use under the following conditions:<br>Data is limited for health/medical/biomedical research. [HMB]<br>Future use for population origins or ancestry research is prohibited. [POA]<br>Commercial use prohibited. [NCU]<br>Data use for methods development research irrespective of the specified data use limitations is not prohibited.<br>Future use as a control set for any type of health/medical/biomedical study is not prohibited.<br>Other restrictions: OTHER TERMS.', 'TranslateDUL', 'https://storage.googleapis.com/broad-dsde-dev-consent/57146b0d-34b4-4788-aaac-b01549084730.pdf', 'dulNameConsent.pdf', 0, 1)";
			PreparedStatement preparedStmt = conn.prepareStatement(query1);
			preparedStmt.executeUpdate();
			// Get Election ID
			
			electionId = getDulElectionId();

			String queryMember1 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (5555, ?, 0, 'DAC', 0)";
			String queryMember2 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (6666, ?, 0, 'DAC', 0)";
			String queryMember3 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (7777, ?, 0, 'DAC', 0)";
			String queryMember4 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'DAC', 0)";
			String queryMemberChair = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'CHAIRPERSON', 0)";

			PreparedStatement preparedStmt1 = conn.prepareStatement(queryMember1);
			PreparedStatement preparedStmt2 = conn.prepareStatement(queryMember2);
			PreparedStatement preparedStmt3 = conn.prepareStatement(queryMember3);
			PreparedStatement preparedStmt4 = conn.prepareStatement(queryMember4);
			PreparedStatement preparedStmtChair = conn.prepareStatement(queryMemberChair);
			preparedStmt1.setInt(1, electionId);
			preparedStmt2.setInt(1, electionId);
			preparedStmt3.setInt(1, electionId);
			preparedStmt4.setInt(1, electionId);
			preparedStmtChair.setInt(1, electionId);

			preparedStmt1.executeUpdate();
			preparedStmt2.executeUpdate();
			preparedStmt3.executeUpdate();
			preparedStmt4.executeUpdate();
			preparedStmtChair.executeUpdate();
			System.out.println("CREANDO ELECCION DUL--------------------------------");
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}
	
	public void addDulElectionClosed() {
		open();
		Integer electionId = 0;
		try {
			// Add Election
			String query1 = "INSERT INTO `"+DATABASE+"`.`election` (`status`, `createDate`, `referenceId`, `useRestriction`, `translatedUseRestriction`, `electionType`, `dataUseLetter`, `dulName`, `archived`, `version`) VALUES ('Closed', '2018-08-28 11:49:48', 'ORSP-628', '{\\\"type\\\":\\\"or\\\",\\\"operands\\\":[{\\\"type\\\":\\\"named\\\",\\\"name\\\":\\\"http://purl.obolibrary.org/obo/DUO_0000015\\\"},{\\\"type\\\":\\\"everything\\\"}]}', 'Samples are restricted for use under the following conditions:<br>Data is limited for health/medical/biomedical research. [HMB]<br>Future use for population origins or ancestry research is prohibited. [POA]<br>Commercial use prohibited. [NCU]<br>Data use for methods development research irrespective of the specified data use limitations is not prohibited.<br>Future use as a control set for any type of health/medical/biomedical study is not prohibited.<br>Other restrictions: OTHER TERMS.', 'TranslateDUL', 'https://storage.googleapis.com/broad-dsde-dev-consent/57146b0d-34b4-4788-aaac-b01549084730.pdf', 'dulNameConsent.pdf', 0, 1)";
			PreparedStatement preparedStmt = conn.prepareStatement(query1);
			preparedStmt.executeUpdate();
			// Get Election ID
			electionId = getDulElectionId();
			//Add Votes
			String queryMember1 = "INSERT INTO `"+DATABASE+"`.`vote` (`vote`,  `dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (1, 5555, ?, 0, 'DAC', 0)";
			String queryMember2 = "INSERT INTO `"+DATABASE+"`.`vote` (`vote`, `dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (1, 6666, ?, 0, 'DAC', 0)";
			String queryMember3 = "INSERT INTO `"+DATABASE+"`.`vote` (`vote`, `dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (1, 7777, ?, 0, 'DAC', 0)";
			String queryMember4 = "INSERT INTO `"+DATABASE+"`.`vote` (`vote`, `dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (1, 8888, ?, 0, 'DAC', 0)";
			String queryMemberChair = "INSERT INTO `"+DATABASE+"`.`vote` (`vote`, `dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (1, 8888, ?, 0, 'CHAIRPERSON', 0)";

			PreparedStatement preparedStmt1 = conn.prepareStatement(queryMember1);
			PreparedStatement preparedStmt2 = conn.prepareStatement(queryMember2);
			PreparedStatement preparedStmt3 = conn.prepareStatement(queryMember3);
			PreparedStatement preparedStmt4 = conn.prepareStatement(queryMember4);
			PreparedStatement preparedStmtChair = conn.prepareStatement(queryMemberChair);
			
			preparedStmt1.setInt(1, electionId);
			preparedStmt2.setInt(1, electionId);
			preparedStmt3.setInt(1, electionId);
			preparedStmt4.setInt(1, electionId);
			preparedStmtChair.setInt(1, electionId);

			preparedStmt1.executeUpdate();
			preparedStmt2.executeUpdate();
			preparedStmt3.executeUpdate();
			preparedStmt4.executeUpdate();
			preparedStmtChair.executeUpdate();
			
			System.out.println("CREANDO ELECCION CERRADA APPROVED--------------------------------");
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}
	
	public void addDarElection(String objectId) {
		open();
		try {
			// ADD DAR AND RP ELECTIONS
			String query1 = "INSERT INTO `"+DATABASE+"`.`election` (`status`, `createDate`, `referenceId`, `useRestriction`, `translatedUseRestriction`, `electionType`, `archived`, `version`) VALUES ('Open', '2018-08-28 11:49:48', ? , '{\\\"type\\\":\\\"or\\\",\\\"operands\\\":[{\\\"type\\\":\\\"named\\\",\\\"name\\\":\\\"http://purl.obolibrary.org/obo/DUO_0000015\\\"},{\\\"type\\\":\\\"everything\\\"}]}', 'Samples are restricted for use under the following conditions:<br>Data is limited for health/medical/biomedical research. [HMB]<br>Future use for population origins or ancestry research is prohibited. [POA]<br>Commercial use prohibited. [NCU]<br>Data use for methods development research irrespective of the specified data use limitations is not prohibited.<br>Future use as a control set for any type of health/medical/biomedical study is not prohibited.<br>Other restrictions: OTHER TERMS.', 'DataAccess', 0, 1)";
			String query2 = "INSERT INTO `"+DATABASE+"`.`election` (`status`, `createDate`, `referenceId`, `useRestriction`, `translatedUseRestriction`, `electionType`, `archived`, `version`) VALUES ('Open', '2018-08-28 11:49:48', ? , '{\\\"type\\\":\\\"or\\\",\\\"operands\\\":[{\\\"type\\\":\\\"named\\\",\\\"name\\\":\\\"http://purl.obolibrary.org/obo/DUO_0000015\\\"},{\\\"type\\\":\\\"everything\\\"}]}', 'Samples are restricted for use under the following conditions:<br>Data is limited for health/medical/biomedical research. [HMB]<br>Future use for population origins or ancestry research is prohibited. [POA]<br>Commercial use prohibited. [NCU]<br>Data use for methods development research irrespective of the specified data use limitations is not prohibited.<br>Future use as a control set for any type of health/medical/biomedical study is not prohibited.<br>Other restrictions: OTHER TERMS.', 'RP', 0, 2)";
			PreparedStatement preparedStmtDar = conn.prepareStatement(query1);
			PreparedStatement preparedStmtRP = conn.prepareStatement(query2);
			preparedStmtDar.setString(1, objectId);
			preparedStmtRP.setString(1, objectId);
			preparedStmtDar.executeUpdate();
			preparedStmtRP.executeUpdate();
			// Get Election ID			
			List<Integer> electionIds = getDarElectionId(objectId);
			Integer darElection = electionIds.get(0);
			Integer rpElection = electionIds.get(1);
			//ADD VOTES TO DATA ACCESS ELECTION
			String queryMember1 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (5555, ?, 0, 'DAC', 0)";
			String queryMember2 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (6666, ?, 0, 'DAC', 0)";
			String queryMember3 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (7777, ?, 0, 'DAC', 0)";
			String queryMember4 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'DAC', 0)";
			String queryMemberChair = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'CHAIRPERSON', 0)";
			String queryMemberFinal = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'FINAL', 0)";
			String queryMemberAgreement = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'AGREEMENT', 0)";

			PreparedStatement preparedStmt1 = conn.prepareStatement(queryMember1);
			PreparedStatement preparedStmt2 = conn.prepareStatement(queryMember2);
			PreparedStatement preparedStmt3 = conn.prepareStatement(queryMember3);
			PreparedStatement preparedStmt4 = conn.prepareStatement(queryMember4);
			PreparedStatement preparedStmtChair = conn.prepareStatement(queryMemberChair);
			PreparedStatement preparedStmtFinal = conn.prepareStatement(queryMemberFinal);
			PreparedStatement preparedStmtAgreement = conn.prepareStatement(queryMemberAgreement);
			
			preparedStmt1.setInt(1, darElection);
			preparedStmt2.setInt(1, darElection);
			preparedStmt3.setInt(1, darElection);
			preparedStmt4.setInt(1, darElection);
			preparedStmtChair.setInt(1, darElection);
			preparedStmtFinal.setInt(1, darElection);
			preparedStmtAgreement.setInt(1, darElection);
			
			preparedStmt1.executeUpdate();
			preparedStmt2.executeUpdate();
			preparedStmt3.executeUpdate();
			preparedStmt4.executeUpdate();
			preparedStmtChair.executeUpdate();
			preparedStmtFinal.executeUpdate();
			preparedStmtAgreement.executeUpdate();		
			//ADD VOTES TO RP ELECTION
			String queryMemberRP1 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (5555, ?, 0, 'DAC', 0)";
			String queryMemberRP2 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (6666, ?, 0, 'DAC', 0)";
			String queryMemberRP3 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (7777, ?, 0, 'DAC', 0)";
			String queryMemberRP4 = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'DAC', 0)";
			String queryMemberRPChair = "INSERT INTO `"+DATABASE+"`.`vote` (`dacUserId`, `electionId`, `reminderSent`, `type`, `has_concerns`) VALUES (8888, ?, 0, 'CHAIRPERSON', 0)";

			PreparedStatement preparedStmtRP1 = conn.prepareStatement(queryMemberRP1);
			PreparedStatement preparedStmtRP2 = conn.prepareStatement(queryMemberRP2);
			PreparedStatement preparedStmtRP3 = conn.prepareStatement(queryMemberRP3);
			PreparedStatement preparedStmtRP4 = conn.prepareStatement(queryMemberRP4);
			PreparedStatement preparedStmtRPChair = conn.prepareStatement(queryMemberRPChair);

			preparedStmtRP1.setInt(1, rpElection);
			preparedStmtRP2.setInt(1, rpElection);
			preparedStmtRP3.setInt(1, rpElection);
			preparedStmtRP4.setInt(1, rpElection);
			preparedStmtRPChair.setInt(1, rpElection);
			
			preparedStmtRP1.executeUpdate();
			preparedStmtRP2.executeUpdate();
			preparedStmtRP3.executeUpdate();
			preparedStmtRP4.executeUpdate();
			preparedStmtRPChair.executeUpdate();
					
			System.out.println("CREANDO ELECCION DAR--------------------------------");
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

	public void deleteDulElection() {
		open();
		Integer electionId = 0;
		try {
			electionId = getDulElectionId();
			String deleteVotes = "DELETE FROM `"+DATABASE+"`.`vote` WHERE `electionId`='" + electionId + "'";
			String deleteElection = "DELETE FROM `"+DATABASE+"`.`election` WHERE `electionId`='" + electionId + "'";
			PreparedStatement preparedStmt1 = conn.prepareStatement(deleteVotes);
			PreparedStatement preparedStmt2 = conn.prepareStatement(deleteElection);
			preparedStmt1.executeUpdate();
			preparedStmt2.executeUpdate();
			System.out.println("BORRANDO ELECCION DUL--------------------------------");
			conn.close();
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}
	
	public void deleteDarElection(String objectId) {
		open();
		List<Integer> electionIds = new ArrayList<>();
		try {
			electionIds = getDarElectionId(objectId);
			Integer darElectionId = electionIds.get(0);
			Integer rpElectionId = electionIds.get(1);
			String deleteDarVotes = "DELETE FROM `"+DATABASE+"`.`vote` WHERE `electionId`='" + darElectionId + "'";
			String deleteRpVotes = "DELETE FROM `"+DATABASE+"`.`vote` WHERE `electionId`='" + rpElectionId + "'";
			String deleteDarElection = "DELETE FROM `"+DATABASE+"`.`election` WHERE `electionId`='" + darElectionId + "'";
			String deleteRpElection = "DELETE FROM `"+DATABASE+"`.`election` WHERE `electionId`='" + rpElectionId + "'";
			PreparedStatement preparedStmt1 = conn.prepareStatement(deleteDarVotes);
			PreparedStatement preparedStmt2 = conn.prepareStatement(deleteRpVotes);
			PreparedStatement preparedStmt3 = conn.prepareStatement(deleteDarElection);
			PreparedStatement preparedStmt4 = conn.prepareStatement(deleteRpElection);
			preparedStmt1.executeUpdate();
			preparedStmt2.executeUpdate();
			preparedStmt3.executeUpdate();
			preparedStmt4.executeUpdate();
			System.out.println("BORRANDO ELECCION DAR--------------------------------");
			conn.close();
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}
	
	public String checkRationale() {
		open();
		Integer electionId = 0;
		String rationale = "";
		try {
			electionId = getDulElectionId();
			String getRationale = "SELECT rationale FROM "+DATABASE+".vote WHERE electionId=" + electionId + " AND updateDate<>''";
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
			String sql = "SELECT roleId FROM " +DATABASE+".user_role WHERE dacUserId = 5555";
			ResultSet rs = stmt.executeQuery(sql);
			Integer id = 0;
			while( rs.next() ) {
			    id = rs.getInt(1);
			}
			if (id == 1) {
				String query1="UPDATE " +DATABASE+".`user_role` SET `roleId`=3 WHERE `roleId`=1 and `dacUserId`=5555";
				System.out.println(query1);
				PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
				preparedStmt1.executeUpdate();
			} else {
				String query2="UPDATE " +DATABASE+".`user_role` SET `roleId`=1 WHERE `roleId`=3 and `dacUserId`=5555";
				System.out.println(query2);
				PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
				preparedStmt2.executeUpdate();
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLDBHelper.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			close();
		}
	}

}
