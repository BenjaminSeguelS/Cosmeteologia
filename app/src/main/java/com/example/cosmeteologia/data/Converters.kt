package com.example.cosmeteologia.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromUserType(value: UserType): String {
        return value.name
    }

    @TypeConverter
    fun toUserType(value: String): UserType {
        return UserType.valueOf(value)
    }
}
