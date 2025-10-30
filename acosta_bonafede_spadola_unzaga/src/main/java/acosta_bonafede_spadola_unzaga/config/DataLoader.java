package acosta_bonafede_spadola_unzaga.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import acosta_bonafede_spadola_unzaga.Entities.Asistido;
import acosta_bonafede_spadola_unzaga.Entities.Ciudad;
import acosta_bonafede_spadola_unzaga.DAO.ICiudadRepo; // Necesitas el DAO de Ciudad
import acosta_bonafede_spadola_unzaga.Service.AsistidoService; // Necesitas el Service de Asistido

/**
 * Clase que se ejecuta al inicio de la aplicación para cargar datos de prueba.
 */
@Component
public class DataLoader implements CommandLineRunner {

    // Asumimos que necesitas el DAO para Ciudad y el Service para Asistido
    @Autowired
    private ICiudadRepo ciudadDAO; 

    @Autowired
    private AsistidoService asistidoService; 

    @Override
    public void run(String... args) throws Exception {
        
        // Ejecutar solo si la base de datos está vacía para evitar duplicados
        if (ciudadDAO.count() == 0) {
            
            System.out.println("Cargando Ciudades de prueba...");
            
            // --- 1. Carga de Ciudades ---
            
            Ciudad c1 = new Ciudad();
            // c1.setId(1L); // No se establece, Spring Data JPA lo hará automáticamente
            c1.setNombre("Rosario");
            ciudadDAO.save(c1);

            Ciudad c2 = new Ciudad();
            // c2.setId(2L);
            c2.setNombre("Buenos Aires");
            ciudadDAO.save(c2);
            
            Ciudad c3 = new Ciudad();
            // c3.setId(3L);
            c3.setNombre("Córdoba");
            ciudadDAO.save(c3);
            
            System.out.println("Ciudades cargadas: " + ciudadDAO.count());
            
            // --- 2. Carga de Asistidos ---
            
            System.out.println("Cargando Asistidos de prueba...");
            
            // Asistido 1: Para probar GET, PUT, y DELETE (ID=1)
            Asistido a1 = new Asistido();
            a1.setNombre("Juan");
            a1.setApellido("Perez");
            a1.setDni(20111222L);
            a1.setDireccion("Calle 123");
            a1.setFechaNacimiento(LocalDate.of(1980, 10, 20));
            a1.setEdadRegistro(45);
            a1.setCiudad(c1); // Rosario
            asistidoService.insert(a1); // Usamos insert para que pase las validaciones

            // Asistido 2: Para probar unicidad de Nombre Completo
            Asistido a2 = new Asistido();
            a2.setNombre("Ana");
            a2.setApellido("Gomez");
            a2.setDni(30444555L);
            a2.setDireccion("Avenida Siempre Viva 4");
            a2.setFechaNacimiento(LocalDate.of(1995, 4, 1));
            a2.setEdadRegistro(30);
            a2.setCiudad(c2); // Buenos Aires
            asistidoService.insert(a2);

            // Asistido 3: Sin DNI (opcional)
            Asistido a3 = new Asistido();
            a3.setNombre("Carlos");
            a3.setApellido("Vera");
            a3.setDni(null); // DNI nulo para prueba (si tu DTO lo permite)
            a3.setDireccion("Ruta Nacional 9");
            a3.setFechaNacimiento(LocalDate.of(1975, 1, 1));
            a3.setEdadRegistro(50);
            a3.setCiudad(c3); // Córdoba
            asistidoService.insert(a3);
            
            System.out.println("Asistidos cargados.");
        }
    }
}
