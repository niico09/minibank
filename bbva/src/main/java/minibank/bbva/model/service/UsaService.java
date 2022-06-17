package minibank.bbva.model.service;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import minibank.bbva.model.dao.DAO;
import minibank.bbva.model.entitys.Person;

public class UsaService {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/contexto-template-jdbc.xml");

		DAO<Person> aDAO = ctx.getBean("daoPerson", DAO.class);

		Person a = new Person();
		a.setCellphone("1126456472");
		a.setDni(39878422L);
		a.setEmail("nico.09g@gmail.com");
		a.setLastName("gomez");
		a.setName("nicolas");

		System.out.println("Antes de persistir");
		System.out.println(a);

		System.out.println("\nPersistir");
		System.out.println(aDAO.save(a));

		System.out.println("\nDespuÃ©s de persistir");

		System.out.println(a);

		System.out.println("\nBuscar: 1L");

		((ConfigurableApplicationContext) ctx).close();

	}
}
