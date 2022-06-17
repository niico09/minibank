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

@Repository("daoMovement")
public class MovementService implements DAO<Movements> {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	@Resource(name = "template")
	public void setConnection(JdbcTemplate jdbc) throws SQLException {
		this.jdbc = jdbc;
	}

	@Override
	public boolean save(Movements movements) {
		String sql = "INSERT INTO Movements(DayTransfer,Amount,Description,TypeMoney,Origin,Destination, Site)"
				+ "VALUES (?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, (java.sql.Date) new Date());
			ps.setDouble(2, movements.getAmount());
			ps.setString(3, movements.getDescription());
			ps.setString(4, movements.getTypeMoney());
			ps.setString(5, movements.getOrigin());
			ps.setString(6, movements.getDestination());
			ps.setObject(7, movements.getSite());
			return ps;
		}, keyHolder);
		movements.setId(keyHolder.getKey().longValue());
		return movements.getId() != 0;
	}

	// TODO: only read the origen account from the transfer
	@Override
	public Optional<Movements> read(String id) {
		String sql = "SELECT * FROM Account WHERE Origin = ?";
		Movements a = jdbc.queryForObject(sql, this::mapearFilaAArtista, id);
		return Optional.ofNullable(a);
	}

	private Movements mapearFilaAArtista(ResultSet rs, int rowNum) throws SQLException {
		var movements = new Movements();
		movements.setAmount(rs.getDouble("Amount"));
		movements.setDayTransfer(rs.getDate("DayTransfer"));
		movements.setDescription(rs.getString("Description"));
		movements.setDestination(rs.getString("Destination"));
		movements.setId(rs.getLong("id"));
		movements.setOrigin(rs.getString("Origin"));
		movements.setSite(rs.getString("Site"));
		movements.setTypeMoney(rs.getString("TypeMoney"));
		return movements;
	}

	@Override
	public void delete(Movements t) {
		throw new MethodNotFoundException();
	}

	@Override
	public Collection<Movements> readAll() {
		String sql = "SELECT * FROM Movements";
		return jdbc.query(sql, this::mapearFilaAArtista);
	}

	@Override
	public boolean update(Movements objeto) {
		throw new MethodNotFoundException();
	}

	@Override
	public List<Movements> readSpecific(String id) {
		String sql = "SELECT * FROM Account WHERE Number = ? ";
		var listMovements = new ArrayList<Movements>();

		List<Map<String, Object>> rows = jdbc.queryForList(sql, id);
		for (Map rs : rows) {
			var obj = new Movements();
			obj.setAmount((double) rs.get("Amount"));
			obj.setDayTransfer((Date) rs.get("DayTransfer"));
			obj.setDescription((String) rs.get("Description"));
			obj.setDestination((String) rs.get("Destination"));
			obj.setId((long) rs.get("id"));
			obj.setOrigin((String) rs.get("Origin"));
			obj.setSite((String) rs.get("Site"));
			obj.setTypeMoney((String) rs.get("TypeMoney"));
			listMovements.add(obj);
		}
		return listMovements;
	}

	@Override
	public Optional<Movements> getSpecialParam(String id) {
		throw new MethodNotFoundException();
	}

}
