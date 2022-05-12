package com.alea.pokemon;
import com.fasterxml.jackson.annotation.JsonInclude;
//evitar que pinta string vacios o nulos
//los integers siempre los pintara minimo con un valor 0 si no tienen contenido
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonEntity{
    private String pokemonName;
    private String pokemonUrl;
    private int altura;
    private int peso;
    private int experiencia_base;
    
	public PokemonEntity() {

    }
    
    public String getPokemonName() {
        return pokemonName;
    }
    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }
    public String getPokemonUrl() {
        return pokemonUrl;
    }
    public void setPokemonUrl(String pokemonUrl) {
        this.pokemonUrl = pokemonUrl;
    }

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getExperiencia_base() {
		return experiencia_base;
	}

	public void setExperiencia_base(int experiencia_base) {
		this.experiencia_base = experiencia_base;
	}
}
