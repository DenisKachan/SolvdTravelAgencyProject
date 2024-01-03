package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.TravelAgent;
import com.solvd.travelAgencyProject.persistence.interfaces.TravelAgentRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisConfiguration;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class TravelAgentImpl implements TravelAgentRepository {
    @Override
    public Connection create(TravelAgent value) throws SQLException {
        if (MybatisConfiguration.flag) {
            Connection travelAgentConnection = null;
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                travelAgentConnection = session.getConnection();
                travelAgentConnection.setAutoCommit(false);
                TravelAgentRepository travelAgentRepository = session.getMapper(TravelAgentRepository.class);
                travelAgentRepository.create(value);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return travelAgentConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try (connection) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into travel_agent(name, surname, tour_responsibility) \n" +
                        "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, value.getName());
                preparedStatement.setString(2, value.getSurname());
                preparedStatement.setInt(3, value.getTourType().getId());
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
                TravelAgentRepository travelAgentRepository = session.getMapper(TravelAgentRepository.class);
                travelAgentRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete travel_agent where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(TravelAgent value, int id) {
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                TravelAgentRepository travelAgentRepository = session.getMapper(TravelAgentRepository.class);
                travelAgentRepository.updateById(value, id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update travel_agent  set name=?, surname=?, tour_responsibility=? \n" +
                        "where id = ?;");
                preparedStatement.setString(1, value.getName());
                preparedStatement.setString(2, value.getSurname());
                preparedStatement.setInt(3, value.getTourType().getId());
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public TravelAgent getById(int id) {
        TravelAgent travelAgent = new TravelAgent();
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                TravelAgentRepository travelAgentRepository = session.getMapper(TravelAgentRepository.class);
                travelAgent = travelAgentRepository.getById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from travel_agent where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                travelAgent.setId(id);
                travelAgent.setName(resultSet.getString("name"));
                travelAgent.setSurname(resultSet.getString("surname"));
                travelAgent.setTourTypeId(resultSet.getInt("tour_responsibility"));
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
        return travelAgent;
    }
}
