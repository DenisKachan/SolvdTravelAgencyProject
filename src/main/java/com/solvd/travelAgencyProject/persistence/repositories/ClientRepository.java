package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Client;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ClientRepository implements Create<Client> {
    @Override
    public void create(Client value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT client(name, surname, phone_number, discount_id) \n" +
                    "VALUES ('?, '?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,value.getName());
            preparedStatement.setString(2,value.getSurname());
            preparedStatement.setInt(3,value.getPhoneNumber());
            preparedStatement.setInt(4,value.getDiscount().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                value.setId(resultSet.getInt(1));
            }
        }catch (SQLException sqlException){
            log.error(sqlException.getMessage());
        }
    }
}
