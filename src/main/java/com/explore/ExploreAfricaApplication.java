package com.explore;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.explore.domain.TourPackage;
import com.explore.services.TourPackageService;
import com.explore.services.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ExploreAfricaApplication implements CommandLineRunner { /* add object scope by adding CommandLineRunner */
	/* invoke services to the internal H2 database by injecting the two classes */
	@Autowired
	private TourPackageService tourPackageService;
	@Autowired
	private TourService tourService;
	
	public static void main(String[] args) { 
		SpringApplication.run(ExploreAfricaApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		/* Setup database before any web requests are processed */
		//Create the default tour packages
		tourPackageService.createTourPackage("BC", "Benin Cal");
		tourPackageService.createTourPackage("CC", "Cote'devor Calm");
		tourPackageService.createTourPackage("CH", "Congo Hot springs");
		tourPackageService.createTourPackage("CY", "Congo California");
		tourPackageService.createTourPackage("DS", "Burkina Faso Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kenya California");
		tourPackageService.createTourPackage("NW", "Namibia Watch");
		tourPackageService.createTourPackage("SC", "South Africa Cali");
		tourPackageService.createTourPackage("TC", "Tanzania of California");
		/* to prove they are persistent print out the attributes using the Java 8 fetch for each string */
		tourPackageService.lookup().forEach(tourPackage -> System.out.println(tourPackage));
		
		importTours().forEach(t -> tourService.createTour(
				t.title,
				t.description,
				t.blub,
				Integer.parseInt(t.price),
				t.length,
				t.bullets,
				t.keywords,
				t.packageType,
				Difficulty.valueOf(t.difficulty),
				Region.findLabel(t.region)));
		
		System.out.println("Number of tours =" + tourService.total());
	}

	/* Helper file for json */
	static class TourFromFile {
		/* attributes in the json file */
		private String packageType, title, description, blub, price, length, bullets, keywords, difficulty, region;
		
		/* open file and import @ json method and returns the objects as list */
		static List<TourFromFile> importTours() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(TourFromFile.class.getResourceAsStream("/ExploreAfrica.json"), new TypeReference<List<TourFromFile>>() {});
		}
	}
}
