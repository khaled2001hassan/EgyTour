package com.example.graduationproject2.base.rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

@Database(entities = [PlaceWithImage::class], version = 1)
abstract class MyDataBase:RoomDatabase() {
    abstract fun getPlaceDao():PlacesDao
    companion object{
        var dataBase : MyDataBase?=null
        fun getInstance (context : Context):MyDataBase{
            if (dataBase==null){
                dataBase = Room.databaseBuilder(context,MyDataBase::class.java,"My DataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dataBase!!
        }
    }
}