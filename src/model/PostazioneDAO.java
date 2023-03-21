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

public class PostazioneDAO {

	private static final String TABLE_NAME = "postazione";

	public synchronized void doSave(PostazioneBean postazione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PostazioneDAO.TABLE_NAME
				+ " (tipo, nome_postazione, costo, img, descrizione, sala_codice) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, postazione.getTipo());
			preparedStatement.setString(2, postazione.getNome_postazione());
			preparedStatement.setInt(3, postazione.getCosto());
			preparedStatement.setString(4,postazione.getImg());
			preparedStatement.setString(5, postazione.getDescrizione());
			preparedStatement.setInt(6, postazione.getCodice_sala());
			

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


	public synchronized PostazioneBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PostazioneBean bean = new PostazioneBean();

		String selectSQL = "SELECT * FROM " + PostazioneDAO.TABLE_NAME + " WHERE n_posto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setN_posto(rs.getInt("n_posto"));
				bean.setTipo(rs.getString("tipo"));
				bean.setNome_postazione(rs.getString("nome_postazione"));
				bean.setCosto(rs.getInt("costo"));
				bean.setImg(rs.getString("img"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCodice_sala(rs.getInt("sala_codice"));
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

		String deleteSQL = "DELETE FROM " + PostazioneDAO.TABLE_NAME + " WHERE n_posto = ?";

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


	public synchronized Collection<PostazioneBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PostazioneBean> postazioni = new LinkedList<PostazioneBean>();

		String selectSQL = "SELECT * FROM " + PostazioneDAO.TABLE_NAME;

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PostazioneBean bean = new PostazioneBean();
				
				bean.setN_posto(rs.getInt("n_posto"));
				bean.setTipo(rs.getString("tipo"));
				bean.setNome_postazione(rs.getString("nome_postazione"));
				bean.setCosto(rs.getInt("costo"));
				bean.setImg(rs.getString("img"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCodice_sala(rs.getInt("sala_codice"));

				postazioni.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return postazioni;
	}
	
	public synchronized static byte[] load(String n_posto) throws SQLException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		byte[] bt = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			String sql = "SELECT img FROM postazione WHERE n_posto = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, n_posto);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("img");
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bt;
	}

		public synchronized static void updatePhoto(String n_posto, String img) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DriverManagerConnectionPool.getConnection();

			stmt = con.prepareStatement("UPDATE postazione SET img = ? WHERE n_posto = ?");
			
			File file = new File(img);
			try {
				FileInputStream fis = new FileInputStream(file);
				stmt.setBinaryStream(1, fis, fis.available());
				stmt.setString(2, n_posto);
				
				stmt.executeUpdate();
				con.commit();
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (con != null)
					DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
		
		
		public synchronized Collection<PostazioneBean> doRetrieveByType(String type) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<PostazioneBean> postazioni = new LinkedList<PostazioneBean>();

			String selectSQL = "SELECT * FROM " + PostazioneDAO.TABLE_NAME + " WHERE tipo = ?";

			
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, type);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					PostazioneBean bean = new PostazioneBean();
					
					bean.setN_posto(rs.getInt("n_posto"));
					bean.setTipo(rs.getString("tipo"));
					bean.setNome_postazione(rs.getString("nome_postazione"));
					bean.setCosto(rs.getInt("costo"));
					bean.setImg(rs.getString("img"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setCodice_sala(rs.getInt("sala_codice"));

					postazioni.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return postazioni;
		}
		
		
	

}