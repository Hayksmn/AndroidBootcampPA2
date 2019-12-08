package com.self.admin.bootcamp

data class Player(
    val name: String,
    var points: Int = 0
){
    override fun toString(): String {
        return "$name: $points"
    }
}