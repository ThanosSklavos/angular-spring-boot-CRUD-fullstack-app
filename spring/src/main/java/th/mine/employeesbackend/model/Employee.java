package th.mine.employeesbackend.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRSTNAME")
    @NotNull
    private String firstname;
    @Column(name = "LASTNAME")
    @NotNull
    private String lastname;
    @Column(name = "EMAILID")
    private String emailId;


 }
