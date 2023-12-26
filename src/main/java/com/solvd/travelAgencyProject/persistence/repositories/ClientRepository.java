package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Client;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import com.solvd.travelAgencyProject.persistence.interfaces.Delete;
import com.solvd.travelAgencyProject.persistence.interfaces.Get;
import com.solvd.travelAgencyProject.persistence.interfaces.Update;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ClientRepository implements Create<Client>, Delete, Update<Client>, Get<Client> {
    @Override
    public Connection create(Client value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        connection.setAutoCommit(false);
        try (connection) {
          PreparedStatement  preparedStatement = connection.prepareStatement("INSERT client(name, surname, phone_number, discount_id) \n" +
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

    @Override
    public void deleteById(int id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnectionFromPool()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete client where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }

    }

    @Override
    public void updateById(Client value, int id) throws SQLException {
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

    @Override
    public Client getById(int id) {
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
