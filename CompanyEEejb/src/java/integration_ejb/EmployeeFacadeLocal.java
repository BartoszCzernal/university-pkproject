/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration_ejb;

import business_layer.entity.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bartek
 */
@Local
public interface EmployeeFacadeLocal {

    void create(Employee employee);

    void edit(Employee employee);

    void remove(Employee employee);

    Employee find(Object id);

    List<Employee> findAll();

    List<Employee> findRange(int[] range);

    int count();
    
}
