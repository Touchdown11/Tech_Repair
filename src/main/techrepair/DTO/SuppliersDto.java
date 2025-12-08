package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersDto {

    private String Name;
    private String Id;
    private String Quantity;
    private String Contact;
    private int Price;
    private String Address;


}
