package com.alea.pokemon;

import java.util.List;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcPokemonBaseExperience extends ActionsCommon{
	private static String BASE_EXPERIENCE  = "base_experience";
	
    @GetMapping("/experienced")
    public List<PokemonEntity> getMostExperienced() throws JSONException{
    	//tenemos todos los pokemons con su nombre y enlace
    	List<PokemonEntity> pokeList=getTotalDataApi();
    	pokeList = returnTop5(pokeList, BASE_EXPERIENCE);
    	
    	return pokeList; 
    }
   
}
