package minibank.bbva.model.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import minibank.bbva.model.dao.DAO;
import minibank.bbva.model.entitys.Movements;
import minibank.bbva.model.entitys.Person;

@Repository("daoPerson")
public class PersonService implements DAO<Person> {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	@Resource(name = "template")
	public void setConnection(JdbcTemplate jdbc) throws SQLException {
		this.jdbc = jdbc;
	}

	@Override
	public boolean save(Person person) {
		String sql = "INSERT INTO Persona(Dni,Name,LastName,Email,CellPhone)" + " VALUES (?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, person.getDni());
			ps.setString(2, person.getName());
			ps.setString(3, person.getLastName());
			ps.setString(4, person.getEmail());
			ps.setObject(5, person.getCellphone());
			return ps;
		}, keyHolder);
		person.setId(keyHolder.getKey().longValue());
		return person.getId() != 0;
	}

	@Override
	public Optional<Person> read(String id) {
		String sql = "SELECT * FROM Persona WHERE Dni = ?";
		Person a = jdbc.queryForObject(sql, this::mapElements, id);
		return Optional.ofNullable(a);
	}

	private Person mapElements(ResultSet rs, int rowNum) throws SQLException {
		return new Person(rs.getLong("Id"), rs.getLong("Dni"), rs.getString("Name"), rs.getString("LastName"),
				rs.getString("CellPhone"), rs.getString("Email"));
	}

	@Override
	public void delete(Person t) {
		String sql = "DELETE FROM Persona WHERE Dni = ?";
		Object[] args = new Object[] { t.getDni() };
		jdbc.update(sql, args);

	}

	@Override
	public Collection<Person> readAll() {
		String sql = "SELECT * FROM Persona";
		return jdbc.query(sql, this::mapElements);
	}

	@Override
	public boolean update(Person person) {
		throw new MethodNotFoundException();
	}

	@Override
	public List<Person> readSpecific(String id) {
		throw new MethodNotFoundException();
	}

	@Override
	public Optional<Person> getSpecialParam(String name) {
		String sql = "SELECT * FROM Persona WHERE Name = ?";
		Person a = jdbc.queryForObject(sql, this::mapElements, name);
		return Optional.ofNullable(a);
	}

	public List<Person> getForName(String name) {
		String sql = "SELECT * FROM Persona WHERE Name LIKE ' ? ' ";
		var listPersons = new ArrayList<Person>();

		List<Map<String, Object>> rows = jdbc.queryForList(sql, name);
		for (Map rs : rows) {
			var obj = new Person();
			obj.setCellphone((String) rs.get("Cellphone"));
			obj.setDni((Long) rs.get("Dni"));
			obj.setEmail((String) rs.get("Email"));
			obj.setId((long) rs.get("id"));
			obj.setLastName((String) rs.get("LastName"));
			obj.setName((String) rs.get("Name"));
			listPersons.add(obj);
		}
		return listPersons;

	}

}
