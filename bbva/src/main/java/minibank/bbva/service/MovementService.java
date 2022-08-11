package minibank.bbva.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import minibank.bbva.impl.ServiceImpl;
import minibank.bbva.impl.inter.ResultChange;
import minibank.bbva.model.dao.AccountDAO;
import minibank.bbva.model.dao.ClientDAO;
import minibank.bbva.model.dao.MovementsDAO;
import minibank.bbva.model.entitys.Account;
import minibank.bbva.model.entitys.Client;
import minibank.bbva.model.entitys.Movements;
import minibank.bbva.model.entitys.Sells;
import minibank.bbva.model.entitys.TransferCredit;
import minibank.bbva.model.entitys.TransferDebit;

@Component
public class MovementService {

	@Resource(name = "movimientoDAO")
	private MovementsDAO movementsDAO;

	@Resource(name = "clienteDAO")
	private ClientDAO clientDAO;

	@Resource(name = "cuentaDAO")
	private AccountDAO accountDAO;

	@Autowired
	ServiceImpl serviceImpl;

	@Transactional
	public void transferir(Long idOrigen, Long idDestino, Double monto) {

		Account origen = accountDAO.read(idOrigen);
		Account destino = accountDAO.read(idDestino);

		if (monto <= 0) {
			throw new IllegalArgumentException("Monto de transferencia debe ser mayor a CERO");
		}

		if (origen == null) {
			throw new IllegalArgumentException("Cuenta Origen inexistente");
		}

		if (destino == null) {
			throw new IllegalArgumentException("Cuenta Destino inexistente");
		}

		if ((origen.getSaldoActual() + origen.getDescubiertoAcordado()) < monto) {
			throw new IllegalArgumentException("Saldo insuficiente");
		}

		if (!cuentaAbierta(origen)) {
			throw new IllegalArgumentException("Cuenta origen cerrada");
		}

		if (!cuentaAbierta(destino)) {
			throw new IllegalArgumentException("Cuenta destino cerrada");
		}

//		if (origen.getMoneda() != destino.getMoneda()) {
//			throw new IllegalArgumentException("Cuentas de diferente moneda");
//		}

		var transferCredit = new TransferCredit();
		transferCredit.setFechayHora(LocalDateTime.now());
		transferCredit.setMonto(monto);
		transferCredit.setDescripcion("Trasnferencia Recibida");
		transferCredit.setCuentaOrigen(origen);

		var transferDebit = new TransferDebit();
		transferDebit.setFechayHora(LocalDateTime.now());
		transferDebit.setMonto(monto);
		transferDebit.setDescripcion("Trasnferencia Realizada");
		transferDebit.setCuentaDestino(destino);

		origen.agregarMovimiento(transferDebit);
		destino.agregarMovimiento(transferCredit);

		accountDAO.update(origen);
		accountDAO.update(destino);
	}

	@Transactional
	public void vender(Long idCliente, Long idOrigen, Long idDestino, Double monto) {

		Client cliente = clientDAO.read(idCliente);
		Account origen = accountDAO.read(idOrigen);
		Account destino = accountDAO.read(idDestino);

		if (monto <= 0) {
			throw new IllegalArgumentException("Monto de venta debe ser mayor a CERO");
		}

		if (cliente == null) {
			throw new IllegalArgumentException("Cliente inexistente");
		}

		if (origen == null) {
			throw new IllegalArgumentException("Cuenta Origen inexistente");
		}

		if (destino == null) {
			throw new IllegalArgumentException("Cuenta Destino inexistente");
		}

		if ((origen.getSaldoActual() + origen.getDescubiertoAcordado()) < monto) {
			throw new IllegalArgumentException("Saldo insuficiente");
		}

		if (!cuentaAbierta(origen)) {
			throw new IllegalArgumentException("Cuenta origen cerrada");
		}

		if (!cuentaAbierta(destino)) {
			throw new IllegalArgumentException("Cuenta destino cerrada");
		}

//		ResultChange resultadoCambio = (ResultChange) serviceImpl.change(origen.getMoneda(), destino.getMoneda(),
//				monto);

		var sellDebit = new Sells();
		sellDebit.setFechayHora(LocalDateTime.now());
		sellDebit.setMonto(monto);
		sellDebit.setDescripcion("Debito venta de moneda");
		sellDebit.setCotizacion(((ResultChange) serviceImpl).getTasa());
		sellDebit.setComision(0.0D);

		var sellCredit = new Sells();
		sellCredit.setFechayHora(LocalDateTime.now());
		sellCredit.setMonto(((ResultChange) serviceImpl).getResultado());
		sellCredit.setDescripcion("Credito Venta de moneda");
		sellCredit.setCotizacion(((ResultChange) serviceImpl).getTasa());
		sellCredit.setComision(0.0D);

		origen.agregarMovimiento(sellDebit);
		destino.agregarMovimiento(sellCredit);

		movementsDAO.create(sellDebit);
		movementsDAO.create(sellCredit);

		accountDAO.update(origen);
		accountDAO.update(destino);

	}

	private boolean cuentaAbierta(Account account) {
		return account.getFechaCierre() == null;
	}

	public List<Movements> getMovementsFromAccountId(Long id) {
		return (List<Movements>) movementsDAO.getMovimientosPorCuenta(id);
	}

}
