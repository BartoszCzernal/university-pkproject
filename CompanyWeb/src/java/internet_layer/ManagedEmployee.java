package internet_layer;

import business_layer.dto.EmployeeDto;
import business_layer_ejb.FacadeBusinessLayerEjbRemote;
import helper.JsfUtil;
import helper.PaginationHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.NumberConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

@Named(value = "managedEmployee")
@SessionScoped
public class ManagedEmployee implements Serializable, ActionListener {

    @EJB
    private FacadeBusinessLayerEjbRemote facade;
    
    
    
    public ManagedEmployee() {}
    private int backPage = 1;
    private PaginationHelper pagination;
    private EmployeeDto employeeDto = new EmployeeDto();
    private DataModel items;
    private int state = 1;
    private EmployeeDto employeeDtoBefore;
    private NumberConverter number_convert = new NumberConverter();
    
    public NumberConverter getNumber_convert() {
        this.number_convert.setPattern("######.## zł");
        return number_convert;
    }

    public void setNumber_convert(NumberConverter Number_convert) {
        this.number_convert = Number_convert;
    }
    
    public int getBackPage() {
        return backPage;
    }
    
    public String destroy() {
        employeeDto = (EmployeeDto)items.getRowData();
        int howMuch = items.getRowCount();
        if (howMuch == 1) {
            this.getPagination().previousPage();
        }
        performDestroy();
        return "lista_pracownikow";
    }
    
    private void performDestroy() {
        try {
            getFacade().remove(employeeDto);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Usunieto_pracownika"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("Blad_usuwania"));
        }
    }
    
    public String refresh() {
        getPagination().updatePage();
        items = getPagination().createPageDataModel();
        return "lista_pracownikow";
    }
    
    public String prepareEdit() {
        employeeDto = (EmployeeDto) items.getRowData();
        employeeDtoBefore = new EmployeeDto(employeeDto);
        return "dodaj_pracownika";
    }
    
    public String update() {
        try {
            boolean result = getFacade().edit(employeeDtoBefore, employeeDto);
            employeeDto = new EmployeeDto();
            recreateModel();
            if (result) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Pracownik_zmieniony"));
            } else {
                throw new Exception();
            }
            return "lista_pracownikow";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("Blad_modyfikacji"));
            return "lista_pracownikow";
        }
    }
    
    public String prepareView() {
        employeeDto = (EmployeeDto) items.getRowData();
        backPage = 0;
        state = 1;
        return "rezultat";
    }
    
    public String back() {
        backPage = 1;
        employeeDto = new EmployeeDto();
        return "lista_pracownikow";
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(3) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    int[] range = {getPageFirstItem(), getPageLastItem() + 1};
                    return new ListDataModel(getFacade().findRange(range));
                }
            };
        }
        return pagination;
    }
    
    private void recreateModel() {
        items = null;
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "lista_pracownikow";
    }
    
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "lista_pracownikow";
    }
    
    public String getFirstName() {
        return employeeDto.getFirstName();
    }
    
    public void setFirstName(String firstName) {
        this.employeeDto.setFirstName(firstName);
    }
    
    public String getLastName() {
        return employeeDto.getLastName();
    }
    
    public void setLastName(String lastName) {
        this.employeeDto.setLastName(lastName);
    }
    
    public String getCity() {
        return employeeDto.getCity();
    }
    
    public void setCity(String city) {
        this.employeeDto.setCity(city);
    }
    
    public String getJobPosition() {
        return employeeDto.getJobPosition();
    }
    
    public void setJobPosition(String jobPosition) {
        this.employeeDto.setJobPosition(jobPosition);
    }
    
    public int getSalary() {
        return employeeDto.getSalary();
    }
    
    public void setSalary(int salary) {
        this.employeeDto.setSalary(salary);
    }
    
    public Date getDateOfBirth() {
        return employeeDto.getDateOfBirth();
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.employeeDto.setDateOfBirth(dateOfBirth);
    }
    
    public Date getStartDate() {
        return employeeDto.getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.employeeDto.setStartDate(startDate);
    }
    
    public FacadeBusinessLayerEjbRemote getFacade() {
        return facade;
    }

    public void setFacade(FacadeBusinessLayerEjbRemote facade) {
        this.facade = facade;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }
    
    public void createEmployee() {
        facade.createEmployee(employeeDto);
        backPage = 1;
        getEmployeeData();
        recreateModel();
        getPagination().nextPage();
    }
    
    public void getEmployeeData() {
        state = 1;
        employeeDto = facade.getEmployeeData();
        if (employeeDto == null ) {
            employeeDto = new EmployeeDto();
            state = 0;
        }
    }

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        createEmployee();
    }
    
    public DataModel createDataModel() {
        return new ListDataModel(facade.getItemsDto());
    }
    
    public DataModel getItems() {
        if (items == null || facade.isState()) {
            items = getPagination().createPageDataModel();
        }
        backPage = 1;
        return items;
    }
    
    public String save() {
        facade.save();
        return "/faces/index";
    }
    
    public String load() {
        facade.load();
        refresh();
        return "/faces/index";
    }
    
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    
    ArrayList<EmployeeDto> foundEmployees;

    public ArrayList<EmployeeDto> getFoundEmployees() {
        return foundEmployees;
    }

    public void setFoundEmployees(ArrayList<EmployeeDto> foundEmployees) {
        this.foundEmployees = foundEmployees;
    }
    
    
    public void search(int value) {
        foundEmployees = facade.search(search, value);
    }
    
    public int getSumSalary() {
        return facade.getSumSalary();
    }
    
   
    
}
