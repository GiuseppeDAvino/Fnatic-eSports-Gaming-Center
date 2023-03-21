package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class PrenotazioneDAO {

	private static final String TABLE_NAME = "prenotazione";
	private TesseraDAO tesseraDAO = new TesseraDAO();


	public synchronized boolean doSave(PrenotazioneBean prenotazione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PrenotazioneDAO.TABLE_NAME
				+ " (data_prenotazione, utente_email, n_posto) VALUES (?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prenotazione.getData_prenotazione());
			preparedStatement.setString(2, prenotazione.getEmail_utente());
			preparedStatement.setInt(3, prenotazione.getNumero_postazione());
			
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
		return true;
	}


	public synchronized Collection<PrenotazioneBean> doRetrieveByKey(String utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();

		String selectSQL = "SELECT * FROM " + PrenotazioneDAO.TABLE_NAME + " WHERE utente_email = ?";

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PrenotazioneBean bean = new PrenotazioneBean();
				
				bean.setId_prenotazione(rs.getInt("id_prenotazione"));
				bean.setData_prenotazione(rs.getString("data_prenotazione"));
				bean.setEmail_utente(rs.getString("utente_email"));
				bean.setNumero_postazione(rs.getInt("n_posto"));

				prenotazioni.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prenotazioni;
	}

	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PrenotazioneDAO.TABLE_NAME + " WHERE id_prenotazione = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();
			connection.commit();

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


	public synchronized Collection<PrenotazioneBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();

		String selectSQL = "SELECT * FROM " + PrenotazioneDAO.TABLE_NAME;

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PrenotazioneBean bean = new PrenotazioneBean();
				
				bean.setId_prenotazione(rs.getInt("id_prenotazione"));
				bean.setData_prenotazione(rs.getString("data_prenotazione"));
				bean.setEmail_utente(rs.getString("utente_email"));
				bean.setNumero_postazione(rs.getInt("n_posto"));

				prenotazioni.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prenotazioni;
	}
	public synchronized Collection<PrenotazioneBean> doRetrieveByDate(String data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();

		String selectSQL = "select * from prenotazione where data_prenotazione >= '2020-7-2'";

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PrenotazioneBean bean = new PrenotazioneBean();
				
				bean.setId_prenotazione(rs.getInt("id_prenotazione"));
				bean.setData_prenotazione(rs.getString("data_prenotazione"));
				bean.setEmail_utente(rs.getString("utente_email"));
				bean.setNumero_postazione(rs.getInt("n_posto"));

				prenotazioni.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prenotazioni;
	}
	

}