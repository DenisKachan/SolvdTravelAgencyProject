package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Tour;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.interfaces.Create;
import com.solvd.travelAgencyProject.persistence.interfaces.Delete;
import com.solvd.travelAgencyProject.persistence.interfaces.Get;
import com.solvd.travelAgencyProject.persistence.interfaces.Update;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class TourRepository implements Create<Tour>, Delete, Update<Tour>, Get<Tour> {
    @Override
    public Connection create(Tour value) throws SQLException {
        Connection connection = ConnectionPool.getConnectionFromPool();
        connection.setAutoCommit(false);
        try (connection){
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT tour(name, cost, type_of_the_transport, type_of_the_tour) \n" +
                    "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, value.getName());
            preparedStatement.setDouble(2, value.getCost());
            preparedStatement.setInt(3, value.getMainTransport().getId());
            preparedStatement.setInt(4, value.getTourType().getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete tour where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
    }

    @Override
    public void updateById(Tour value, int id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnectionFromPool()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Update tour set name=?, cost=?, type_of_the_transport=?, type_of_the_tour=? \n" +
                    "where id = ?;");
            preparedStatement.setString(1, value.getName());
            preparedStatement.setDouble(2, value.getCost());
            preparedStatement.setInt(3, value.getMainTransport().getId());
            preparedStatement.setInt(4, value.getTourType().getId());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
    }

    @Override
    public Tour getById(int id) {
        Tour tour = new Tour();
        try (Connection connection = ConnectionPool.getConnectionFromPool()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from tour where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            tour.setId(id);
            tour.setName(resultSet.getString("name"));
            tour.setCost(resultSet.getDouble("cost"));
            tour.setMainTransportId(resultSet.getInt("type_of_the_transport"));
            tour.setTourTypeId(resultSet.getInt("type_of_the_tour"));
        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
        }
        return tour;
    }
}
