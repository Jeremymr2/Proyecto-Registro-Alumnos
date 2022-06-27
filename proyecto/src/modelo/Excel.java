package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    /**
     * Crea el excel con un cabezal
     */
    public static void crearExcel() throws IOException {
        Workbook book = new XSSFWorkbook();

        Sheet sheet = (Sheet) book.createSheet("Datos Personales");

        Row fila = sheet.createRow(0);

        fila.createCell(0).setCellValue("Código");
        fila.createCell(1).setCellValue("Nombre");
        fila.createCell(2).setCellValue("Apellido");
        fila.createCell(3).setCellValue("Edad");
        fila.createCell(4).setCellValue("Sexo");
        fila.createCell(5).setCellValue("Enfermedad");

        try {
            FileOutputStream fileout = new FileOutputStream("Excel de estudiantes.xlsx");
            book.write(fileout);
            fileout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int obtenerPosicion(String codigo) {
        GestorEstudiantes estudiantes = new GestorEstudiantes();
        return estudiantes.posicion(codigo);
    }

    public static int obtenerPosicion(Estudiante e) {
        GestorEstudiantes estudiantes = new GestorEstudiantes();
        return estudiantes.posicion(e);
    }

    public void leerArchivo() throws IOException {
        FileInputStream file = new FileInputStream(new File("Excel de estudiantes.xlsx"));
        Estudiante e = new Estudiante("", "", 0, "", "", "");
        int cont = 0;
        GestorEstudiantes estudiantes = new GestorEstudiantes();

        // Crear el objeto que tendrá el libro de Excel
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        /**
         * Obtenemos la primera pestaña a la que se quiera procesar indicando el
         * indice.
         *
         * Una vez obtenida la hoja excel con las filas que se quieren leer
         * obtenemos el iterator
         *
         * que nos permite recorrer cada una de las filas que contiene.
         *
         */
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        Row row;

        // Recorremos todas las filas para mostrar el contenido de cada celda
        while (rowIterator.hasNext()) {

            row = rowIterator.next();

            // Obtenemos el iterator que permite recorrer todas las celdas de una fila
            Iterator<Cell> cellIterator = row.cellIterator();

            Cell celda = null;

            while (cellIterator.hasNext()) {

                celda = cellIterator.next();

                if (celda.getRowIndex() != 0) {
                    switch (celda.getColumnIndex()) {
                        case 0 ->
                            e.setCodigo(celda.getStringCellValue());
                        case 1 ->
                            e.setNombre(celda.getStringCellValue());
                        case 2 ->
                            e.setApellido(celda.getStringCellValue());
                        case 3 ->
                            e.setEdad((int) celda.getNumericCellValue());
                        case 4 ->
                            e.setSexo(celda.getStringCellValue());
                        case 5 ->
                            e.setEnfermedad(celda.getStringCellValue());
                    }
                }

            }
            if (celda.getRowIndex() != 0) {
                estudiantes.agregarEstudiante(e);
                cont++;

            }
        }
        JOptionPane.showMessageDialog(null, "¡Se han cargado " + cont + " estudiantes! ");
        // cerramos el libro excel
        workbook.close();
    }

    /**
     * Modifica los datos de un excel existente
     */
    public void modificar(Estudiante e, int posicion) throws IOException {
        GestorEstudiantes estudiantes = new GestorEstudiantes();

        try {
            FileInputStream file = new FileInputStream(new File("Excel de estudiantes.xlsx"));

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFRow fila = sheet.createRow(posicion + 1);

            if (fila == null) {
                fila = sheet.createRow(0);
            }

            fila.createCell(0).setCellValue(e.getCodigo());
            fila.createCell(1).setCellValue(e.getNombre());
            fila.createCell(2).setCellValue(e.getApellido());
            fila.createCell(3).setCellValue(e.getEdad());
            fila.createCell(4).setCellValue(e.getSexo());
            fila.createCell(5).setCellValue(e.getEnfermedad());

            file.close();
            FileOutputStream output = new FileOutputStream("Excel de estudiantes.xlsx");
            wb.write(output);
            output.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
