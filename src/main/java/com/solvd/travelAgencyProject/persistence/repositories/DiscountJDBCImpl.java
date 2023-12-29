package com.solvd.travelAgencyProject.persistence.repositories;

import com.solvd.travelAgencyProject.domain.Discount;
import com.solvd.travelAgencyProject.persistence.interfaces.DiscountRepository;
import com.solvd.travelAgencyProject.persistence.utils.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.utils.MybatisConfiguration;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;

@Log4j2
public class DiscountJDBCImpl implements DiscountRepository {


    @Override
    public Connection create(Discount value) throws SQLException {
        if (MybatisConfiguration.flag) {
            Connection discountConnection = null;
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                discountConnection = session.getConnection();
                discountConnection.setAutoCommit(false);
                DiscountRepository discountRepository = session.getMapper(DiscountRepository.class);
                discountRepository.create(value);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return discountConnection;
        } else {
            Connection connection = ConnectionPool.getConnectionFromPool();
            connection.setAutoCommit(false);
            try (connection) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into discount(amount_of_tours, amount_of_discount) \n" +
                        "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, value.getAmountOfTours());
                preparedStatement.setDouble(2, value.getAmountOfDiscount());
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
                DiscountRepository discountRepository = session.getMapper(DiscountRepository.class);
                discountRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete discount where id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public void updateById(Discount value, int id) {
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                DiscountRepository discountRepository = session.getMapper(DiscountRepository.class);
                discountRepository.updateById(value, id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("Update discount  set amount_of_tours=?, amount_of_discount=? \n" +
                        "where id = ?;");
                preparedStatement.setInt(1, value.getAmountOfTours());
                preparedStatement.setDouble(2, value.getAmountOfDiscount());
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
    }

    @Override
    public Discount getById(int id) {
        Discount discount = new Discount();
        if (MybatisConfiguration.flag) {
            try (SqlSession session = MybatisConfiguration.getSessionFactory().openSession(true)) {
                DiscountRepository discountRepository = session.getMapper(DiscountRepository.class);
                discount = discountRepository.getById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            try (Connection connection = ConnectionPool.getConnectionFromPool()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from discount where id=?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                discount.setId(id);
                discount.setAmountOfTours(resultSet.getInt("amount_of_tours"));
                discount.setAmountOfDiscount(resultSet.getDouble("amount_of_discount"));
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
            }
        }
        return discount;
    }
}
