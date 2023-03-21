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

import model.UtenteBean;


public class UtenteDAO {
	public synchronized int registraUtente(UtenteBean utente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String INSERISCI_UTENTE = "INSERT INTO utente (email,nome,cognome,username,password,img)"
				+ "VALUES (?,?,?,?,?,?);";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(INSERISCI_UTENTE);
			
			String email = utente.getEmail();
			if(controllaEmail(email)) {
				return 1;
			}else {
				preparedStatement.setString(1, email);
			}
			
			preparedStatement.setString(2, utente.getNome());
			preparedStatement.setString(3, utente.getCognome());
			
			String username = utente.getUsername();
			if(controllaUsername(username)) {
				return 2;
			}else {
				preparedStatement.setString(4, username);
			}
			
			preparedStatement.setString(5, utente.getPassword());
			preparedStatement.setString(6, utente.getImg());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return 0;
	}
	
	public synchronized UtenteBean loginUtente(String e, String psw) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		UtenteBean utente = new UtenteBean();
		
		String TROVA_UTENTE = "SELECT * FROM utente WHERE email=? AND BINARY password=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(TROVA_UTENTE);
			
			preparedStatement.setString(1, e);
			preparedStatement.setString(2, psw);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			while (rs.next()) {
				utente.setEmail(rs.getString("email"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } finally {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
                return utente;
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return null;

	}
	
	public boolean controllaEmail(String email) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String TROVA_UTENTE = "SELECT * FROM utente WHERE email=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(TROVA_UTENTE);
			
			preparedStatement.setString(1, email);

			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			while (rs.next()) {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } finally {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
                return true;
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
		return false;
	}
public boolean controllaUsername(String username) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String TROVA_UTENTE = "SELECT * FROM utente WHERE username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(TROVA_UTENTE);
			
			preparedStatement.setString(1, username);

			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			while (rs.next()) {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } finally {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
                return true;
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
		return false;
	}

	public synchronized static byte[] load(String username) throws SQLException {

	Connection connection = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	byte[] bt = null;

	try {
		connection = DriverManagerConnectionPool.getConnection();
		String sql = "SELECT img FROM utente WHERE username = ?";
		stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, username);
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

	public synchronized static void updatePhoto(String username, String img) throws SQLException {
	Connection con = null;
	PreparedStatement stmt = null;

	try {
		con = DriverManagerConnectionPool.getConnection();

		stmt = con.prepareStatement("UPDATE utente SET img = ? WHERE username = ?");
		
		File file = new File(img);
		try {
			FileInputStream fis = new FileInputStream(file);
			stmt.setBinaryStream(1, fis, fis.available());
			stmt.setString(2, username);
			
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
	public synchronized boolean doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM utente" + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);

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
	
	public synchronized UtenteBean doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = new UtenteBean();

		String selectSQL = "SELECT * FROM utente" + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));
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
	
	public synchronized Collection<UtenteBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();

		String selectSQL = "SELECT * FROM utente; ";

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
				
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setUsername(rs.getString("username"));

				utenti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utenti;
	}

}
