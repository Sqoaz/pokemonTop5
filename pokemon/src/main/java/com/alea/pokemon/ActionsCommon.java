package com.alea.pokemon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ActionsCommon {
	
	 @Autowired
	 protected RestTemplate restTemplate;
	 
	 protected final String url = "https://pokeapi.co/api/v2/pokemon/";
	 protected final String urlLimit = "https://pokeapi.co/api/v2/pokemon?limit=";
		/** 
		1. The 5 heaviest Pokémons.
		2. The 5 highest Pokémons.
		3. The 5 Pokémons with more base experience.
		*/
	 

	/**
     * Nos facilita el nombre del pokemon y su enlace con todas las características que necesitamos
     * @return List<PokemonEntity>
     * @throws JSONException
     */
    @SuppressWarnings("rawtypes")
	protected final List<PokemonEntity> getTotalDataApi() throws JSONException{
    	 final int totalPokemons = getNumTotalPokemons();
    	 Object tipos = restTemplate.getForObject(urlLimit+totalPokemons, Object.class);
    	
		@SuppressWarnings("unchecked")
		LinkedHashMap<String,List> listaPokemons = (LinkedHashMap)tipos;
    	 
    	 List<PokemonEntity> pokeList = new ArrayList<PokemonEntity>();
    	 PokemonEntity poke = new PokemonEntity();
    	 for (int i = 0; i <totalPokemons; i++) {
    		 poke = new PokemonEntity();
    		 poke.setPokemonName(((LinkedHashMap)listaPokemons.get("results").get(i)).get("name").toString());
    		 poke.setPokemonUrl(((LinkedHashMap)listaPokemons.get("results").get(i)).get("url").toString());
    		 pokeList.add(poke);
		}
    	 return pokeList;
    }
    
    @SuppressWarnings("rawtypes")
    /**
     * Devolver el total de pokemons que tiene la API
     * @return int
     * @throws JSONException
     */
   private int getNumTotalPokemons() throws JSONException{
    	Object tipos = restTemplate.getForObject(url, Object.class);//en esta llamada nos da el total de pokemons que hay
    	
    	final int totalPokemons=Integer.parseInt(((LinkedHashMap)tipos).get("count").toString());
		return totalPokemons;
   }
    
    

    
    /**
     * Devuelve el pokemon con la caracteristica que se necesita: altura, peso, habilidad, experiencia, campo, evoluciones, etc.
     * @param pokemon
     * @param caracteristica
     * @return PokemonEntity
     */
    @SuppressWarnings("rawtypes")
	protected PokemonEntity callPokemon(PokemonEntity pokemon, String caracteristica) {
    	//obtenemos toda la informacion del pokemon en concreto y la propiedad que necesitamos
    	Object tipos = restTemplate.getForObject(pokemon.getPokemonUrl(), Object.class);
    	int resultado = 0;
    	if(null!=tipos) {
    		//que no sea nulo antes de obtener la altura
    		if(null!=((LinkedHashMap)tipos).get(caracteristica)) {
    			resultado = Integer.parseInt(((LinkedHashMap)tipos).get(caracteristica).toString());
    		}
    	}
    	if(null!=caracteristica && !"".equals(caracteristica)) {
    		if(caracteristica.equals("height")) {
    			pokemon.setAltura(resultado);
    		}else if(caracteristica.equals("weight")) {
    			pokemon.setPeso(resultado);
    		}else if(caracteristica.equals("base_experience")) {
    			pokemon.setExperiencia_base(resultado);
    		}
    	}
    	return pokemon;
    }
    
    protected List<PokemonEntity> returnTop5(List<PokemonEntity> pokeList,String caracteristica){
    	PokemonEntity poke;
    	int[] top5 = {0,0,0,0,0};
    	List<PokemonEntity> topPokemonsFinales = new ArrayList<PokemonEntity>();
    	for (int x = 0; x < 5; x ++) {
    		//inicializarlo con 5 entidades
			topPokemonsFinales.add(new PokemonEntity());
		}
    	
    	for (int i = 0; i < pokeList.size(); i++) {
    		poke = new PokemonEntity();
    		//se recorreran todos los pokemons que se han recuperado del API
    		poke=callPokemon(pokeList.get(i),caracteristica);///tenemos la caracteristica
    		int feature = poke.getAltura();
    		if(caracteristica.equals("height")) {
    			feature =  poke.getAltura();
    		}else if(caracteristica.equals("weight")) {
    			feature =  poke.getPeso();
    		}else if(caracteristica.equals("base_experience")) {
    			feature =  poke.getExperiencia_base();
    		}
    		
    		for (int j = 0; j < top5.length; j++) {
                if(feature>top5[j] || feature==top5[j]){
                	//si la caracterisitica es más grande que la de la posicion actual se cambia
                    if(j<top5.length-1){
                    	//pero movemos la de la posicion actual a la siguiente (si es la ultima posicion se sustituye directamente el valor)
                    	//es decir movemos la altura una posicion hacia abajo siempre que no sea la ultima
                    	//j puede valer de 0 hasta 3, que son las posiciones de la array, excepto la ultima posicion
                    	top5[j+1]=top5[j];
                    }
                    top5[j]=feature;//la nueva caracterisitica sustituye la posicion correspondiente
                    topPokemonsFinales.add(j,poke);
                    break;
                }
            }
		}
    	pokeList = new ArrayList<PokemonEntity>();
    	for (int x = 0; x < 5; x ++) {
    		pokeList.add(topPokemonsFinales.get(x));
		}
    	
    	return pokeList;
    }
}
