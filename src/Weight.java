/**
 * @author Yehuda Rothstein - 329671333
 * @version 4.12.2024
 * This class represents a Weight object
 * @apiNote Submition Coresponding Code -  8062225-20441a
 */
public class Weight {
    private final int _kilos;
    private final int _grams;

    /**
     * Weight constructor - If the given weight is valid - creates a new Weight object, otherwise if one of the parameters in not valid initialize it to 1.
     * @param kilos the number of kilos in Weight (greater or equal to 1)
     * @param grams the number of grams in Weight (0-999)
     */
    public Weight(int kilos, int grams){
        if (isValidWeight(kilos, grams)) {
            _grams = grams;
            _kilos = kilos;
        } else {
             _grams = 0;
             _kilos = 1;
        }
    }

    /**
     * Weight constructor - Copy constructor
     * @param other the weight (as an Object) to be copied
     */
    public Weight (Weight other){
        _grams = other._grams;
        _kilos = other._kilos;
    }

    /**
     * Weight constructor - Constructor with only one parameter
     * @param totalGrams the total number of grams
     */
    public Weight(int totalGrams){
        if(isValidWeight(totalGrams/1000, totalGrams % 1000)){
            _kilos = totalGrams/1000;
            _grams = totalGrams % 1000;
        } else {
            _grams = 0;
            _kilos = 1;
        }
    }

    /**
     * Gets the grams
     * @return the number of grams for this weight
     */
    public int getGrams() {
        return _grams;
    }

    /**
     * Gets the kilos
     * @return the number of kilos for this weight
     */
    public int getKilos() {
        return _kilos;
    }

    /**
     * Checks if two weights are the same
     * @param other the weight to compare this weight to
     * @return true if the weights are the same
     */
    public boolean equals (Weight other){
        return (other.toGrams(other._kilos, other._grams) == toGrams(_kilos, _grams));
    }

    /**
     * Checks if this weight is lighter than another weight
     * @param other weight to compare this weight to
     * @return true if this weight is lighter than the other weight
     */
    public boolean lighter (Weight other){
        return (other.toGrams(other._kilos, other._grams) > toGrams(_kilos, _grams));
    }

    /**
     * Checks if this weight is heavier than another weight
     * @param other weight to compare this weight to
     * @return true if this weight is heavier than the other weight
     */
    public boolean heavier (Weight other){
        return other.lighter(this);
    }

    /**
     * Returns a String that represents this weight
     * @return a String that represents this weight in the following format: kiols.grmas(3 digits) for example: 4.07 or 3.055 or 4.005
     */
    public String toString(){
        String formattedGrams;
        if (_grams % 10 == 0) {
            formattedGrams = String.valueOf((getGrams() / 10));
          if (_grams % 100 == 0 )
            formattedGrams = String.valueOf(getGrams() / 100);
        } else {
            formattedGrams = String.valueOf(getGrams());
        }
        if (_grams == 0){
            formattedGrams = "0";
        }
        else if (_grams < 10) {
            formattedGrams = "00" + formattedGrams;
        } else if (_grams < 100) {
            formattedGrams = "0" + formattedGrams;
        }
        return (_kilos + "." + formattedGrams );
    }

    /**
     * Return a new weight with the additional grams given as parameter
     * @param grams  the additional grams to add to the new returned weight
     * @return a new weight with the additional grams given as parameter
     */
    public Weight add (int grams){
        int _kilos = getKilos();

        if (grams < 0){
            _kilos--;
            grams = 1000 + grams + getGrams();
        } else if (grams+getGrams() >= 1000){
            _kilos++;
            grams = (grams+getGrams())% 1000;
        } else
            grams = getGrams() + grams;


        if (!isValidWeight(_kilos, grams))
            return this;

        return( new Weight(_kilos, grams));
    }

    private int toGrams(int _kilos, int _grams){
        return (1000 *_kilos +  _grams);
    }

    private boolean isValidWeight(int _kilos, int _grams){
        return (_grams >= 0 && _grams < 1000 && _kilos >= 0);
    }
}
