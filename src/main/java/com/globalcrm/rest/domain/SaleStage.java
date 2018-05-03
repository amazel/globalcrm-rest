package com.globalcrm.rest.domain;

/**
 * Created by Hugo Lezama on May - 2018
 *
 * INITIAL_CONTACT = Contacto Inicial
 * QUALIFICATION = En desarrollo
 * PRICE_QUOTE = Generando presupuesto
 * NEGOTIATION = En negociacion
 * CLOSED_WON = Venta lograda
 * CLOSED_LOST = Venta perdida
 * DEFERRED = Pospuesta
 *
 */
public enum SaleStage {
    INITIAL_CONTACT, QUALIFICATION, PRICE_QUOTE , NEGOTIATION, CLOSED_WON, CLOSED_LOST, DEFERRED
}
