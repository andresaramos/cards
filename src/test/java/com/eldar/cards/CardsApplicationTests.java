package com.eldar.cards;

import com.eldar.cards.exceptions.CardNotFoundException;
import com.eldar.cards.exceptions.OperationNotFoundException;
import com.eldar.cards.model.Operation;
import com.eldar.cards.model.dto.CardDTO;
import com.eldar.cards.model.dto.OperationDTO;
import com.eldar.cards.service.CardService;
import com.eldar.cards.service.OperationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.eldar.cards.model.Marca.VISA;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardsApplicationTests {

	@Autowired
	private CardService cardService;

	@Autowired
	private OperationService operationService;

	@Test
	@DisplayName("Mostrar informacion completa de una tarjeta")
	void showCardInfo() {

		CardDTO cardDTO = cardService.getCard(1L)
				.orElseThrow(CardNotFoundException::new);

		assertEquals(VISA, cardDTO.getMarca());
		assertEquals("2736498724537654", cardDTO.getNumero());
		assertEquals("Juan Perez", cardDTO.getHolder());
		assertEquals("19/10/2025", cardDTO.getFechaVencimiento());
		assertEquals(225.0, cardDTO.getTasa());

	}

	@Test
	@DisplayName("Verifica si una operacion es valida o no en base al importe de la misma")
	void validarOperation() {
		Operation validOper = operationService.getOperation(1L)
				.orElseThrow(OperationNotFoundException::new);

		Operation invalidOper = operationService.getOperation(2L)
				.orElseThrow(OperationNotFoundException::new);

		assertTrue(operationService.isValid(validOper));
		assertFalse(operationService.isValid(invalidOper));
	}

	@Test
	@DisplayName("Verifica si una tarjeta es valida o no para operar en base a su fecha de vencimiento")
	void validarCard() {
		CardDTO validCar = cardService.getCard(1L)
				.orElseThrow(CardNotFoundException::new);

		CardDTO invalidCar = cardService.getCard(2L)
				.orElseThrow(CardNotFoundException::new);

		assertTrue(cardService.isValid(validCar));
		assertFalse(cardService.isValid(invalidCar));
	}

	@Test
	@DisplayName("Compara que dos tarjetas sean iguales")
	void compareCards() {

		CardDTO cardDTO1 = cardService.getCard(1L)
				.orElseThrow(CardNotFoundException::new);

		CardDTO cardDTO2 = cardService.getCard(2L)
				.orElseThrow(CardNotFoundException::new);

		CardDTO cardDTO3 = CardDTO.builder()
								.numero(cardDTO1.getNumero())
								.build();

		assertTrue(cardService.equals(cardDTO1, cardDTO3));

		assertFalse(cardService.equals(cardDTO1, cardDTO2));

	}

	@Test
	@DisplayName("Obtener TASA, MARCA e IMPORTE de una operacion")
	void obtenerInfoExtendida() {
		OperationDTO operDTO = operationService.getOperationExtendedInfo(1L)
									.orElseThrow(OperationNotFoundException::new);

		assertEquals(VISA, operDTO.getCard().getMarca());
		assertEquals(225.0, operDTO.getCard().getTasa());
		assertEquals(750, operDTO.getImporte());
	}
}
