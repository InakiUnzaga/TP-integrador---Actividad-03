package acosta_bonafede_spadola_unzaga.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONDIMENTO") 
public class Condimento extends Ingredientes {

    public Condimento() {
        super();
    }

    public Condimento(String nombre, int calorias) {
        super(nombre, calorias);
    }
    
}
