package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.MainTransport;
import com.solvd.travelAgencyProject.persistence.interfaces.MainTransportRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisConfiguration;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class MainTransportImpl implements MainTransportRepository {
    @Override
    public Connection create(MainTransport value) throws SQLException {
        if (MybatisConfiguration.flag) {
            SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true);
            Connection mainTransportConnection = session.getConnection();
            try {
                mainTransportConnection.setAutoCommit(false);
                MainTransportRepository mainTransportRepository = session.getMapper(MainTransportRepository.class);
                mainTransportRepository.create(value);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return mainTransportConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into main_transport(name) \n" +
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
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                MainTransportRepository mainTransportRepository = session.getMapper(MainTransportRepository.class);
                mainTransportRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete main_transport where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(MainTransport value, int id) {
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                MainTransportRepository mainTransportRepository = session.getMapper(MainTransportRepository.class);
                mainTransportRepository.updateById(value, id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update main_transport  set name=? \n" +
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
    public MainTransport getById(int id) {
        MainTransport mainTransport = new MainTransport();
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                MainTransportRepository mainTransportRepository = session.getMapper(MainTransportRepository.class);
                mainTransport = mainTransportRepository.getById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from main_transport where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                mainTransport.setId(id);
                mainTransport.setName(resultSet.getString("name"));
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
        return mainTransport;
    }
}
