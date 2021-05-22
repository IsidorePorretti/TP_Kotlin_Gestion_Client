package fr.epf.min1.gcIsidore.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.epf.min1.gcIsidore.model.Client

@Dao
interface ClientDAO {

    @Query("select * from clients")
    suspend fun getAllClients() : List<Client>

    @Insert
    suspend fun addClient(client: Client)

}