package com.alea.pokemon;


import org.json.JSONException;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AcPokemonHeight extends ActionsCommon{
    
	private static String HIGHEST = "height";
	
    @GetMapping("/highest")
    public List<PokemonEntity> getHighest() throws JSONException{
    	//tenemos todos los pokemons con su nombre y enlace
    	List<PokemonEntity> pokeList=getTotalDataApi();
    	pokeList = returnTop5(pokeList, HIGHEST);
    	
    	return pokeList;
    }

    
}
