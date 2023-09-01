package org.example.jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import org.example.jdbc.dao.ReservaDAO;
import org.example.jdbc.factory.ConnectionFactory;
import org.example.jdbc.model.Reserva;

public class ReservasController {
    private final ReservaDAO reservaDAO;

    public ReservasController() {
        Connection connection = new ConnectionFactory().recuperarConexion();
        this.reservaDAO = new ReservaDAO(connection);
    }

    public void guardar(Reserva reserva) {
        this.reservaDAO.guardar(reserva);
    }

    public List<Reserva> buscar() {
        return this.reservaDAO.buscar();
    }

    public List<Reserva> buscarId(String id) {
        return this.reservaDAO.buscarId(id);
    }

    public void actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
        this.reservaDAO.Actualizar(fechaE, fechaS, valor, formaPago, id);
    }

    public void Eliminar(Integer id) {
        this.reservaDAO.Eliminar(id);
    }
}
