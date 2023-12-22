package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Discount;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import com.solvd.travelAgencyProject.persistence.interfaces.Delete;
import com.solvd.travelAgencyProject.persistence.interfaces.Update;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
@Log4j2
public class DiscountRepository implements Create<Discount>, Delete, Update<Discount> {


    @Override
    public void create(Discount value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        try {PreparedStatement preparedStatement = connection.prepareStatement("INSERT discount(amount_of_tours, amount_of_discount) \n" +
                "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,value.getAmountOfTours());
            preparedStatement.setDouble(2,value.getAmountOfDiscount());
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
    public void updateById(Discount discount,int id) {
    }
}
