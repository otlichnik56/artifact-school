package groupIdru.hogwarts.artifactschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ArtifactSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtifactSchoolApplication.class, args);
	}

}
