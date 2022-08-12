package bbvaminibank.bbva.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import minibank.bbva.model.entitys.Address;

/**
 * TEST de Constructor y Validacion de atributos de Direccion de Cliente
 * 
 * @author Matias Castillo
 *
 */
public class DireccionTest {

	Address dir = new Address();
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@Test
	public void testContructorDireccionOk() {
		dir = new Address();
		dir.setNumero("numero1");
		dir.setPiso("piso1");
		dir.setCiudad("ciudad1");
		dir.setCodigoPostal("codigoPostal1");
		dir.setProvincia("provincia1");
		dir.setCalle("calle1");
		dir.setDepartamento("departamento1");

		String calle = "calle1";
		String numero = "numero1";
		String departamento = "departamento1";
		String piso = "piso1";
		String ciudad = "ciudad1";
		String codigoPostal = "codigoPostal1";
		String provincia = "provincia1";
		assertEquals(calle, dir.getCalle());
		assertEquals(numero, dir.getNumero());
		assertEquals(departamento, dir.getDepartamento());
		assertEquals(piso, dir.getPiso());
		assertEquals(ciudad, dir.getCiudad());
		assertEquals(codigoPostal, dir.getCodigoPostal());
		assertEquals(provincia, dir.getProvincia());
	}

	@Test
	public void testValidacionCamposObligatorios() {
		String calle = "";
		String numero = "";
		String departamento = "";
		String piso = "";
		String ciudad = "";
		String codigoPostal = "";
		String provincia = "";

		var dir = new Address();
		dir.setNumero(numero);
		dir.setPiso(piso);
		dir.setCiudad(ciudad);
		dir.setCodigoPostal(codigoPostal);
		dir.setProvincia(provincia);
		dir.setCalle(calle);
		dir.setDepartamento(departamento);

		Set<ConstraintViolation<Address>> violations = validator.validate(dir);
		assertTrue(!violations.isEmpty());
		assertEquals(5, violations.size());

	}

}
