/**
 * 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import modeloBBDD.Modelo;
import modeloBBDD.Periodo;
import vista.JIFPeriodos;

/**
 * Controlador del JIFPeriodos donde se controlara las acciones de los botones y
 * la gestion de cada periodo ademas de visualizar reservas y modificar mensajes
 *
 * @author <a href="mailto:amonfortp1@ieslavereda.es">Alejandro Monfort Parra
 *         </a>
 *
 */
public class controladorPeriodos implements ActionListener, MouseListener {

	JIFPeriodos JIP;
	Modelo modelo;

	DefaultTableModel dtm = new DefaultTableModel(new String[] { "Periodo", "Dia de Inicio", "Dia Final", "Hora Inicio",
			"Hora Final", "Tiempo", "Habilitado" }, 0) {

		Class[] types = new Class[] { java.lang.String.class, java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class };

		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
	};

	/**
	 * @param periodos JInternalFrame del administrador
	 * @param modelo
	 */
	public controladorPeriodos(JIFPeriodos periodos, Modelo modelo) {
		super();
		this.JIP = periodos;
		this.modelo = modelo;
		iniciar();
	}

	private void iniciar() {
		JIP.btnActualizar.setActionCommand("actualizar mensaje");
		JIP.btnCancelar.setActionCommand("cerrar");
		JIP.btnCrear.setActionCommand("crear periodo");
		JIP.comboBoxTiempo.setActionCommand("calcular tiempos");
		JIP.comboBoxCurso2.setActionCommand("mostrar periodos");
		JIP.comboBoxPeriodos.setActionCommand("mostrar reservas");
		JIP.comboBoxMensajes.setActionCommand("mostrar mensaje");
		JIP.btnEnabled.setActionCommand("activar periodo");

		JIP.btnActualizar.addActionListener(this);
		JIP.btnCancelar.addActionListener(this);
		JIP.btnCrear.addActionListener(this);
		JIP.comboBoxTiempo.addActionListener(this);
		JIP.comboBoxCurso2.addActionListener(this);
		JIP.comboBoxPeriodos.addActionListener(this);
		JIP.comboBoxMensajes.addActionListener(this);
		JIP.btnEnabled.addActionListener(this);

		JIP.comboBoxTiempo.addMouseListener(this);

		cargarCursos();
		cargarTiempo();
		cargarPeriodos();
	}

	/**
	 * 
	 * Metodo para hacer visible el JinternalFrame de periodos
	 */
	public void start() {
		JIP.setVisible(true);
	}

	private void cargarCursos() {
		ArrayList<Integer> cursos = modelo.obtenerCursos();

		for (int i = 0; i < cursos.size(); i++) {
			JIP.comboBoxCurso1.addItem(cursos.get(i));
			JIP.comboBoxCurso2.addItem(cursos.get(i));
		}
	}

	private void cargarTiempo() {
		JIP.comboBoxTiempo.addItem(LocalTime.parse("00:30"));
		JIP.comboBoxTiempo.addItem(LocalTime.parse("00:20"));
		JIP.comboBoxTiempo.addItem(LocalTime.parse("00:10"));

		calcularTiempos();
	}

	private void calcularTiempos() {
		if (JIP.comboBoxTiempo.getSelectedItem().equals(LocalTime.parse("00:30"))) {
			JIP.dateTimePickerInicio.timePicker.getSettings().generatePotentialMenuTimes(TimeIncrement.ThirtyMinutes,
					null, null);
			JIP.dateTimePickerFinal.timePicker.getSettings().generatePotentialMenuTimes(TimeIncrement.ThirtyMinutes,
					null, null);
		} else if (JIP.comboBoxTiempo.getSelectedItem().equals(LocalTime.parse("00:20"))) {
			JIP.dateTimePickerInicio.timePicker.getSettings().generatePotentialMenuTimes(TimeIncrement.TwentyMinutes,
					null, null);
			JIP.dateTimePickerFinal.timePicker.getSettings().generatePotentialMenuTimes(TimeIncrement.TwentyMinutes,
					null, null);
		} else if (JIP.comboBoxTiempo.getSelectedItem().equals(LocalTime.parse("00:10"))) {
			JIP.dateTimePickerInicio.timePicker.getSettings().generatePotentialMenuTimes(TimeIncrement.TenMinutes, null,
					null);
			JIP.dateTimePickerFinal.timePicker.getSettings().generatePotentialMenuTimes(TimeIncrement.TenMinutes, null,
					null);
		}
	}

