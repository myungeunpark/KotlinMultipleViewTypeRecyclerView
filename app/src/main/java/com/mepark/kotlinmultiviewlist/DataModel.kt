package com.mepark.kotlinmultiviewlist

sealed class DataModel{

    data class Movie(val title: String,
                     val des: String,
                     val img : String)
        : DataModel()


    data class Director(val name: String,
                        val des: String,
                        val photo : String)
        : DataModel()

    data class Header(val name : String)
        : DataModel()
}
