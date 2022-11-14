package dao;

import entities.Employee;
import org.hibernate.SessionFactory;

public class EmployeeDao extends AbstractCrudDao<Employee> {
    public EmployeeDao(SessionFactory sessionFactory) {
        super(sessionFactory, Employee.class, "Employee");
    }
}