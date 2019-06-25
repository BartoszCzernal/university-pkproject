package business_layer.dto;

import java.util.Date;

public class EmployeeDto {
    
    protected long id;
    protected String firstName;
    protected String lastName;
    protected Date dateOfBirth;
    protected Date startDate;
    protected String city;
    protected String jobPosition;
    protected int salary;
    
    public EmployeeDto() {}
    public EmployeeDto(String[] dane, Date dateOfBirth, Date startDate) {
        firstName = dane[0];
        lastName = dane[1];
        city = dane[2];
        jobPosition = dane[3];
        salary = Integer.parseInt(dane[4]);
        this.dateOfBirth = dateOfBirth;
        this.startDate = startDate;
    }
    
    public EmployeeDto(EmployeeDto e) {
        firstName = e.getFirstName();
        lastName = e.getLastName();
        city = e.getCity();
        jobPosition = e.getJobPosition();
        salary = e.getSalary();
        this.dateOfBirth = e.getDateOfBirth();
        this.startDate = e.getStartDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
     
}
