package business_layer;

import business_layer.dto.EmployeeDto;
import business_layer.entity.Employee;
import java.util.ArrayList;
import java.util.List;

public class FacadeBusinessLayer {
    
    private static long key = 0;
    private ArrayList<Employee> employees = new ArrayList<>();
    private boolean state = false;

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    public int count() {
        return employees.size();
    }
    
    public ArrayList<EmployeeDto> findRange(int[] range) {
        ArrayList<EmployeeDto> temp = new ArrayList();
        if (getEmployees().isEmpty()) {
            state = false;
            return temp;
        }
        for (int i = range[0]; i < range[1]; i++) {
            temp.add(makeEmployeeDto(getEmployees().get(i)));
        }
        return temp;
    }
    
    public void createEmployee(EmployeeDto employeeDto) {
        Employee employee = makeEmployee(employeeDto);
        addEmployee(employee);
    }
    
    public Employee makeEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setCity(employeeDto.getCity());
        employee.setJobPosition(employeeDto.getJobPosition());
        employee.setSalary(employeeDto.getSalary());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setStartDate(employeeDto.getStartDate());
        return employee;
    }
    
    protected void getMaxKey() {
        long max = 0;
        for (Employee e : employees) {
            if (e.getId() > max) {
                max = e.getId();
            }
        }
        key = max + 1;
    }
    
    protected void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            state = true;
        } else {
            state = false;
        }
    }
    
    public EmployeeDto getEmployeeData() {
        if (state) {
            Employee employee = employees.get(employees.size() - 1);
            return makeEmployeeDto(employee);
        }
        return null;
    }
    
    public EmployeeDto makeEmployeeDto(Employee employee) {
        EmployeeDto temp = new EmployeeDto();
        temp.setId(employee.getIdNotNull());
        temp.setFirstName(employee.getFirstName());
        temp.setLastName(employee.getLastName());
        temp.setCity(employee.getCity());
        temp.setJobPosition(employee.getJobPosition());
        temp.setSalary(employee.getSalary());
        temp.setDateOfBirth(employee.getDateOfBirth());
        temp.setStartDate(employee.getStartDate());
        return temp;
    }
    
    public ArrayList<EmployeeDto> getItemsDto() {
        ArrayList<EmployeeDto> data = new ArrayList<>();
        employees.forEach((e) -> {
            data.add(makeEmployeeDto(e));
        });
        return data;
    }
    
    public ArrayList<ArrayList<String>> getItems() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        employees.stream().map((e) -> {
            ArrayList<String> row = new ArrayList<>();
            row.add(e.getIdNotNull().toString());
            row.add(e.getFirstName());
            row.add(e.getLastName());
            row.add(e.getCity());
            row.add(e.getJobPosition());
            row.add("" + e.getSalary());
            row.add(e.getDateOfBirth().toString());
            row.add(e.getStartDate().toString());
            return row;
        }).forEachOrdered((row) -> {
            data.add(row);
        });
        return data;
    }
    
    public int isThereEmployee(EmployeeDto e) {
        Employee temp = this.makeEmployee(e);
        return getEmployees().indexOf(temp);
    }
    
    public boolean edit(EmployeeDto employeeBefore, EmployeeDto employeeUpdate) {
        int index1, index2;
        state = true;
        index1 = this.isThereEmployee(employeeBefore);
        if (index1 == -1) {
            return false;
        }
        index2 = this.isThereEmployee(employeeUpdate);
        if (index2 != -1) {
            return false;
        }
        Employee e = getEmployees().get(index1);
        e.setFirstName(employeeUpdate.getFirstName());
        e.setLastName(employeeUpdate.getLastName());
        e.setCity(employeeUpdate.getCity());
        e.setJobPosition(employeeUpdate.getJobPosition());
        e.setSalary(employeeUpdate.getSalary());
        e.setDateOfBirth(employeeUpdate.getDateOfBirth());
        e.setStartDate(employeeUpdate.getStartDate());
        return true;
    }
    
    public void remove(EmployeeDto eDto) {
        Employee e = this.makeEmployee(eDto);
        this.getEmployees().remove(e);
    }
    
    public void getEmployeesFromDB(List<Employee> employeesDB) {
        employees.clear();
        employees.addAll(employeesDB);
    }
}
