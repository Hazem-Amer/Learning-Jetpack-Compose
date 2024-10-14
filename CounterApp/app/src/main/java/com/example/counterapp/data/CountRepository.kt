package com.example.counterapp.data

interface CountRepository {
    fun getCount():CountModel
}