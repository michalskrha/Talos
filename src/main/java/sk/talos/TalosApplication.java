package sk.talos;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class TalosApplication {

	@GetMapping("/")
	String home() {
		return "Spring is here!";
	}

	public static void main(String[] args) {
		SpringApplication.run(TalosApplication.class, args);
	}
}