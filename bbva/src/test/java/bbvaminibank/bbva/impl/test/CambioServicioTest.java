package bbvaminibank.bbva.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import minibank.bbva.impl.inter.ResultChange;
import minibank.bbva.impl.inter.ServiceChange;
import minibank.bbva.model.entitys.enums.TypeMoney;

/**
 * 
 * @author Matias Castillo
 *
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/spring/contexto-jpa-test.xml")
@Transactional
class CambioServicioTest {

	@Autowired(required = true)
	ServiceChange servicioCambio;

	@BeforeEach
	public void inicioCadaTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void CalculoOK() {

		ResultChange resultadoCambio = (ResultChange) servicioCambio.change(TypeMoney.USD, TypeMoney.ARS, 1000.0);

		assertEquals(resultadoCambio.getResultado(), resultadoCambio.getTasa() * 1000.0);

	}
}
