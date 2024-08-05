import java.time.LocalDate;
import java.time.DayOfWeek;

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
        
        // Is this a weekend day or a weekday?
        if (isWeekendDay(date)) {
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