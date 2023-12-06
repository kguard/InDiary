package com.kguard.indiary.util

import androidx.lifecycle.MutableLiveData

class ListLiveData<T> : MutableLiveData<ArrayList<T>>() {
    init {
        value = arrayListOf()
    }

    fun add(item: T) {
        val items = value
        items?.add(item)
        value = items
    }

    fun add(position: Int, item: T) {
        val items = value
        items?.add(position, item)
        value = items
    }

    fun addAll(itemList: ArrayList<T>) {
        val items = value
        items?.addAll(itemList)
        value = items
    }

    fun addAll(itemList: List<T>) {
        val items = value
        items?.addAll(itemList)
        value = items
    }

    fun update(itemList: List<T>) {
        val items = value
        items?.clear()
        items?.addAll(itemList)
        value = items
    }

    fun remove(item: T) {
        val items = value
        items?.remove(item)
        value = items
    }

    fun removeAt(position: Int) {
        val items = value
        items?.removeAt(position)
        value = items
    }

    fun clear() {
        val items = value
        items?.clear()
        value = items
    }

    fun size(): Int {
        return value?.size!!
    }

    fun contain(item: T): Boolean {
        return value?.contains(item)!!
    }

    fun replace(item: T) {
        val items = value
        val position = value?.indexOf(item)
        items?.removeAt(position!!)
        items?.add(position!!, item)
        value = items
    }

    fun replaceAt(item: T, position: Int) {
        val items = value
        items?.removeAt(position)
        items?.add(position, item)
        value = items
    }

    fun notifyChange() {
        val items = value
        value = items
    }

}