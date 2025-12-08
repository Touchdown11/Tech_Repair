package Model;

import DBLayer.DbConnection;
import DTO.CustomerDto;
import DTO.SuppliersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuppliresModle {
    public boolean saveSupplires(SuppliersDto suppliersDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Supplier VALUES (?,?,?,?,?,?)";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1, suppliersDto.getId());
        ptsm.setString(2, suppliersDto.getName());
        ptsm.setString(3, suppliersDto.getContact());
        ptsm.setString(4, suppliersDto.getQuantity());
        ptsm.setInt(5, suppliersDto.getPrice());
        ptsm.setString(6,suppliersDto.getAddress());

        return ptsm.executeUpdate() > 0;
    }

    public boolean updatesupplires(SuppliersDto suppliersDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Supplier SET Name = ?, Contact = ?, Quantity = ?, Price = ?, Address = ? WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, suppliersDto.getName());
        pstm.setString(2, suppliersDto.getContact());
        pstm.setString(3, suppliersDto.getQuantity());
        pstm.setInt(4, suppliersDto.getPrice());
        pstm.setString(5, suppliersDto.getAddress());
        pstm.setString(6, suppliersDto.getId());


        return pstm.executeUpdate() > 0;
    }

    public boolean deleteSuppliers(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Supplier WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }
}
