package Model;

import DBLayer.DbConnection;
import DTO.CustomerDto;
import DTO.CustomerJobDto;
import DTO.OrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerJobModel {
    public boolean saveCustomerJob(CustomerJobDto customerJobDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO `Job` VALUES (?,?,?,?,?)";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1, customerJobDto.getId());
        ptsm.setString(2,customerJobDto.getCustomerId());
        ptsm.setString(3, customerJobDto.getEmployeeId());
        ptsm.setString(4, customerJobDto.getProblem());
        ptsm.setDouble(5, customerJobDto.getPrice());

        return ptsm.executeUpdate() > 0;
    }

    public boolean deleteCustomerOrder(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM `Job` WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public String getCustEmail(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT Email FROM Customer WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        // Execute the query and retrieve the ResultSet
        ResultSet resultSet = pstm.executeQuery();

        // Check if there is a result and return the email
        if (resultSet.next()) {
            return resultSet.getString("Email");
        }

        // Return null or throw an exception if no result found
        return null;
    }

    public String getCustomerEmailbyOrderId(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT Email FROM Customer WHERE Id = (SELECT CustomerId FROM `Job` WHERE Id = ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        // Execute the query and retrieve the ResultSet
        ResultSet resultSet = pstm.executeQuery();

        // Check if there is a result and return the email
        if (resultSet.next()) {
            return resultSet.getString("Email");
        }

        // Return null or throw an exception if no result found
        return null;
    }



    public boolean isOrderExist(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM `Job` WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.executeQuery();
        return pstm.getResultSet().next();
    }
}
