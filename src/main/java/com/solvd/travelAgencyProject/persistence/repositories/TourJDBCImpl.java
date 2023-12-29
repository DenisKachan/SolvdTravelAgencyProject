package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.domain.Tour;
import com.solvd.travelAgencyProject.persistence.interfaces.ClientAgreementRepository;
import com.solvd.travelAgencyProject.persistence.interfaces.TourRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisImplementation;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class TourJDBCImpl implements TourRepository {
    @Override
    public Connection create(Tour value) throws SQLException {
        if (MybatisImplementation.flag) {
            Connection tourConnection = null;
            try (SqlSession session = MybatisImplementation.getSessionFactory().openSession(true)) {
                tourConnection = session.getConnection();
                tourConnection.setAutoCommit(false);
                TourRepository tourRepository = session.getMapper(TourRepository.class);
                tourRepository.create(value);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return tourConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try (connection) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into tour(name, cost, type_of_the_transport, type_of_the_tour) \n" +
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
    }

    @Override
    public void deleteById(int id) {
        if (MybatisImplementation.flag) {
            try (SqlSession session = MybatisImplementation.getSessionFactory().openSession(true)) {
                TourRepository tourRepository = session.getMapper(TourRepository.class);
                tourRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete tour where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(Tour value, int id) {
        if (MybatisImplementation.flag) {
            try (SqlSession session = MybatisImplementation.getSessionFactory().openSession(true)) {
                TourRepository tourRepository = session.getMapper(TourRepository.class);
                tourRepository.updateById(value, id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
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
    }

    @Override
    public Tour getById(int id) {
        Tour tour = new Tour();
        if (MybatisImplementation.flag) {
            try (SqlSession session = MybatisImplementation.getSessionFactory().openSession(true)) {
                TourRepository tourRepository = session.getMapper(TourRepository.class);
                tour = tourRepository.getById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
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
        }
        return tour;
    }

    @Override
    public void showToursWithArrangedCost(double costOfTheTour) {
        if (MybatisImplementation.flag) {
            try (SqlSession session = MybatisImplementation.getSessionFactory().openSession(true)
            ) {
                TourRepository tourRepository = session.getMapper(TourRepository.class);
                tourRepository.showToursWithArrangedCost(costOfTheTour);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select t.id, t.name, t.cost, tt.id, tt.name from tour t " +
                        " inner join tour_type tt on t.type_of_the_tour = tt.id where t.cost<=?");
                preparedStatement.setDouble(1, costOfTheTour);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    log.info("Tour id -" + resultSet.getInt("id") + ", tour cost - " + resultSet.getDouble("cost") + ", name - " + resultSet.getString("name") +
                            "tour type id - " + resultSet.getInt("type_of_the_tour") + "tour type name - " + resultSet.getString("tour_type.name"));
                }
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void showToursWithEstablishedTourType(String nameOfTheTourType) {
        if (MybatisImplementation.flag) {
            try (SqlSession session = MybatisImplementation.getSessionFactory().openSession(true)) {
                TourRepository tourRepository = session.getMapper(TourRepository.class);
                tourRepository.showToursWithEstablishedTourType(nameOfTheTourType);

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select t.id, t.name, t.cost, tt.id, tt.name from tour t" +
                        " inner join tour_type tt on t.type_of_the_tour = tt.id where tour_type.name=?");
                preparedStatement.setString(1, nameOfTheTourType);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    log.info("Tour id -" + resultSet.getInt("id") + ", tour cost - " + resultSet.getDouble("cost") + ", name - " + resultSet.getString("name") +
                            "tour type id - " + resultSet.getInt("type_of_the_tour") + "tour type name - " + resultSet.getString("tour_type.name"));
                }
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }
}
