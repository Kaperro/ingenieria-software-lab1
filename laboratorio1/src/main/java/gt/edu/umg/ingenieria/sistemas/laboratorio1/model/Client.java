package gt.edu.umg.ingenieria.sistemas.laboratorio1.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Entity
public class Client implements Serializable {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String nit;
    private String phone;
    private String address;
    private Date date;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String nit, String phone, String address, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nit = nit;
        this.phone = phone;
        this.address = address;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
