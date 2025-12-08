package Model;

import DBLayer.DbConnection;
import DTO.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerModle {
    // functions
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException {
        //db connction
        Connection connection = DbConnection.getInstance().getConnection();
        // data insert to database
        String sql = "INSERT INTO Customer VALUES (?,?,?,?)";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1, customerDto.getId());
        ptsm.setString(2, customerDto.getName());
        ptsm.setString(3, customerDto.getContact());
        ptsm.setString(4, customerDto.getEmail());
        return ptsm.executeUpdate() > 0;
    }

    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Customer SET Name = ?, Contact = ?, Email = ? WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customerDto.getName());
        pstm.setString(2, customerDto.getContact());
        pstm.setString(3, customerDto.getEmail());
        pstm.setString(4, customerDto.getId());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Customer WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }
}
