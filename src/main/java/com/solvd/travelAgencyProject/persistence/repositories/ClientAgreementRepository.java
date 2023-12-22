package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import com.solvd.travelAgencyProject.persistence.interfaces.Delete;
import com.solvd.travelAgencyProject.persistence.interfaces.Update;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ClientAgreementRepository implements Create<ClientAgreement>, Delete, Update<ClientAgreement> {

    @Override
    public void create(ClientAgreement value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT client_agreement(date,conditions, client_id, client_discount_id, travel_agent_id, tour_identificator, tour_transport, tour_type) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, (Date) value.getDate());
            preparedStatement.setString(2,value.getConditions());
            preparedStatement.setInt(3,value.getClient().getId());
            preparedStatement.setInt(4,value.getDiscount().getId());
            preparedStatement.setInt(5,value.getTravelAgent().getId());
            preparedStatement.setInt(6,value.getTour().getId());
            preparedStatement.setInt(7,value.getMainTransport().getId());
            preparedStatement.setInt(8,value.getTourType().getId());
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
    public void updateById(ClientAgreement clientAgreement, int id) {

    }
}
