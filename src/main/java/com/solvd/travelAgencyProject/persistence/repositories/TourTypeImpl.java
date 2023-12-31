package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.TourType;
import com.solvd.travelAgencyProject.persistence.interfaces.TourTypeRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisConfiguration;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class TourTypeImpl implements TourTypeRepository {

    @Override
    public Connection create(TourType value) throws SQLException {
        if (MybatisConfiguration.flag) {
            Connection tourTypeConnection = null;
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                tourTypeConnection = session.getConnection();
                tourTypeConnection.setAutoCommit(false);
                TourTypeRepository tourTypeRepository = session.getMapper(TourTypeRepository.class);
                tourTypeRepository.create(value);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return tourTypeConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try (connection) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into tour_type(name) \n" +
                        "VALUES ('sea');", Statement.RETURN_GENERATED_KEYS);
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
                TourTypeRepository tourTypeRepository = session.getMapper(TourTypeRepository.class);
                tourTypeRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete tour_type where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(TourType value, int id) {
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                TourTypeRepository tourTypeRepository = session.getMapper(TourTypeRepository.class);
                tourTypeRepository.updateById(value, id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update tour_type  set name=? \n" +
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
    public TourType getById(int id) {
        TourType tourType = new TourType();
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                TourTypeRepository tourTypeRepository = session.getMapper(TourTypeRepository.class);
                tourType = tourTypeRepository.getById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from tour_type where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                tourType.setId(id);
                tourType.setName(resultSet.getString("name"));
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
        return tourType;
    }
}
