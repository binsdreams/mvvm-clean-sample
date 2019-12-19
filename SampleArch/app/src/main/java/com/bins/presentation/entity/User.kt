package com.bins.presentation.entity


data class User(
    var id: Int = 0,
    var fname: String? = null,
    var lname: String? = null,
    var photoUrl: String? = null,
    var gender: String? = null,
    var location: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var dob: String? = null)