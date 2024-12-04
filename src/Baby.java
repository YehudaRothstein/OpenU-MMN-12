/**
 * @author Yehuda Rothstein - 329671333
 * @version 4.12.2024
 * This Class represents an Object of a date
 * @apiNote Submition Coresponding Code -  8093161 -20441a
 */
public class Baby {
    private final String _firstName, _lastName, _id;
    private final Date _dateOfBirth;
    private Weight _currentWeight;
    private final Weight _birthWeight;

    private final String DEFUALT_ID = "0000000000";

    /**
     * Baby constructor - If the given id and birthWeightInGrams are valid -
     * creates a new Baby object with the parameters,
     * otherwise, if the id is not calid creates the Baby with id = "000000000" and all other parameters.
     * @param fName the first name of the baby.
     * @param lName the last name of the baby.
     * @param id the id of the baby (9 characters).
     * @param day the day of the baby's birth.
     * @param month the month of the baby's birth.
     * @param year the year of the baby's birth.
     * @param birthWeightInGrams the weight of the baby at birth in grams (should be minimum 1KG).
     */
    public Baby(String fName, String lName, String id,
                int day, int month, int year, int birthWeightInGrams){
        _firstName = fName;
        _lastName = lName;

        if (isValidId(id))
            _id = id;
        else
            _id = DEFUALT_ID;

        _dateOfBirth = new Date(day, month, year);
        _birthWeight = new Weight(birthWeightInGrams);
        _currentWeight = new Weight(birthWeightInGrams);
    }

    /**
     * Copy constructor
     * @param other the baby to be copied.
     */
    public Baby(Baby other){
        _id = other.getId();
        _lastName = other.getLastName();
        _firstName = other.getFirstName();
        _dateOfBirth = other.getDateOfBirth();
        _currentWeight = other.getCurrentWeight();
        _birthWeight = other.getBirthWeight();
    }

    /**
     * Gets the first name.
     * @return the first name of this baby.
     */
    public String getFirstName(){
        return _firstName;
    }

    /**
     * Gets the last name.
     * @return the last name of this baby.
     */
    public String getLastName(){
        return  _lastName;
    }

    /**
     * Gets the id.
     * @return the id of this baby.
     */
    public String getId(){
        return _id;
    }

    /**
     * Gets the date of birth.
     * @return the date of birth of this baby.
     */
    public Date getDateOfBirth(){
        return _dateOfBirth;
    }

    /**
     * Gets the birth weight.
     * @return the weight of this baby at birth.
     */
    public Weight getBirthWeight(){
        return _birthWeight;
    }

    /**
     * Gets the current weight.
     * @return the current weight of this baby.
     */
    public Weight getCurrentWeight(){
        return _currentWeight;
    }

    /**
     * Sets the current weight if the given parameter is valid.
     * @param weightToSet the new current weight.
     */
    public void setCurrentWeight(Weight weightToSet){
        if (weightToSet.getKilos() >= 1) {
            _currentWeight = new Weight(weightToSet);
        }
    }

    /**
     * Returns a String that represents this baby.
     * @return a String that represents this baby.
     */
    public String toString(){
        return "Name: " + _firstName + " " + _lastName  + "\nId: " + _id + "\nDate of Birth: " + _dateOfBirth.toString() +
                "\nBirth Weight: " + getBirthWeight().toString() + "\nCurrent Weight: " + _currentWeight.toString() + "\n";
    }

    /**
     * checks if the id is valid
     * @param _id the id to check
     * @return true if the ID is valid (9 characters)
     */
    private boolean isValidId(String _id){
        return _id.length() == 9;
    }

    /**
     * Checks if two babies are the same.
     * Two babies are consider the same if they have the same first and last name, same ID and similar date of birth.
     * @param other the baby to compare this baby with.
     * @return true if the babies are the same.
     */
    public boolean equals (Baby other){
        return _id.equals(other.getId()) &&
                _firstName.equals(other.getFirstName()) &&
                _lastName.equals(other.getLastName()) &&
                _dateOfBirth.equals(other.getDateOfBirth());
    }

