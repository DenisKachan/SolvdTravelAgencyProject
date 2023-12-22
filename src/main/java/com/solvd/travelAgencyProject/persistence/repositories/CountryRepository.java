package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import com.solvd.travelAgencyProject.persistence.interfaces.Delete;
import com.solvd.travelAgencyProject.persistence.interfaces.Update;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class CountryRepository implements Create<Country>, Delete, Update<Country> {
    @Override
    public void create(Country value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT country(name) \n" +
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

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void updateById(Country country,int id) {

    }
}