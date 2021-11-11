package com.nttdata.SACG_jdbc_taller1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author scarnero
 *
 */
public class NTTDataJDBCMethods {
	
	private static Connection dbConnection = null;
	
	public static void OracleConnection() {
		try {

			getConnection();

			printQuery(createQuery());
			
			dbConnection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error en la conexión con BBDD");
		}
	}
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "system", "root");
	}
	
	private static  ResultSet createQuery() throws SQLException {
		final Statement sentence = dbConnection.createStatement();
		
		final String query = "SELECT sp.name AS playerName, st.name AS teamName, sp.first_rol AS rol1, sp.second_rol AS rol2, sp.birth_date AS birthD FROM nttdata_oracle_soccer_player sp JOIN nttdata_oracle_soccer_team st ON sp.id_soccer_team = st.id_soccer_team";
		
		final ResultSet queryResult = sentence.executeQuery(query);
		
		return queryResult;
	}
	
	private static void printQuery(ResultSet rs) throws SQLException {
		StringBuilder playerInfo = new StringBuilder();
		
		while (rs.next()) {

			playerInfo.append("Nombre: ");
			playerInfo.append(rs.getString("playerName"));

			playerInfo.append(" | Equipo: ");
			playerInfo.append(rs.getString("teamName"));

			playerInfo.append(" | Demarcación");
			playerInfo.append(rs.getString("rol1"));

			playerInfo.append(" | Demarcación alternativa");
			playerInfo.append(rs.getString("rol2"));

			playerInfo.append(" | Fecha nacimiento: ");
			playerInfo.append(rs.getString("birthD"));

			playerInfo.append("\n");
		}
		
		System.out.println(playerInfo.toString());
	}
}
