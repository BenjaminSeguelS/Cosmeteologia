package com.example.cosmeteologia.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clientData: ClientData)

    @Query("SELECT * FROM client_forms")
    fun getAll(): Flow<List<ClientData>>
}
