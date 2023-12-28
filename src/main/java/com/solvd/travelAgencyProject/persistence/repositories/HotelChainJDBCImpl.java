package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.HotelChain;
import com.solvd.travelAgencyProject.persistence.interfaces.HotelChainRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisImplementation;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class HotelChainJDBCImpl implements HotelChainRepository {
    @Override
    public Connection create(HotelChain value) throws SQLException {
        if (MybatisImplementation.flag) {
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            Connection hotelChainConnection = session.getConnection();
            hotelChainConnection.setAutoCommit(false);
            HotelChainRepository hotelChainRepository = session.getMapper(HotelChainRepository.class);
            hotelChainRepository.create(value);
            return hotelChainConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try (connection) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into hotel_chain(name) \n" +
                        "VALUES (?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, value.getName());
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
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            HotelChainRepository hotelChainRepository = session.getMapper(HotelChainRepository.class);
            hotelChainRepository.deleteById(id);
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete hotel_chain where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(HotelChain value, int id) {
        if (MybatisImplementation.flag) {
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            HotelChainRepository hotelChainRepository = session.getMapper(HotelChainRepository.class);
            hotelChainRepository.updateById(value, id);
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update hotel_chain  set name=? \n" +
                        "where id = ?;");
                preparedStatement.setString(1, value.getName());
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public HotelChain getById(int id) {
        if (MybatisImplementation.flag) {
            SqlSession session = MybatisImplementation.getSessionFactory().openSession(true);
            HotelChainRepository hotelChainRepository = session.getMapper(HotelChainRepository.class);
            return hotelChainRepository.getById(id);
        } else {
            HotelChain hotelChain = new HotelChain();
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from hotel_chain where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                hotelChain.setId(id);
                hotelChain.setName(resultSet.getString("name"));
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
            return hotelChain;
        }
    }
}
