package com.streammedia.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.annotation.Generated;

/**
 * The type Genres item.
 */
@Generated("com.robohorse.robopojogenerator")
@EqualsAndHashCode
public class GenresItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

    /**
     * Set name.
     *
     * @param name the name
     */
    public void setName(String name){
		this.name = name;
	}

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){
		return name;
	}

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(int id){
		this.id = id;
	}

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){
		return id;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
 	public String toString(){
		return 
			"GenresItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}