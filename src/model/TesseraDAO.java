package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class TesseraDAO {

	private static final String TABLE_NAME = "tessera";

	public synchronized void doSave(TesseraBean tessera) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + TesseraDAO.TABLE_NAME
				+ " (saldo, tipo, email_utente) VALUES (?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, tessera.getSaldo());
			preparedStatement.setString(2, tessera.getTipo());
			preparedStatement.setString(3, tessera.getEmail_utente());
			

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}


	public synchronized TesseraBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		TesseraBean bean = new TesseraBean();

		String selectSQL = "SELECT * FROM " + TesseraDAO.TABLE_NAME + " WHERE email_utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("codice"));
				bean.setSaldo(rs.getInt("saldo"));
				bean.setTipo(rs.getString("tipo"));
				bean.setEmail_utente(rs.getString("email_utente"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TesseraDAO.TABLE_NAME + " WHERE n_posto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}


	public synchronized Collection<TesseraBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<TesseraBean> tessere = new LinkedList<TesseraBean>();

		String selectSQL = "SELECT * FROM " + TesseraDAO.TABLE_NAME;

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TesseraBean bean = new TesseraBean();
				
				bean.setCodice(rs.getInt("codice"));
				bean.setSaldo(rs.getInt("saldo"));
				bean.setTipo(rs.getString("tipo"));
				bean.setEmail_utente(rs.getString("email_utente"));

				tessere.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return tessere;
	}
	
	public synchronized int restituisciSaldo(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int saldo = 0;

		String selectSQL = "SELECT saldo FROM " + TesseraDAO.TABLE_NAME + " WHERE email_utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				saldo = rs.getInt("saldo");

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return saldo;
	}
	
	public synchronized boolean paga(String email, int costo) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int totaleSaldo = 0;
		String insertSQL = "UPDATE tessera set "
				+ " saldo = ? where email_utente = '"+email+"'";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			totaleSaldo = restituisciSaldo(email) - costo; 
			System.out.println(restituisciSaldo(email)+ "-" + costo);
			preparedStatement.setInt(1, totaleSaldo);
			System.out.println("TESSERADAO " +totaleSaldo);

			if(totaleSaldo < 0) {
				return false;
			}
			else {
			
			preparedStatement.executeUpdate();
			}
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return true;
	}




}