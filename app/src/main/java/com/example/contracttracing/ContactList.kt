package com.example.contracttracing

class ContactList {
    var contactList: MutableList<ContactModel> = mutableListOf<ContactModel>()
    private var contactModel : ContactModel

    constructor(contactModel: ContactModel){
       this.contactModel = contactModel
       contactList.add(contactModel)
    }
}