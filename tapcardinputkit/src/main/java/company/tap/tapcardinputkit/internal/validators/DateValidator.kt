package company.tap.tapcardinputkit.internal.validators

import android.text.TextUtils
import java.util.*

/**
 * Created by AhlaamK on 4/17/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/

/**
 * Maximum amount of years in advance that a credit card expiration date should be trusted to be
 * valid. This is mostly used if the current date is towards the end of the century and the
 * expiration date is at the start of the following one.
 *
 *
 * Ex. Current year is 2099, Expiration date is "01/01". The YY is "less than" the current year,
 * but since the difference is less than `MAXIMUM_VALID_YEAR_DIFFERENCE`, it should still
 * be trusted to be valid client-side.
 */

object DateValidator {
    private var mCalendar: Calendar = Calendar.getInstance()

    /**
     * Helper for determining whether a date is a valid credit card expiry date.
     *
     * @param month Two-digit month
     * @param year  Two or four digit year
     * @return Whether the date is a valid credit card expiry date.
     */
    fun isValidDate(month: String, year: String): Boolean {
        return isValidHelper(
            month,
            year
        )
    }

    /**
     * Is valid helper boolean.
     *
     * @param monthString the month string
     * @param yearString  the year string
     * @return the boolean
     */
    private fun isValidHelper(monthString: String, yearString: String): Boolean {
        val MAXIMUM_VALID_YEAR_DIFFERENCE = 20
        if (TextUtils.isEmpty(monthString)) {
            return false
        }
        if (TextUtils.isEmpty(yearString)) {
            return false
        }
        if (!TextUtils.isDigitsOnly(monthString) || !TextUtils.isDigitsOnly(yearString)) {
            return false
        }
        val month = monthString.toInt()
        if (month < 1 || month > 12) {
            return false
        }
        val currentYear =
            getCurrentTwoDigitYear()
        val year: Int
        val yearLength = yearString.length
        year = if (yearLength == 2) {
            yearString.toInt()
        } else if (yearLength == 4) {
            yearString.substring(2).toInt()
        } else {
            return false
        }
        if (year == currentYear && month < getCurrentMonth()) {
            return false
        }
        if (year < currentYear) {
            // account for century-overlapping in 2-digit year representations
            val adjustedYear = year + 100
            if (adjustedYear - currentYear > MAXIMUM_VALID_YEAR_DIFFERENCE) {
                return false
            }
        }
        return year <= currentYear + MAXIMUM_VALID_YEAR_DIFFERENCE
    }

    /**
     * [Calendar.MONTH] is 0-prefixed. Add `1` to align it with visualized expiration
     * dates.
     */
    private fun getCurrentMonth(): Int {
        return mCalendar?.get(Calendar.MONTH)?.plus(1)!!
    }

    /**
     * [Calendar.YEAR] is the full, 4-digit year. Take the trailing two digits to align it
     * with visualized expiration dates.
     */
    private fun getCurrentTwoDigitYear(): Int {
        mCalendar = Calendar.getInstance()
        return mCalendar?.get(Calendar.YEAR)?.rem(100)!!
    }


}