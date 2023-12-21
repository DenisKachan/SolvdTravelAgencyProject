package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.TravelAgent;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class TravelAgentRepository implements Create<TravelAgent> {
    @Override
    public void create(TravelAgent value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT travel_agent(name, surname, tour_responsibility) \n" +
                    "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,value.getName());
            preparedStatement.setString(2,value.getSurname());
            preparedStatement.setInt(3,value.getTourType().getId());
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
