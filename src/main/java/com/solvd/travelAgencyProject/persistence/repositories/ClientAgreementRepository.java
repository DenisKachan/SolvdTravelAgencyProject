package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import com.solvd.travelAgencyProject.persistence.interfaces.Delete;
import com.solvd.travelAgencyProject.persistence.interfaces.Get;
import com.solvd.travelAgencyProject.persistence.interfaces.Update;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ClientAgreementRepository implements Create<ClientAgreement>, Delete, Update<ClientAgreement>, Get<ClientAgreement> {

    @Override
    public Connection create(ClientAgreement value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        connection.setAutoCommit(false);
        try (connection) {
           PreparedStatement preparedStatement = connection.prepareStatement("INSERT client_agreement(date,conditions, client_id, client_discount_id, travel_agent_id, tour_identificator, tour_transport, tour_type) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, (Date) value.getDate());
            preparedStatement.setString(2, value.getConditions());
            preparedStatement.setInt(3, value.getClient().getId());
            preparedStatement.setInt(4, value.getDiscount().getId());
            preparedStatement.setInt(5, value.getTravelAgent().getId());
            preparedStatement.setInt(6, value.getTour().getId());
            preparedStatement.setInt(7, value.getMainTransport().getId());
            preparedStatement.setInt(8, value.getTourType().getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete client_agreement where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
    }

    @Override
    public void updateById(ClientAgreement value, int id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnectionFromPool()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Update client_agreement  set date=?,conditions=?, client_id=?, client_discount_id=?, travel_agent_id=?, tour_identificator=?, tour_transport=?, tour_type=? \n" +
                    "where id = ?;");
            preparedStatement.setDate(1, (Date) value.getDate());
            preparedStatement.setString(2, value.getConditions());
            preparedStatement.setInt(3, value.getClient().getId());
            preparedStatement.setInt(4, value.getDiscount().getId());
            preparedStatement.setInt(5, value.getTravelAgent().getId());
            preparedStatement.setInt(6, value.getTour().getId());
            preparedStatement.setInt(7, value.getMainTransport().getId());
            preparedStatement.setInt(8, value.getTourType().getId());
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
    }

    @Override
    public ClientAgreement getById(int id) throws SQLException {
        ClientAgreement clientAgreement = new ClientAgreement();
        try (Connection connection = ConnectionPool.getConnectionFromPool()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from client_agreement where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            clientAgreement.setId(id);
            clientAgreement.setDate(resultSet.getDate("date"));
            clientAgreement.setConditions(resultSet.getString("conditions"));
            clientAgreement.setClientId(resultSet.getInt("client_id"));
            clientAgreement.setDiscountId(resultSet.getInt("client_discount_id"));
            clientAgreement.setTravelAgentId(resultSet.getInt("travel_agent_id"));
            clientAgreement.setTourId(resultSet.getInt("tour_identificator"));
            clientAgreement.setMainTransportId(resultSet.getInt("tour_transport"));
            clientAgreement.setTourTypeId(resultSet.getInt("tour_type"));
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
        return clientAgreement;
    }
}
