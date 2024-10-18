package com.appetiser.trackcatalog.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

/**
 * Makes this date utility class injectable
 */
class DateUtil @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val LAST_VISIT_KEY = "last_visit"
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("date_prefs", Context.MODE_PRIVATE)

    var lastVisit: Date?
        get() = sharedPreferences.getString(LAST_VISIT_KEY, null)?.let { date ->
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(date)
        }
        set(value) {
            value?.let { date ->
                sharedPreferences.edit().putString(LAST_VISIT_KEY,
                    SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date)
                ).apply()
            } ?: run {
                sharedPreferences.edit().remove(LAST_VISIT_KEY).apply()
            }
        }

}