    /**
     * Checks if two babies are twins.
     * Twins should have similar last name, different first name,
     * different ID and similar date of birth or difference of one day between the date of birth of the current baby and the other.
     * @param other the baby to compare this baby with.
     * @return true if the babies are twins.
     */
    public boolean areTwins (Baby other){
        return  !_firstName.equals(other.getFirstName()) && _lastName.equals(other.getLastName())
                && !_id.equals(other.getId()) && (_dateOfBirth.difference(other.getDateOfBirth()) <= 1);
    }

    /**
     * Checks if the weight of this baby is heavier than the weight of another baby.
     * @param other baby to compare this baby's weight to.
     * @return true if the weight of this baby is heavier than the weight of the other baby.
     */
    public boolean heavier (Baby other){
        return (_currentWeight.heavier(other.getCurrentWeight()));
    }

    /**
     * Updates the baby's current weight by adding the additional grams.
     * If the sum of the current weight and the additional grams is negative, the baby's current weight will remain unchanged.
     * @param grams the number of grams to add to he baby's current weight (can be negative).
     */
    public void updateCurrentWeight (int grams){
        if (_currentWeight.add(grams).getKilos() >= 1)
            _currentWeight = _currentWeight.add(grams);
        }

    /**
     * Checks if the date of birth of this baby is before than the date of birth of another baby.
     * @param other baby to compare this baby's date of birth to.
     * @return true if the date of birth of this baby is before the date of birth of the other baby.
     */
    public boolean older (Baby other){
        return (other._dateOfBirth.before(_dateOfBirth));
    }

    /**
     * Checks if the current weight of this baby is within the valid range.
     * @param numOfDays of days passed since the baby was born.
     * @return 1- If the date given as a parameter is less than a week or more than a year.
     * 2- If the progress is not correct according to the rules.
     * 3- If the progress is correct according to the rules.
     */
    public int isWeightInValidRange (int numOfDays){
         final double BIRTH_WEIGHT_TO_DOUBLE = getBirthWeight().getGrams()+ getBirthWeight().getKilos()*1000;
         final double CURRENT_WEIGHT_TO_DOUBLE = (getCurrentWeight().getGrams() + getCurrentWeight().getKilos()*1000);
         final double EXPECTED_WEIGTH_AFTER_WEEK = (BIRTH_WEIGHT_TO_DOUBLE - (BIRTH_WEIGHT_TO_DOUBLE / 10));
         final double EXPECTED_WEIGHT_AFETR_2_MONTHS = 1800 + EXPECTED_WEIGTH_AFTER_WEEK;
         final double EXPECTED_WEIGHT_AFETR_4_MONTHS = 1500 + EXPECTED_WEIGHT_AFETR_2_MONTHS;
        final double EXPECTED_WEIGHT_AFETR_8_MONTHS = 1920 + EXPECTED_WEIGHT_AFETR_4_MONTHS;


        if (numOfDays > 365 || numOfDays < 0){
            return 1;
        }else if ((numOfDays < 7) &&
                _birthWeight.getKilos() - BIRTH_WEIGHT_TO_DOUBLE / ((double) numOfDays / 7 * 10)
                        < CURRENT_WEIGHT_TO_DOUBLE){
            return 3;
        } else if ((numOfDays >= 8 && numOfDays <= 60) &&
                (30 * (numOfDays - 7) + EXPECTED_WEIGTH_AFTER_WEEK) <  CURRENT_WEIGHT_TO_DOUBLE)  {
            return 3;
        } else if (numOfDays > 60 && numOfDays <= 120 &&
                EXPECTED_WEIGHT_AFETR_2_MONTHS + 25 * (numOfDays - 60) < CURRENT_WEIGHT_TO_DOUBLE){
            return 3;
        }  else if (numOfDays > 120 && numOfDays <= 240 &&
                EXPECTED_WEIGHT_AFETR_4_MONTHS + 16 * (numOfDays - 120) < CURRENT_WEIGHT_TO_DOUBLE){
            return 3;
        } else if (numOfDays > 240 &&
                EXPECTED_WEIGHT_AFETR_8_MONTHS + 8 * (numOfDays - 240) < CURRENT_WEIGHT_TO_DOUBLE){
            return 3;
        }
        return 2;
    }
}









