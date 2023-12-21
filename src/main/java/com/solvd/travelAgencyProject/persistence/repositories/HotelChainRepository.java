package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.HotelChain;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class HotelChainRepository implements Create<HotelChain> {
    @Override
    public void create(HotelChain value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT hotel_chain(name) \n" +
                    "VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,value.getName());
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
