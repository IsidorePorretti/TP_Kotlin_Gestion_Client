package fr.epf.min1.gcIsidore.data


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import fr.epf.min1.gcIsidore.model.Client
import fr.epf.min1.gcIsidore.model.Gender


@Database(entities = [Client::class], version = 1)
@TypeConverters(GenderConverter::class)
abstract class ClientDatabase : RoomDatabase(){
    abstract fun getClientDAO() : ClientDAO
}

class GenderConverter {
    @TypeConverter fun toGender(value: String) = Gender.valueOf(value)
    @TypeConverter fun toString(value: Gender) = value.name

}