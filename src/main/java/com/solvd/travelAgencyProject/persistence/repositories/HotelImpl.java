package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.persistence.interfaces.HotelRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisConfiguration;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class HotelImpl implements HotelRepository {
    @Override
    public Connection create(Hotel value) throws SQLException {
        if (MybatisConfiguration.flag) {
            Connection hotelConnection = null;
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                hotelConnection = session.getConnection();
                hotelConnection.setAutoCommit(false);
                HotelRepository hotelRepository = session.getMapper(HotelRepository.class);
                hotelRepository.create(value);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return hotelConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try (connection) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into hotel(name, capacity, phone_number, hotel_chain_identificator) \n" +
                        "VALUES (?, ?,?,?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, value.getName());
                preparedStatement.setInt(2, value.getCapacity());
                preparedStatement.setInt(3, value.getTelephoneNumber());
                preparedStatement.setInt(4, value.getHotelChain().getId());
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
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                HotelRepository hotelRepository = session.getMapper(HotelRepository.class);
                hotelRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete hotel where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(Hotel value, int id) {
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                HotelRepository hotelRepository = session.getMapper(HotelRepository.class);
                hotelRepository.updateById(value, id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update hotel  set name=?, capacity=?, phone_number=?, hotel_chain_identificator=? \n" +
                        "where id = ?;");
                preparedStatement.setString(1, value.getName());
                preparedStatement.setInt(2, value.getCapacity());
                preparedStatement.setInt(3, value.getTelephoneNumber());
                preparedStatement.setInt(4, value.getHotelChain().getId());
                preparedStatement.setInt(5, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public Hotel getById(int id) {
        Hotel hotel = new Hotel();
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                HotelRepository hotelRepository = session.getMapper(HotelRepository.class);
                hotel = hotelRepository.getById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from hotel where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                hotel.setId(id);
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
        return hotel;
    }
}
