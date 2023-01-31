package com.example.myapplication.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//La variable data sera piochée dans le GSON sur "data"
class MenuResult(@SerializedName("data") val data: List<Category>): Serializable {

}