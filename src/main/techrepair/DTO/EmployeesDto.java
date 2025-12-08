package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EmployeesDto {
    private String Id;
    private String Name;
    private String Duty;
    private String Email;
}
