package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Client;
import com.solvd.travelAgencyProject.persistence.interfaces.ClientRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisImplementation;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class ClientJDBCImpl implements ClientRepository {
    @Override
    public Connection create(Client value) throws SQLException {
        if (MybatisImplementation.flag) {
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            Connection clientConnection = session.getConnection();
            clientConnection.setAutoCommit(false);
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            clientRepository.create(value);
            return clientConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try (connection) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into client(name, surname, phone_number, discount_id) \n" +
                        "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, value.getName());
                preparedStatement.setString(2, value.getSurname());
                preparedStatement.setInt(3, value.getPhoneNumber());
                preparedStatement.setInt(4, value.getDiscount().getId());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    value.setId(resultSet.getInt(1));
                }
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
            return connection;
        }
    }

    @Override
    public void deleteById(int id) {
        if (MybatisImplementation.flag) {
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            clientRepository.deleteById(id);
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete client where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }

    }

    @Override
    public void updateById(Client value, int id) {
        if (MybatisImplementation.flag) {
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            clientRepository.updateById(value, id);
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update client  set name=?, surname=?, phone_number=?, discount_id=? \n" +
                        "where id = ?;");
                preparedStatement.setString(1, value.getName());
                preparedStatement.setString(2, value.getSurname());
                preparedStatement.setInt(3, value.getPhoneNumber());
                preparedStatement.setInt(4, value.getDiscount().getId());
                preparedStatement.setInt(5, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public Client getById(int id) {
        if (MybatisImplementation.flag) {
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            return clientRepository.getById(id);
        } else {
            Client client = new Client();
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from client where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                client.setId(id);
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setPhoneNumber(resultSet.getInt("phone_number"));
                client.setDiscountId(resultSet.getInt("discount_id"));
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
            return client;
        }
    }
}
