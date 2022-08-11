package minibank.bbva.model.entitys;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Extraccion")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Extraction extends DepositExtraction {

}
