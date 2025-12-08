package Model;
import DBLayer.DbConnection;
import DTO.CustomerDto;
import DTO.EmployeesDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeModle {
    public boolean saveEmployee(EmployeesDto employeesDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Employee VALUES (?,?,?,?)";
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1, employeesDto.getId());
        ptsm.setString(2, employeesDto.getName());
        ptsm.setString(3, employeesDto.getDuty());
        ptsm.setString(4, employeesDto.getEmail());
        return ptsm.executeUpdate() > 0;
    }

    public  boolean updateEmployee(EmployeesDto employeesDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE  Employee SET  Name = ?, Duty = ?, Email = ? WHERE Id = ?";
        System.out.println("Executing query: " + sql);
        System.out.println("With parameters: " + employeesDto.getName() + ", " + employeesDto.getDuty() + ", " + employeesDto.getEmail() + ", " + employeesDto.getId());

        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setString(1, employeesDto.getName());
        ptsm.setString(2, employeesDto.getDuty());
        ptsm.setString(3, employeesDto.getEmail());
        ptsm.setString(4, employeesDto.getId());

        return ptsm.executeUpdate() > 0;
    }

    public boolean deleteEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Employee WHERE Id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }


}
