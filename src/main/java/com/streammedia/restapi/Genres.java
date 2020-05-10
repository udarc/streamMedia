package com.streammedia.restapi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.annotation.Generated;

/**
 * The type Genres.
 */
@Generated("com.robohorse.robopojogenerator")
@EqualsAndHashCode
public class Genres{

	@JsonProperty("genres")
	private List<GenresItem> genres;

    /**
     * Set genres.
     *
     * @param genres the genres
     */
    public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

    /**
     * Get genres list.
     *
     * @return the list
     */
    public List<GenresItem> getGenres(){
		return genres;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
 	public String toString(){
		return 
			"Genres{" + 
			"genres = '" + genres + '\'' + 
			"}";
		}
}