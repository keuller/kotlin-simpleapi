package com.kestraa.simpleapi

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Query
import org.http4k.lens.float
import org.http4k.lens.int
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.ApacheServer
import org.http4k.server.asServer

typealias ImcRecordCondition = (value: Float) -> ((record: ImcRecord) -> Boolean)

fun main() {
    val indexHandler: HttpHandler = {
        Response(OK).body("Hello API")
    }

    val filtrarImcPorRange: ImcRecordCondition = { value ->
        { record ->
            (record.min <= value && value <= record.max)
        }
    }

    val imcHandler: HttpHandler = { request ->
        val alturaParam = Query.float().required("altura")
        val pesoParam = Query.int().required("peso")

        val altura = alturaParam(request)
        val peso = pesoParam(request)
        val imc: Float = (peso / (altura * altura))
        val texto = ImcTable.data().filter(filtrarImcPorRange(imc)).first().text

        Response(OK).body("Seu IMC $imc - $texto")
    }

    val app = routes(
        "/" bind GET to indexHandler,
        "/imc" bind GET to imcHandler
    )

    app.asServer(ApacheServer(8080)).start()
}
