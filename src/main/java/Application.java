import dao.EmployeeDao;
import entities.Employee;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Application {

    public static void main(String[] args){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory()) {
            EmployeeDao employeeDAO = new EmployeeDao(sessionFactory);



            try (Session session = sessionFactory.getCurrentSession()) {
                Transaction tx = session.beginTransaction();
                // create departments

                employeeDAO.save(new Employee("EmpName-1", "Address-32"));
                employeeDAO.save(new Employee("EmpName-2", "Address-54"));
                tx.commit();
            }

            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                System.out.println("Employees");
                employeeDAO.list().forEach(employee -> System.out.println(employee.getId() +", " + employee.getName() + ", " + employee.getAddress() ));
                System.out.println("Departments");
            }
        }
    }
}