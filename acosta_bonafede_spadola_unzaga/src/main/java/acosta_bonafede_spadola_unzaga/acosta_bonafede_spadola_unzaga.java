package acosta_bonafede_spadola_unzaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("acosta_bonafede_spadola_unzaga.Entities") 
public class acosta_bonafede_spadola_unzaga {

	public static void main(String[] args) {
		SpringApplication.run(acosta_bonafede_spadola_unzaga.class, args);
	}

}
