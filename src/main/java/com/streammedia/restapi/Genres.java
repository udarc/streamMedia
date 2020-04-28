package com.streammedia.restapi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@EqualsAndHashCode
public class Genres{

	@JsonProperty("genres")
	private List<GenresItem> genres;

	public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	@Override
 	public String toString(){
		return 
			"Genres{" + 
			"genres = '" + genres + '\'' + 
			"}";
		}
}