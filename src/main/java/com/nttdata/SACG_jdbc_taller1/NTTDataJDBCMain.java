package com.nttdata.SACG_jdbc_taller1;

/**
 * 
 * @author scarnero
 *
 */
public class NTTDataJDBCMain {
	public static void main(String[] args) {
		stablishODBConnection();
	}

	private static void stablishODBConnection() {
		NTTDataJDBCMethods.OracleConnection();
	}
}
