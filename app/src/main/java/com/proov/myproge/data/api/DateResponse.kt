// Paketi deklaratsioon. See ütleb teie programmile, kus see klass asub projekti kaustastruktuuris.
package com.proov.myproge.data.api

// "data class" on eritüüpi klass Kotlinis, mida kasutatakse tavaliselt ainult andmete hoidmiseks.
// See genereerib automaatselt mõned kasulikud meetodid, nagu "equals()", "hashCode()" ja "toString()",
// mis teeb andmeklasside töötluse lihtsamaks.
data class DateResponse(
    // See on ühe välja deklaratsioon andmeklassis. Klass "DateResponse" sisaldab ainult ühte välja "date",
    // mis on string-tüüpi. See vastab JSON-vastusele, mida me ootame API-lt "getDate()".
    val date: String
)
