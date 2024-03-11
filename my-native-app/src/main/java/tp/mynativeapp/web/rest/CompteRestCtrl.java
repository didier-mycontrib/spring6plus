package tp.mynativeapp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.mynativeapp.dto.CompteDto;

@RestController //@Component de type controller d'api rest
@RequestMapping(value="/rest/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	

	
	//En GET
	//http://localhost:8181/appliSpring/rest/api-bank/compte
	@GetMapping("")
	public List<CompteDto> getComptesByCriteria() {
		List<CompteDto> listeCompteDto = new ArrayList<>();
		listeCompteDto.add(new CompteDto(1L,"compteA",100.0));
		listeCompteDto.add(new CompteDto(2L,"compteB",150.0));
		listeCompteDto.add(new CompteDto(3L,"compteC",180.0));
		return listeCompteDto;
	}
	
	
	
	public CompteRestCtrl() {
	}

}
