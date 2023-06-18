package com.example.graduationproject2.base.rooms

import androidx.room.*
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

@Dao
interface PlacesDao {
    @Insert
    fun addPlace(placeWithImage: PlaceWithImage)

    @Delete
    fun deletePlace(placeWithImage: PlaceWithImage)

    @Update
    fun upDatePlace(placeWithImage: PlaceWithImage)

    @Query("SELECT * FROM PlaceWithImage")
    fun getPlace(): MutableList<PlaceWithImage>
    @Query("SELECT * FROM PlaceWithImage WHERE id=:id")
    fun getPlaceById(id: String): PlaceWithImage?
}