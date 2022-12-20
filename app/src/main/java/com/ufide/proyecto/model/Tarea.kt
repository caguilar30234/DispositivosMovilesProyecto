package com.ufide.proyecto.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName="tarea")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="nombreTarea")
    val nombreTarea: String,
    @ColumnInfo(name="fechaTarea")
    val fechaEntrega: String?,
    @ColumnInfo(name="descripcionTarea")
    val Description: String?,
    @ColumnInfo(name="Completada")
    val completa: Boolean?,
    @ColumnInfo(name="ruta_imagen")
    val rutaimagen: String?
) : Parcelable