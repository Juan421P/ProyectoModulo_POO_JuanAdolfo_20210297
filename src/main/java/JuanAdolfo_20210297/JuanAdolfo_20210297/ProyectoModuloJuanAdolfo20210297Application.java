package JuanAdolfo_20210297.JuanAdolfo_20210297;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloJuanAdolfo20210297Application {

    // Profesor: Mire no me servía el .env y el application.properties; no me agarraba
    // Me dolía la cabeza y manejaba 0 ganas de pensar más duro porque ya la había probado
    // y ya servía pero sin las variables del env.
    // Le ruego me disculpe. Pero si funciona. Igual la rúbrica no dice nadota de usar el
    // .env ey 🥺. Lo que le digo, todo funciona menos eso. Y qué feo estar todo el rato
    // quebrándome la cabeza. Porque EN VERDAD no sé qué es lo que me hace falta, no recuerdo.

    // A pues no, ya me ayudó usted. Gracias ey disculpe que me sentía un poquito mal 😥

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
        );
        SpringApplication.run(ProyectoModuloJuanAdolfo20210297Application.class, args);
    }

}
