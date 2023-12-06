package com.kguard.indiary.util

interface ItemHelperInterface {
    fun onItemMove(from_position: Int, to_position: Int): Boolean
    fun onItemSwipe(position: Int)
}