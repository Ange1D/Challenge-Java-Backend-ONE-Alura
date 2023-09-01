package org.example.views;

import org.example.jdbc.controller.HuespedesController;
import org.example.jdbc.controller.ReservasController;
import org.example.jdbc.model.Huesped;
import org.example.jdbc.model.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

public class Busqueda extends JFrame {

    private final JPanel contentPane;
    private final JTextField txtBuscar;
    private final JTable tbHuespedes;
    private final JTable tbReservas;
    private final DefaultTableModel modelo;
    private final DefaultTableModel modeloHuesped;
    private final JLabel labelAtras;
    private final JLabel labelExit;
    int xMouse, yMouse;

    private final ReservasController reservaController;
    private final HuespedesController huespedesController;

    String reserva;
    String huesped;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        this.reservaController = new ReservasController();
        this.huespedesController = new HuespedesController();

        //setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE B\u00DASQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);




        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        //panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        panel.addTab("Reservas", new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/reservado.png"))), scroll_table, null);
        scroll_table.setVisible(true);

        LlenarTablaReservas();

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("N\u00FAmero de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("N\u00FAmero de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        //panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        panel.addTab("Hu\u00E9spedes", new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/pessoas.png"))), scroll_tableHuespedes, null);

        scroll_tableHuespedes.setVisible(true);

        LlenarTablaHuespedes();

        JLabel lblNewLabel_2 = new JLabel("");
        //lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/reservado.png"))));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) { //El usuario pasa el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) { //El usuario quitarel mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarTabla();
                if (txtBuscar.getText().equals("")) {
                    LlenarTablaHuespedes();
                    LlenarTablaReservas();
                } else {
                    LlenarTablaReservasId();
                    LlenarTablaHuespedesId();
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaReservas = tbReservas.getSelectedRow();
                int filaHuespedes = tbHuespedes.getSelectedRow();

                if (filaReservas >= 0) {
                    ActualizarReserva();
                    limpiarTabla();
                    LlenarTablaReservas();
                    LlenarTablaHuespedes();
                }
                else if (filaHuespedes >= 0) {
                    ActualizarHuesped();
                    limpiarTabla();
                    LlenarTablaHuespedes();
                    LlenarTablaReservas();
                }
            }
        });
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaReservas = tbReservas.getSelectedRow();
                int filaHuespedes = tbHuespedes.getSelectedRow();

                if (filaReservas >= 0) {

                    reserva = tbReservas.getValueAt(filaReservas, 0).toString();
                    int confirmar = JOptionPane.showConfirmDialog(null, "\u00BFDesea Eliminar los datos?");

                    if(confirmar == JOptionPane.YES_OPTION){

                        String valor = tbReservas.getValueAt(filaReservas, 0).toString();
                        reservaController.Eliminar(Integer.valueOf(valor));
                        JOptionPane.showMessageDialog(contentPane, "Registro Eliminado");
                        limpiarTabla();
                        LlenarTablaReservas();
                        LlenarTablaHuespedes();
                    }
                }

                else if (filaHuespedes >= 0) {

                    huesped = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
                    int confirmarh = JOptionPane.showConfirmDialog(null, "\u00BFDesea Eliminar los datos?");

                    if(confirmarh == JOptionPane.YES_OPTION){

                        String valor = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
                        huespedesController.Eliminar(Integer.valueOf(valor));
                        JOptionPane.showMessageDialog(contentPane, "Registro Eliminado");
                        limpiarTabla();
                        LlenarTablaHuespedes();
                        LlenarTablaReservas();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Error fila no seleccionada, por favor realice una busqueda y seleccione una fila para eliminar");
                }
            }
        });
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
    }

    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }


    private List<Reserva> BuscarReservas() {
        return this.reservaController.buscar();
    }

    private List<Huesped> BuscarHuespedes() {
        return this.huespedesController.listarHuespedes();
    }

    private void LlenarTablaReservas() {

        // Llenar tabla
        List<Reserva> reserva = BuscarReservas();
        try {
            for (Reserva reservas : reserva) {
                modelo.addRow(new Object[] { reservas.getId(), reservas.getfechaE(), reservas.getfechaS(), reservas.getvalor(), reservas.getformaPago() });
            }
        } catch (Exception e) {
            throw e;
        }
    }


    private void LlenarTablaHuespedes() {
        //Llenar Tabla
        List<Huesped> huesped = BuscarHuespedes();
        try {
            for (Huesped huespedes : huesped) {
                modeloHuesped.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(), huespedes.getFechaNacimiento(), huespedes.getNacionalidad(), huespedes.getTelefono(), huespedes.getIdReserva() });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void limpiarTabla() {
        ((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
        ((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
    }

    private void LlenarTablaReservasId() {

        // Llenar tabla
        List<Reserva> reserva = BuscarReservasId();
        try {
            for (Reserva reservas : reserva) {
                modelo.addRow(new Object[] { reservas.getId(), reservas.getfechaE(), reservas.getfechaS(), reservas.getvalor(), reservas.getformaPago() });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void LlenarTablaHuespedesId() {
        //Llenar Tabla
        List<Huesped> huesped = BuscarHuespedesId();
        try {
            for (Huesped huespedes : huesped) {
                modeloHuesped.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(), huespedes.getFechaNacimiento(), huespedes.getNacionalidad(), huespedes.getTelefono(), huespedes.getIdReserva() });
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private List<Reserva> BuscarReservasId() {
        return this.reservaController.buscarId(txtBuscar.getText());
    }

    private List<Huesped> BuscarHuespedesId() {
        return this.huespedesController.listarHuespedesId(txtBuscar.getText());
    }

    private void ActualizarReserva() {
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {

                    Date fechaE = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
                    Date fechaS = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
                    String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
                    String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                    this.reservaController.actualizar(fechaE,fechaS, valor, formaPago, id);
                    JOptionPane.showMessageDialog(this, String.format("Registro modificado con \u00E9xito"));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));

    }

    private void ActualizarHuesped() {
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(filaHuesped -> {

                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
                    String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
                    Date fechaN = Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
                    String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
                    String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
                    Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    this.huespedesController.actualizar(nombre,apellido,fechaN, nacionalidad, telefono, idReserva, id);
                    JOptionPane.showMessageDialog(this, "Registro modificado con \u00E9xito");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));

    }

}
