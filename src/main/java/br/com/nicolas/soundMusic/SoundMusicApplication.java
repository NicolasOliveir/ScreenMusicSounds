package br.com.nicolas.soundMusic;

import br.com.nicolas.soundMusic.principal.Principal;
import br.com.nicolas.soundMusic.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoundMusicApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(SoundMusicApplication.class, args);}

	@Autowired
	ArtistaRepository repository;

	@Override
	public void run(String... args) {
		Principal principal = new Principal(repository);
		principal.exibeMenu();
	}
}
