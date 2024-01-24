package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.persistence.interfaces.ClientAgreementRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisConfiguration;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class ClientAgreementImpl implements ClientAgreementRepository {

    @Override
    public Connection create(ClientAgreement value) throws SQLException {
        if (MybatisConfiguration.flag) {
            SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true);
            Connection clientAgreementConnection = session.getConnection();
            try  {
                clientAgreementConnection.setAutoCommit(false);
                ClientAgreementRepository clientAgreementRepository = session.getMapper(ClientAgreementRepository.class);
                clientAgreementRepository.create(value);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return clientAgreementConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into client_agreement(conditions, client_id, client_discount_id, travel_agent_id, tour_identificator, tour_transport, tour_type) \n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, value.getConditions());
                preparedStatement.setInt(2, value.getClient().getId());
                preparedStatement.setInt(3, value.getDiscount().getId());
                preparedStatement.setInt(4, value.getTravelAgent().getId());
                preparedStatement.setInt(5, value.getTour().getId());
                preparedStatement.setInt(6, value.getMainTransport().getId());
                preparedStatement.setInt(7, value.getTourType().getId());
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
                ClientAgreementRepository clientAgreementRepository = session.getMapper(ClientAgreementRepository.class);
                clientAgreementRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete client_agreement where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(ClientAgreement value, int id) {
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                ClientAgreementRepository clientAgreementRepository = session.getMapper(ClientAgreementRepository.class);
                clientAgreementRepository.updateById(value, id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update client_agreement  set conditions=?, client_id=?, client_discount_id=?, travel_agent_id=?, tour_identificator=?, tour_transport=?, tour_type=? \n" +
                        "where id = ?;");
                preparedStatement.setString(1, value.getConditions());
                preparedStatement.setInt(2, value.getClient().getId());
                preparedStatement.setInt(3, value.getDiscount().getId());
                preparedStatement.setInt(4, value.getTravelAgent().getId());
                preparedStatement.setInt(5, value.getTour().getId());
                preparedStatement.setInt(6, value.getMainTransport().getId());
                preparedStatement.setInt(7, value.getTourType().getId());
                preparedStatement.setInt(8, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public ClientAgreement getById(int id) {
        ClientAgreement clientAgreement = new ClientAgreement();
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                ClientAgreementRepository clientAgreementRepository = session.getMapper(ClientAgreementRepository.class);
                clientAgreement = clientAgreementRepository.getById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from client_agreement where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                clientAgreement.setId(id);
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
        }
        return clientAgreement;
    }
}