	private void crearPeriodo() {
		LocalDate diaInicio = JIP.dateTimePickerInicio.datePicker.getDate();
		LocalDate diaFinal = JIP.dateTimePickerFinal.datePicker.getDate();
		LocalTime horaInicio = JIP.dateTimePickerInicio.timePicker.getTime();
		LocalTime horaFinal = JIP.dateTimePickerFinal.timePicker.getTime();
		LocalTime tiempo = (LocalTime) JIP.comboBoxTiempo.getSelectedItem();
		int curso = (int) JIP.comboBoxCurso1.getSelectedItem();

		if (diaInicio != null && diaFinal != null) {
			Periodo p = new Periodo(0, diaInicio, diaFinal, horaInicio, horaFinal, tiempo, curso, false);

			if (modelo.crearPeriodo(p)) {
				JOptionPane.showMessageDialog(null, "El periodo se a creado correctamente", "INFO",
						JOptionPane.INFORMATION_MESSAGE);

				Map<LocalDate, TreeSet<LocalTime>> dh = modelo.obtenerDiasHoras();
				HashSet<LocalDate> reservas = new HashSet<LocalDate>();

				for (LocalDate dia : dh.keySet()) {
					reservas.add(dia);
				}

				JIP = new JIFPeriodos(reservas);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Revisa que has seleccionado un dia de inicio y final", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarPeriodos() {
		Map<Integer, Periodo> p = modelo.obtenerPeriodos((int) JIP.comboBoxCurso2.getSelectedItem());

		if (!p.isEmpty()) {

//			dtm.addColumn("Periodo");
//			dtm.addColumn("Dia de Inicio");
//			dtm.addColumn("Dia Final");
//			dtm.addColumn("Hora Inicio");
//			dtm.addColumn("Hora Final");
//			dtm.addColumn("Tiempo");
//			dtm.addColumn("Habilitado");

			for (Integer key : p.keySet()) {
				Object datos[] = new Object[7];

				datos[0] = String.valueOf(p.get(key).getId());
				datos[1] = String.valueOf(p.get(key).getDiaInicio());
				datos[2] = String.valueOf(p.get(key).getDiaFinal());
				datos[3] = String.valueOf(p.get(key).getHoraInicio());
				datos[4] = String.valueOf(p.get(key).getHoraFinal());
				datos[5] = String.valueOf(p.get(key).getTiempo());
				datos[6] = p.get(key).isHabilitado();
				dtm.addRow(datos);
			}

			JIP.tablePeriodos.setModel(dtm);
			int anchos[] = { 1, 30, 30, 30, 30, 30, 1 };
			for (int i = 0; i < JIP.tablePeriodos.getColumnCount(); i++) {
				JIP.tablePeriodos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comand = e.getActionCommand();

		if (comand.equals("cerrar")) {
			JIP.dispose();
		} else if (comand.equals("crear periodo")) {
			crearPeriodo();
		} else if (comand.equals("calcular tiempos")) {
			calcularTiempos();
		} else if (comand.equals("activar periodo")) {

		} else if (comand.equals("mostrar periodos")) {
			cargarPeriodos();
		} else if (comand.equals("mostrar reservas")) {

		} else if (comand.equals("mostrar mensaje")) {

		} else if (comand.equals("actualizar mensaje")) {

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
