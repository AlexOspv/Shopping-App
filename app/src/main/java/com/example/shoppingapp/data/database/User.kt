package com.example.shoppingapp.data.database

class User {
    var name: String? = null
    var email: String? = null
    var id: String? = null
    var avatarMockUpResource = 0

    constructor() {}

    constructor(name: String?, email: String?, id: String?, avatarMockUpResource: Int) {
        this.name = name
        this.email = email
        this.id = id
        this.avatarMockUpResource = avatarMockUpResource
    }
}