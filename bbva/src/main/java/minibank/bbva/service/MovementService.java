package minibank.bbva.service;

import java.util.List;

import javax.annotation.Resource;

import minibank.bbva.model.dao.MovementsDAO;
import minibank.bbva.model.entitys.Movements;

public class MovementService {

	@Resource(name = "movimientoDAO")
	private MovementsDAO movementsDAO;

	public List<Movements> getMovementsFromAccountId(Long id) {
		return (List<Movements>) movementsDAO.getMovimientosPorCuenta(id);
	}

}
