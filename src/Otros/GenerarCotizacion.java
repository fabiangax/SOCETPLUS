/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import java.io.FileOutputStream;
import java.util.Date;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;


public class GenerarCotizacion {
        private static String FILE = "E:\\Downloads\\Cotizacion.pdf";
        private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                        Font.BOLD);
        private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.RED);
        private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD);
        private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.BOLD);
        private static Font titleArial = new Font(Font.FontFamily.HELVETICA, 13);
        private static Font bodyArial = new Font(Font.FontFamily.HELVETICA, 11);

        public static void main(String[] args) {
                try {
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(FILE));
                        document.open();
                        addMetaData(document);
                        addTitlePage(document);
                        document.close();
                } catch (DocumentException | IOException e) {
                        e.printStackTrace();
                }
        }

        // iText allows to add metadata to the PDF which can be viewed in your Adobe
        // Reader
        // under File -> Properties
        private static void addMetaData(Document document) {
                document.addTitle("Cotizacion SOCET");
                document.addSubject("Digitalizada");
                document.addKeywords("Cotizacion,SOCET");
                document.addAuthor("Fabian Gaxiola");
                document.addCreator("Fabian Gaxiola");
        }
        
        private static void addTitlePage(Document document)throws DocumentException, BadElementException, IOException {
                Image imagen = Image.getInstance("src/Miscelaneo/1.png"); 
                //imagen.setAlignment(Element.ALIGN_CENTER);
                imagen.setAbsolutePosition(60f, 715f);
                imagen.scaleAbsolute(90,90);
                document.add(imagen);
                Paragraph par = new Paragraph();
                //document.add(Chunk.NEWLINE);
                par.setIndentationLeft(240);
                par.add(new Paragraph("SOCET S.A. de C.V.", titleArial));
                document.add(par);
                //document.add(Chunk.NEWLINE);
                Paragraph par1 = new Paragraph();
                par1.setIndentationLeft(120);
                par1.add(new Paragraph("San José Obrero #2325 Ote. Col. Hacienda San José Cd. Obregón, Sonora.", bodyArial));
                document.add(par1);
                Paragraph par2 = new Paragraph();
                par2.setIndentationLeft(250);
                par2.add(new Paragraph("Tel: (644)1.69.76.86", bodyArial));
                document.add(par2);
                Paragraph par3 = new Paragraph();
                par3.setIndentationLeft(210);
                par3.add(new Paragraph("e-Mail: socetsadecv@yahoo.com.mx", bodyArial));
                document.add(par3);
                document.add(Chunk.NEWLINE);
                PdfPTable table = new PdfPTable(12);
                PdfPCell id = new PdfPCell(new Phrase("ID Cotizacion"));
                id.setColspan(3);
                id.setRowspan(1);
                id.setBackgroundColor(new BaseColor(236, 247, 136));
                table.addCell(id);
                PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
                nombre.setColspan(5);
                nombre.setBackgroundColor(new BaseColor(236, 247, 136));
                table.addCell(nombre);
                PdfPCell empresa = new PdfPCell(new Phrase("Empresa"));
                empresa.setColspan(2);
                empresa.setRowspan(1);
                empresa.setBackgroundColor(new BaseColor(236, 247, 136));
                table.addCell(empresa);
                PdfPCell ciudad = new PdfPCell(new Phrase("Ciudad"));
                ciudad.setBackgroundColor(new BaseColor(236, 247, 136));
                ciudad.setColspan(2);
                table.addCell(ciudad);
                PdfPCell a1 = new PdfPCell(new Phrase("195AD"));
                a1.setColspan(3);
                a1.setBackgroundColor(BaseColor.WHITE);
                table.addCell(a1);
                PdfPCell a2 = new PdfPCell(new Phrase("Lucho Perez"));
                a2.setBackgroundColor(BaseColor.WHITE);
                a2.setColspan(5);
                table.addCell(a2);
                PdfPCell a3 = new PdfPCell(new Phrase("CEMEX"));
                a3.setBackgroundColor(BaseColor.WHITE);
                a3.setColspan(2);
                table.addCell(a3);
                PdfPCell a4 = new PdfPCell(new Phrase("Obregon"));
                a4.setBackgroundColor(BaseColor.WHITE);
                a4.setColspan(2);
                table.addCell(a4);
                PdfPCell idcli = new PdfPCell(new Phrase("ID Cliente"));
                idcli.setColspan(3);
                idcli.setBackgroundColor(new BaseColor(236, 247, 136));
                table.addCell(idcli);
                PdfPCell dir = new PdfPCell(new Phrase("Direccion"));
                dir.setColspan(5);
                dir.setBackgroundColor(new BaseColor(236, 247, 136));
                table.addCell(dir);
                PdfPCell fecha = new PdfPCell(new Phrase("Fecha"));
                fecha.setColspan(2);
                fecha.setBackgroundColor(new BaseColor(236, 247, 136));
                table.addCell(fecha);
                PdfPCell email = new PdfPCell(new Phrase("e-mail"));
                email.setColspan(2);
                email.setBackgroundColor(new BaseColor(236, 247, 136));
                table.addCell(email);
                PdfPCell b1 = new PdfPCell(new Phrase("195CL"));
                b1.setColspan(3);
                b1.setBackgroundColor(BaseColor.WHITE);
                table.addCell(b1);
                PdfPCell b2 = new PdfPCell(new Phrase("Granate #1718 Col. Valle Verde"));
                b2.setBackgroundColor(BaseColor.WHITE);
                b2.setColspan(5);
                table.addCell(b2);
                PdfPCell b3 = new PdfPCell(new Phrase("14/10/2017"));
                b3.setBackgroundColor(BaseColor.WHITE);
                b3.setColspan(2);
                table.addCell(b3);
                PdfPCell b4 = new PdfPCell(new Phrase("cemex@gmail.com"));
                b4.setBackgroundColor(BaseColor.WHITE);
                b4.setColspan(2);
                table.addCell(b4);
                document.add(table);
                document.add(Chunk.NEWLINE);
                PdfPTable table2 = new PdfPTable(12);
                PdfPCell gato = new PdfPCell(new Phrase("#"));
                gato.setBackgroundColor(new BaseColor(236, 247, 136));
                gato.setColspan(1);
                table2.addCell(gato);
                PdfPCell idpro = new PdfPCell(new Phrase("ID Producto"));
                idpro.setColspan(2);
                idpro.setBackgroundColor(new BaseColor(236, 247, 136));
                table2.addCell(idpro);
                PdfPCell cant = new PdfPCell(new Phrase("Cant"));
                cant.setBackgroundColor(new BaseColor(236, 247, 136));
                cant.setColspan(1);
                table2.addCell(cant);
                PdfPCell desc = new PdfPCell(new Phrase("Descripcion"));
                desc.setBackgroundColor(new BaseColor(236, 247, 136));
                desc.setColspan(4);
                table2.addCell(desc);
                PdfPCell valoru = new PdfPCell(new Phrase("Valor Unitario"));
                valoru.setBackgroundColor(new BaseColor(236, 247, 136));
                valoru.setColspan(2);
                table2.addCell(valoru);
                PdfPCell valort = new PdfPCell(new Phrase("Valor Total"));
                valort.setBackgroundColor(new BaseColor(236, 247, 136));
                valort.setColspan(2);
                table2.addCell(valort);
                for(int i=0;i<20;i++){
                    if(i%2==0){
                        PdfPCell v1 = new PdfPCell(new Phrase(" "));
                        v1.setBackgroundColor(BaseColor.WHITE);
                        v1.setColspan(1);
                        table2.addCell(v1);
                        PdfPCell v2 = new PdfPCell(new Phrase(""));
                        v2.setBackgroundColor(BaseColor.WHITE);
                        v2.setColspan(2);
                        table2.addCell(v2);
                        PdfPCell v3 = new PdfPCell(new Phrase(""));
                        v3.setBackgroundColor(BaseColor.WHITE);
                        v3.setColspan(1);
                        table2.addCell(v3);
                        PdfPCell v4 = new PdfPCell(new Phrase(""));
                        v4.setBackgroundColor(BaseColor.WHITE);
                        v4.setColspan(4);
                        table2.addCell(v4);
                        PdfPCell v5 = new PdfPCell(new Phrase(""));
                        v5.setBackgroundColor(BaseColor.WHITE);
                        v5.setColspan(2);
                        table2.addCell(v5);
                        PdfPCell v6 = new PdfPCell(new Phrase(""));
                        v6.setBackgroundColor(BaseColor.WHITE);
                        v6.setColspan(2);
                        table2.addCell(v6);
                    }
                    else{
                        PdfPCell v1 = new PdfPCell(new Phrase(" "));
                        v1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v1.setColspan(1);
                        table2.addCell(v1);
                        PdfPCell v2 = new PdfPCell(new Phrase(""));
                        v2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v2.setColspan(2);
                        table2.addCell(v2);
                        PdfPCell v3 = new PdfPCell(new Phrase(""));
                        v3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v3.setColspan(1);
                        table2.addCell(v3);
                        PdfPCell v4 = new PdfPCell(new Phrase(""));
                        v4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v4.setColspan(4);
                        table2.addCell(v4);
                        PdfPCell v5 = new PdfPCell(new Phrase(""));
                        v5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v5.setColspan(2);
                        table2.addCell(v5);
                        PdfPCell v6 = new PdfPCell(new Phrase(""));
                        v6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v6.setColspan(2);
                        table2.addCell(v6);
                    }
                }
                PdfPCell c1 = new PdfPCell(new Phrase("Observaciones"));
                c1.setBackgroundColor(BaseColor.WHITE);
                c1.setColspan(8);
                c1.setRowspan(4);
                table2.addCell(c1);
                PdfPCell c2 = new PdfPCell(new Phrase("Subtotal:"));
                c2.setBackgroundColor(BaseColor.WHITE);
                c2.setColspan(2);
                table2.addCell(c2);
                PdfPCell c3 = new PdfPCell(new Phrase(" "));
                c3.setBackgroundColor(BaseColor.WHITE);
                c3.setColspan(2);
                table2.addCell(c3);
                PdfPCell c4 = new PdfPCell(new Phrase("Descuento:"));
                c4.setBackgroundColor(BaseColor.WHITE);
                c4.setColspan(2);
                table2.addCell(c4);
                PdfPCell c5 = new PdfPCell(new Phrase(" "));
                c5.setBackgroundColor(BaseColor.WHITE);
                c5.setColspan(2);
                table2.addCell(c5);
                PdfPCell c6 = new PdfPCell(new Phrase("I.V.A:"));
                c6.setBackgroundColor(BaseColor.WHITE);
                c6.setColspan(2);
                table2.addCell(c6);
                PdfPCell c7 = new PdfPCell(new Phrase(" "));
                c7.setBackgroundColor(BaseColor.WHITE);
                c7.setColspan(2);
                table2.addCell(c7);
                PdfPCell c8 = new PdfPCell(new Phrase("Total:"));
                c8.setBackgroundColor(BaseColor.WHITE);
                c8.setColspan(2);
                table2.addCell(c8);
                PdfPCell c9 = new PdfPCell(new Phrase(" "));
                c9.setBackgroundColor(BaseColor.WHITE);
                c9.setColspan(2);
                table2.addCell(c9);
                document.add(table2);
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                Paragraph line = new Paragraph();
                line.setIndentationLeft(160);
                line.add(new Paragraph("_____________________________", titleArial));
                document.add(line);
                Paragraph firma = new Paragraph();
                firma.setIndentationLeft(250);
                firma.add(new Paragraph("Firma", titleArial));
                document.add(firma);
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                Paragraph nota = new Paragraph();
                nota.setIndentationLeft(300);
                nota.add(new Paragraph("NOTA: Cotización válida por 60 días.", bodyArial));
                document.add(nota);
                document.close();
        }
}
