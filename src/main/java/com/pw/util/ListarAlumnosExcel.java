package com.pw.util;

import com.pw.domain.Alumno;
import com.pw.servicios.AlumnoService;
import com.pw.web.ControllerInicio;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

//especificar ruta donde quedará el link para descarga
@Slf4j
@Component("index")
public class ListarAlumnosExcel extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition","attachment; filename=\"listado-alumnos.xlsx\"");
        log.info("Exportando listado general de alumnos a Excel");

        ControllerInicio controllerInicio;

        //crear hoja
        Sheet hoja = workbook.createSheet("Asistencia");
        //asignar ancho a columnas
        hoja.setColumnWidth(0, 5 * 256);
        hoja.setColumnWidth(1, 15 * 256);
        hoja.setColumnWidth(2, 50 * 256);
        hoja.setColumnWidth(3, 15 * 256);
        hoja.setColumnWidth(4, 15 * 256);

        //establecer centrado Titulos
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);

        //fila Titulo
        Row filaTitulo = hoja.createRow(0);
        Cell celda = filaTitulo.createCell(0);
        celda.setCellValue("ASISTENCIA DE ALUMNOS");
        celda.setCellStyle(style);
        hoja.addMergedRegion(new CellRangeAddress(0,1,0,4));

        //fila Fecha actual
        Row filaFecha = hoja.createRow(2);
        celda = filaFecha.createCell(2);

        //Estabkecer estilos para fecha
        CellStyle styleFecha = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        styleFecha.setAlignment(CellStyle.ALIGN_LEFT);
        styleFecha.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        celda.setCellValue(new Date());
        celda.setCellStyle(styleFecha);
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));

        //fila subtitulos
        Row filaData = hoja.createRow(3);
        String[] columnas = {"#", "CEDULA", "NOMBRE", "ASISTENCIA", "ADULAPUNTOS"};

        for(int i = 0 ; i < columnas.length; i++){
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
            celda.setCellStyle(style);
        }

        List<Alumno> listaAluEx =(List<Alumno>) model.get("alumnos");

        int numFila = 4;

        //obtener e iterar los datos de la BD
        for(Alumno alumno: listaAluEx){
            CellStyle styleData = workbook.createCellStyle();
            styleData.setAlignment(CellStyle.ALIGN_CENTER);
            filaData = hoja.createRow(numFila);

            filaData.createCell(0).setCellValue(alumno.getIdAlumnos());
            filaData.createCell(1).setCellValue(alumno.getAlCedula());
            filaData.createCell(2).setCellValue(alumno.getAlApellido() + ", " + alumno.getAlNombre());
            filaData.createCell(3).setCellValue(alumno.getAlAsistencia());
            filaData.createCell(4).setCellValue(alumno.getAlAdulaPuntos());
            filaData.setRowStyle(styleData);

            numFila++;
        }
    }
}
