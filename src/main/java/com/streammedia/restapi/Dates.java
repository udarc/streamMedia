package com.streammedia.restapi;

/**
 * The type Dates.
 */
public class Dates{
	private String maximum;
	private String minimum;

    /**
     * Set maximum.
     *
     * @param maximum the maximum
     */
    public void setMaximum(String maximum){
		this.maximum = maximum;
	}

    /**
     * Get maximum string.
     *
     * @return the string
     */
    public String getMaximum(){
		return maximum;
	}

    /**
     * Set minimum.
     *
     * @param minimum the minimum
     */
    public void setMinimum(String minimum){
		this.minimum = minimum;
	}

    /**
     * Get minimum string.
     *
     * @return the string
     */
    public String getMinimum(){
		return minimum;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
 	public String toString(){
		return 
			"Dates{" + 
			"maximum = '" + maximum + '\'' + 
			",minimum = '" + minimum + '\'' + 
			"}";
		}
}
