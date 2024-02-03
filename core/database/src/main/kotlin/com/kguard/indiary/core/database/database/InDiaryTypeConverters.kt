package com.kguard.indiary.core.database.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson


@ProvidedTypeConverter
class InDiaryTypeConverters {
    @TypeConverter
    fun listToJson(value: List<String?>?) : String?{
        return Gson().toJson(value)
    }
    @TypeConverter
    fun jsonToList(value: String?) : List<String?>? {
        return Gson().fromJson(value,Array<String?>::class.java)?.toList()
    }
}