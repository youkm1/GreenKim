package com.example.greenkim

import java.io.Serializable

data class posts(
    var no:Int, var date: String, var title:String,
    var profile_pic:Int, var nickname:String,
<<<<<<< HEAD
    var contents:String, var likeCounts:String, var chatCounts:String
): Serializable
=======
    var contents:String, var likeCounts:String, var chatCounts:String,
    var liked: Boolean = false
)
>>>>>>> main
