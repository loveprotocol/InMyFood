package com.yes.inmyfood.util

import org.joda.time.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


/**
 * Java의 Date, Calendar 클래스의 불편함을 해소하기 위해 나온 Joda-time 라이브러리의 기능을 모아 놓은 클래스
 * Joda-time과 비슷하게 만든 Java-time이 API 26부터 제공되고 있으나 현재 앱의 min API가 23이므로 사용할 수 없음
 */
class JodaTimeHelper {
    companion object {
        @JvmField val now : LocalDate = LocalDate.now()

        @JvmStatic fun getCurrentTimeStamp(): Long {
            return DateTimeUtils.currentTimeMillis()
        }

        /**
         * "yyyy/MM/dd HH:mm:ss" pattern의 날짜시간 문자열을 타임스탬프로 변환하는 함수
         * @param targetTimeString 타임스탬프로 변환하고자 하는 시간 문자열
         * @return Long 타입의 Timestamp
         */
        fun convertTimeStamp(targetTimeString: String) :Long {
            val formatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")
            return formatter.parseDateTime(targetTimeString).millis
        }

        @JvmStatic fun timeStampAtStartOfThisWeek() : Long {

            val dateMondayOfThisWeeks = now.withDayOfWeek(DateTimeConstants.MONDAY)
            val dateTimeMondayOfThisWeeks = dateMondayOfThisWeeks.toDateTimeAtStartOfDay()

            return dateTimeMondayOfThisWeeks.millis
        }

        @JvmStatic fun dayOfWeekFrom(targetTime: Long) : Int {
            return DateTime(targetTime).dayOfWeek
        }

        @JvmStatic fun isPassOneWeekFrom(lastWithDrawDate: Long) : Boolean
        {
            val emptyDate : Long = 0
            if (lastWithDrawDate == emptyDate)
                return true

            // 현재로부터 일주일 전 날짜 계산
            val now = LocalDateTime.now()
            val oneWeekAgo = now.minusWeeks(1)
            val strOneWeekAgo = oneWeekAgo.toString("yyyy-MM-dd")

            val strLastDate = DateTime(lastWithDrawDate).toString("yyyy-MM-dd")
            val compare = strLastDate.compareTo(strOneWeekAgo)

            return (compare <= 0)
        }

        @JvmStatic fun convertTimeStampToDateString(targetTime: Long?, datePattern: String?) : String {
            val timeStamp = targetTime ?: getCurrentTimeStamp()

            return if (timeStamp < 10000000000) {
                DateTime(timeStamp * 1000).toString(datePattern)
            } else {
                DateTime(timeStamp).toString(datePattern)
            }
        }

        @JvmStatic fun convertMillsToTimeString(targetTime: Long, timePattern: String?) : String {
            return DateTime(targetTime).toString(timePattern)
        }

        @JvmStatic fun getDday(targetTime:Long) : Int {
            val betweenDay = Days.daysBetween(now, LocalDate(targetTime))
            return betweenDay.days
        }

        @JvmStatic fun getAge(birth:String) : Int {
            val birthDate = LocalDate(birth.substring(0, 4).toInt(), birth.substring(4, 6).toInt(), birth.substring(6).toInt())
            return Years.yearsBetween(birthDate,
                now
            ).years
        }

        /**
         * targetTime - minusDays
         * @param targetTime 특정 일수를 빼고자 하는 대상 TimeStamp
         * @param minusDays 빼고자 하는 특정 일수
         * @return 연산된 TimeStamp
         */
        @JvmStatic fun minusDay(targetTime:Long, minusDays:Int) : Long {
            return DateTime(targetTime).minusDays(minusDays).millis
        }
    }
}