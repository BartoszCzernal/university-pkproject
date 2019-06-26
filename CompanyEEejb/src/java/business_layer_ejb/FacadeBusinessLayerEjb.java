package business_layer_ejb;

import business_layer.FacadeBusinessLayer;
import business_layer.dto.EmployeeDto;
import business_layer.entity.Employee;
import integration_ejb.EmployeeFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class FacadeBusinessLayerEjb implements FacadeBusinessLayerEjbRemote {

    @EJB
    private EmployeeFacadeLocal employeeFacade;

    
    
    private FacadeBusinessLayer facade = new FacadeBusinessLayer();
    
    
    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        facade.createEmployee(employeeDto);
    }

    @Override
    public EmployeeDto getEmployeeData() {
        return facade.getEmployeeData();
    }

    @Override
    public ArrayList<ArrayList<String>> getItems() {
        return facade.getItems();
    }

    @Override
    public ArrayList<EmployeeDto> getItemsDto() {
        return facade.getItemsDto();
    }

    @Override
    public int count() {
        return facade.count();
    }

    @Override
    public ArrayList<EmployeeDto> findRange(int[] range) {
        return facade.findRange(range);
    }

    @Override
    public boolean isState() {
        return facade.isState();
    }

    @Override
    public void setState(boolean state) {
        facade.setState(state);
    }

    @Override
    public boolean edit(EmployeeDto employeeBefore, EmployeeDto employeeUpdate) {
        return facade.edit(employeeBefore, employeeUpdate);
    }

    @Override
    public void remove(EmployeeDto eDto) {
        facade.remove(eDto);
    }

    @Override
    public void save() {
        List<Employee> temp = employeeFacade.findAll();
        facade.getEmployeesFromDB(temp);
    }

    @Override
    public void load() {
        for (Employee e : facade.getEmployees()) {
            Long id = e.getId();
            if (id == null || employeeFacade.find(e.getId()) == null) {
                employeeFacade.create(e);
            }
        }
    }
    
    @PostConstruct
    public void init() {
        try {
            load();
        } catch (Exception e) {}
    }
    
    @Override
    public ArrayList<EmployeeDto> search(String search) {
        ArrayList<EmployeeDto> temp = new ArrayList<>();
        for (EmployeeDto e : facade.getItemsDto()) {
            if (e.getFirstName().equals(search) || e.getLastName().equals(search)) {
                temp.add(e);
            }
        }
        return temp;
    }
}
    
