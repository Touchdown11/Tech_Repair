package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerJobDto {
    String Id;
    String CustomerId;
    String EmployeeId;
    String Problem;
    Double Price;
}
