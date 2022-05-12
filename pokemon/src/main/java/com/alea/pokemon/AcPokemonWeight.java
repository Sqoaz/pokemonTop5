package com.alea.pokemon;


import java.util.List;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AcPokemonWeight extends ActionsCommon {
	private static String HEAVIEST  = "weight";
	
    @GetMapping("/heaviest")
    public List<PokemonEntity> getHeaviest() throws JSONException{
    	//tenemos todos los pokemons con su nombre y enlace
    	List<PokemonEntity> pokeList=getTotalDataApi();
    	pokeList = returnTop5(pokeList, HEAVIEST);
    	
    	return pokeList;
    }
}
