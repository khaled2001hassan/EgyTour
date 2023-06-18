package com.example.graduationproject2.userUi.region.ui.base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class PlaceWithImage(
    @PrimaryKey
    @ColumnInfo
    var id: String = "",
    @ColumnInfo
    var name: String? = null,
    @ColumnInfo
    var description: String? = null,
    @ColumnInfo
    var ticket: Int? = null,
    @ColumnInfo
    var imageId: String = ""
) : Serializable