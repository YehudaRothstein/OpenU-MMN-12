/**
 * @author Yehuda Rothstein - 329671333
 * @version 4.12.2024
 * This Class represents an Object of a date
 * @apiNote Submition Coresponding Code -  8062225-20441a
 */

 class Date {
    private int _day, _month,_year ;

    /**
     * Date constructor - If the given date is valid - creates a new Date object, otherwise creates the date 01/01/2000.
     * @param day the day of the month (1-31) depending on the month
     * @param month the month in the year (1-12)
     * @param year the object's year (4 digits)
     */
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            _day = day;
            _year = year;
            _month = month;
        } else {
            _day = 1;
            _month = 1;
            _year = 2024;
        }

    }


    /**
     * Date constructor - defualt constructor the sets the date to 01/01/2024
     *
     */
    public Date() {
        _day = 1;
        _month = 1;
        _year = 2024;
    }

    /**
     * Date constructor - gets the constatnts of a date object and copy them to current instance
     * @param other the date (as an object) to be copied
     */
    public Date(Date other) {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    /**
     * get the year of the date object
     * @return the year of this date
     */
    public int getDay() {
        return _day;
    }

    /**
     * get the month of the date object
     * @return the month of this date
     */
    public int getMonth() {
        return _month;
    }

    /**
     * get the year of the date object
     * @return the day of this date
     */
    public int getYear() {
        return _year;
    }

    /**
     * Sets the day (only if date remains valid)
     * @param dayToSet the new day value
     */
    public void setDay(int dayToSet) {
        if (isValidDate(dayToSet, _month, _year))
            this._day = dayToSet;
    }

    /**
     * Sets the month (only if date remains valid)
     * @param monthToSet the new month value
     */
    public void setMonth(int monthToSet) {
        if (monthToSet > 0 && monthToSet < 13)
            this._month = monthToSet;
    }

    /**
     * Sets the year (only if date remains valid)
     * @param yearToSet the new year value
     */
    public void setYear(int yearToSet) {
        if (yearToSet >= 1111 && yearToSet <= 9999)
            this._year = yearToSet;
    }

    /**
     * Checks if two dates are the same
     * @param other the date to compare this date to
     * @return true if the dates are the same
     */
    public boolean equals (Date other){
        return other.toString().equals(this.toString());
    }

    /**
     * get the amount of days between the current date and given parameters
     * @param day day to check
     * @param month month to check
     * @param year year to check
     * @return the amount of days between
     */
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);

    }

    /**
     * Checks if this date comes before another date
     * @param other date to compare this date to
     * @return true if this date is before the other date
     */
    public boolean before (Date other){
        return calculateDate(_day, _month, _year) < calculateDate(other._day, other._month, other._year);
    }

    /**
     * Checks if this date comes after another date
     * @param other date to compare this date to
     * @return true if this date is after the other date
     */
    public boolean after (Date other){
        return (!before(other) && _day != other._day && _month != other._month && _year != other._year) ;
    }

    /**
     * Calculates the difference in days between two dates
     * @param other the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference (Date other){
        return Math.abs(calculateDate(other._day, other._month, other._year) - calculateDate(_day, _month, _year));
    }

    /**
     * Returns a String that represents this date
     * @return a String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString(){
        String DAY = String.valueOf(_day);
        String MONTH = String.valueOf(_month);

        if (_day < 10)
              DAY = "0"+_day;

        if (_month < 10)
              MONTH = "0"+_month;

        return DAY+"/"+MONTH+"/"+_year;

    }

    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow(){
        int _day = getDay();
        int _month = getMonth();
        int _year = getYear();
        if ((isLeapYear(_year) && _day == 29 && _month == 2)) {
            _day = 1;
            _month = 3;
        } else if (_month == 12 && _day == 31) {
            _year++;
            _day = 1;
            _month = 1;
        } else if ((_month == 4 || _month == 6 || _month == 9 || _month == 11) && _day == 30) {
            _month++;
            _day = 1;
        }  else if ((_month == 2) && (_day == 28) && !isLeapYear(_year)){
            _day = 1;
            _month = 3;
        } else if (_day == 31) {
            _month++;
            _day = 1;
        } else{
            _day++;
        }
        return new Date(_day, _month, _year);
    }

    /**
     * check if the year is a gragorian leap year
     * @param y the year to check
     * @return true if is leap year
     */
    private boolean isLeapYear (int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    /**
     * check if the current date can accour in a grogorian calander
     * @param day day to check
     * @param month month to check
     * @param year year to check
     * @return true if the year can accour
     */
    private boolean isValidDate(int day, int month, int year){
        if (day > 0 && month >= 1 && month <= 12 && year >= 1111 && year <= 9999 && day <= 31) {
            if ((isLeapYear(year) && month == 2 && day <= 29)) {
                return true;
            } else if ((month == 2) && (day <= 28)) {
                return true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
                return true;
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 9 || month == 10 || month == 12) {
                return true;
            }
        }
        return false;
    }
}

