package com.kguard.indiary.util

interface PersonItemHelperInterface {
    fun onItemMove(from_position: Int, to_position:Int):Boolean
    fun onItemSwipe(position:Int)
}