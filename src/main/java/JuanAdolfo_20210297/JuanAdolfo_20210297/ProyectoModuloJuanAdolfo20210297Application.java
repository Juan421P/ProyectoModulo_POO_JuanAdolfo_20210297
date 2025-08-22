package JuanAdolfo_20210297.JuanAdolfo_20210297;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloJuanAdolfo20210297Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		dotenv.get("SERVER_PORT");
		SpringApplication.run(ProyectoModuloJuanAdolfo20210297Application.class, args);
	}

}
