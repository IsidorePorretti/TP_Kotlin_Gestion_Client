package fr.epf.min1.gcIsidore.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

enum class Gender {
    Man, Woman
}

@Entity(tableName = "clients")
@Parcelize
data class Client(

    @PrimaryKey(autoGenerate = true) val id: Int?,
    val lastname: String,
    val firtsname: String,
    val email: String,
    val age: Int,
    val level: String,
    val active: Boolean,
    val gender: Gender
) : Parcelable {

    companion object {
        fun all(size : Int = 30) = (1..size)
            .map {
                Client(null,
                    "nom$it",
                    "prenom$it",
                    "mail$it@epf.fr",
                    20 + it,
                    "Débutant",
                    true,
                    if (it % 3 == 0) Gender.Man else Gender.Woman
                )
            }
    }
}