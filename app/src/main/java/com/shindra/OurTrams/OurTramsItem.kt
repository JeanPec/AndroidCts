package com.shindra.OurTrams

/* Constructors:
    -Primary constructor via Header: it's the method implemented here: attributes are in the class
    header, it allows only to initialize properties: you can't add any logic in it.
        val = read-only property
        var = read-write property

    -Primary constructor in class: attribute have to be declared in class and can be initialized
    right after:
    class OurTramsItem(tramLineLetter: String) {
    private val mTramLineLetter = tramLineLetter}

    -Primary constructor via Init: allows to add some logic into blocks, here it'll be:
        class OurTramsItem(tramLineLetter: String) {
            // Attribute
            val mTramLineLetter: String

            // Initialize and add some logic
            init {
                mTramLineLetter = tramLineLetter
                println("Object initialization")
            }}

    -> Possibility to add default values
    -> A "secondary constructor" exists: used when multiple constructors have to be initialized
         using the "constructor" keyword.
 */

class OurTramsItem(private val mTramLineLetter: String) {

    // Methods
    fun getTramLineLetter(): String { return mTramLineLetter }
}