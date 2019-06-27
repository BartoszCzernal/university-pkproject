package business_layer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String firstName;
    private String lastName;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    private String city;
    private String jobPosition;
    private int salary;

    public Long getId() {
        return id;
    }
    
    public Long getIdNotNull() {
        if (id == null) {
            return new Long(0);
        }
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 71 * hash + Objects.hashCode(this.lastName);
        hash = 71 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 71 * hash + Objects.hashCode(this.startDate);
        hash = 71 * hash + Objects.hashCode(this.city);
        hash = 71 * hash + Objects.hashCode(this.jobPosition);
        hash = 71 * hash + this.salary;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.salary != other.salary) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.jobPosition, other.jobPosition)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", startDate=" + startDate + ", city=" + city + ", jobPosition=" + jobPosition + ", salary=" + salary + '}';
    }
     
    
    public boolean compareFirstName(String firstName) {
        return this.firstName.equals(firstName);
    }
    
    public boolean compareLastName(String lastName) {
        return this.lastName.equals(lastName);
    }
    
    public boolean compareCity(String city) {
        return this.city.equals(city);
    }
}
