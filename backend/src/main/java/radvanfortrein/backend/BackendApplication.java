 package radvanfortrein.backend;

import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import radvanfortrein.backend.schedule.*;

@SpringBootApplication
public class BackendApplication {
	
	static Timer timer = new Timer();
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		timer.schedule(new Updater(), 0, 1000*60);
	}

}
