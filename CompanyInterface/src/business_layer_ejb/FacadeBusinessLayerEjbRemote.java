package business_layer_ejb;

import business_layer.dto.EmployeeDto;
import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface FacadeBusinessLayerEjbRemote {
    public void createEmployee(EmployeeDto employeeDto);
    public EmployeeDto getEmployeeData();
    public ArrayList<ArrayList<String>> getItems();
    public ArrayList<EmployeeDto> getItemsDto();
    public int count();
    public ArrayList<EmployeeDto> findRange(int[] range);
    public boolean isState();
    public void setState(boolean state);
    public boolean edit(EmployeeDto employeeBefore, EmployeeDto employeeUpdate);
    public void remove(EmployeeDto eDto);
    public void save();
    public void load();
    public ArrayList<EmployeeDto> search(String search, int value);
    public int getSumSalary();
}
