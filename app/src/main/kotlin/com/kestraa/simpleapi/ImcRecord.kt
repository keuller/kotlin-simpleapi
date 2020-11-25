package com.kestraa.simpleapi

data class ImcRecord(val min: Float, val max: Float, val text: String)

object ImcTable {

    fun data(): List<ImcRecord> =
        listOf(
            ImcRecord(10.0f, 18.4f, "Baixo Peso"),
            ImcRecord(18.5f, 24.9f, "Peso Normal"),
            ImcRecord(25.0f, 29.9f, "Excesso de Peso"),
            ImcRecord(30.0f, 34.9f, "Obesidade Classe 1"),
            ImcRecord(35.0f, 39.9f, "Obesidade Classe 2"),
            ImcRecord(40.0f, 59.9f, "Obesidade Classe 3 - Ta morrendo, vai se cuidar!")
        )

}
