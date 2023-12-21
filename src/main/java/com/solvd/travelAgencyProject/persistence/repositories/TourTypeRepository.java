package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.TourType;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
@Log4j2
public class TourTypeRepository implements Create<TourType> {

    @Override
    public void create(TourType value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT tour_type(name) \n" +
                    "VALUES ('sea');", Statement.RETURN_GENERATED_KEYS);
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
