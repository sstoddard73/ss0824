import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.Month;

class DayCounter
{
    // Data fields.

    private int numWeekdays;
    private int numWeekendDays;
    private int numHolidays;

    // Constructors.

    public DayCounter() {
        numWeekdays = 0;
        numWeekendDays = 0;
        numHolidays = 0;
    }

    public DayCounter(int numWeekdays, int numWeekendDays, int numHolidays) {
        this.numWeekdays = numWeekdays;
        this.numWeekendDays = numWeekendDays;
        this.numHolidays = numHolidays;
    }

    /**
     * Takes a start date and a number of days and returns
     * a DayCounts object that contains the total of
     * all the various types of days in the range.  The
     * start date itself is not included in the counts, but
     * the end date is.
     * 
     * @param startDay the LocalDate object representing the start date
     * @param numDays  the number of days to count
     * @return the DayCounts object representing the counts of day types
     */
    public void countDays(LocalDate startDay, int numDays) {
        // Iterate over the date range.
        LocalDate date = startDay;
        for (int i = 0; i < numDays; i++) {
            // Bump up the day.  We do this before counting, not after, because we don't want to count
            // the start date, but we do want to count the end date.
            date = date.plusDays(1);

            // Get our day counter object to count this day in whatever category of day it determines.
            countDay(date);            
        }
    }

    /**
     * Takes a date and increments the appropriate day type counter
     * based on what kind of a day the given date is.  Only one type of
     * day will be counted.  If it's a holiday, the holiday counter will
     * be incremented; otherwise, either the weekend day or weekday
     * counter will be incremented.
     * @param date the date in question
     */
    public void countDay(LocalDate date) {
        // Is this a holiday?  If so, don't bother to do the weekend vs. weekday check.
        if (isHoliday(date)) {
            numHolidays++;
        } else if (isWeekendDay(date)) {
            numWeekendDays++;
        } else {
            numWeekdays++;
        }
    }

    /**
     * Checks to see if the given date is a weekend day or a weekday.
     * @param date
     * @return a boolean representing whether or not the given date is a weekend day
     */
    private boolean isWeekendDay(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }

    /**
     * Checks to see if the given date is a holiday.
     * Holidays are:
     *    - July 4 (or nearest weekday if it falls on a weekend day)
     *    - First Monday in September
     * @param date
     * @return a boolean representing whether or not the given date is a holiday
     */
    private boolean isHoliday(LocalDate date) {
        // This is going to be ugly code.
        // Generally I would not want to hard-code the holiday rules here, but it feels like overkill
        // to develop a generic custom holiday class for this project.  In a project designed to be
        // more scalable, I would have a Holiday class that allows you to specify the rules
        // (e.g., Nth day of week in the month or Nth day in the month; boolean for nearest weekday, etc)
        // and a "holiday calculator" class that allows the calling code to set the holidays, then check
        // date(s) against it.

        // Start checking holidays.
        // We will return true if the date lands on any holiday; otherwise we will keep
        // checking until we run out of holidays.
        DayOfWeek dow = date.getDayOfWeek();

        // See if this is the 4th of July or nearest weekday.
        // The three possibilities:
        //    - 3rd of July on a Friday
        //    - 4th of July on a weekday
        //    - 5th of July on a Monday
        if (date.getMonth() == Month.JULY) {
            if (!isWeekendDay(date) && date.getDayOfMonth() == 4) {
                return true;
            } else if (dow == DayOfWeek.FRIDAY && date.getDayOfMonth() == 3) {
                return true;
            } else if (dow == DayOfWeek.MONDAY && date.getDayOfMonth() == 5) {
                return true;
            }
        }

        // See if this is the first Monday in September.
        if (date.getMonth() == Month.SEPTEMBER && dow == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }

        // All done checking for holidays, so if we got this far it is not a holiday.
        return false;
    }

    // Getters and setters.

    public int getNumWeekdays() {
        return numWeekdays;
    }

    public void setNumWeekdays(int numWeekdays) {
        this.numWeekdays = numWeekdays;
    }

    public int getNumWeekendDays() {
        return numWeekendDays;
    }

    public void setNumWeekendDays(int numWeekendDays) {
        this.numWeekendDays = numWeekendDays;
    }

    public int getNumHolidays() {
        return numHolidays;
    }

    public void setNumHolidays(int numHolidays) {
        this.numHolidays = numHolidays;
    }
};