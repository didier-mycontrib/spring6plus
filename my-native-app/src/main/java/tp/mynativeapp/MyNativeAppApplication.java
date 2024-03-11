package tp.mynativeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyNativeAppApplication {

	public static void main(String[] args) {
		System.out.println("This is a Basic Spring Native Application");
		SpringApplication.run(MyNativeAppApplication.class, args);
		System.out.println("http://localhost:8181/my-native-app");
	}

}
