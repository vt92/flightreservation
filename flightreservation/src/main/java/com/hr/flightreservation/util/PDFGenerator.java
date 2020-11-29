package com.hr.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.hr.flightreservation.entity.Reservation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFGenerator {

	public void generateItinerary(Reservation reservation, String filePath) {
		//filepath will be used to later, when we send the email.
		//1. step is to create a document
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			
			//to add new table to PDF and pass the number of columns that we want
			document.add(generateTable(reservation));
			
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		
	}

	//logic for table
	private PdfPTable generateTable(Reservation reservation) {
		//mentioning no. of columns in a table
		PdfPTable table =new PdfPTable(2);
		// cell is also known as row
		PdfPCell cell;
		//pass values to the cell
		cell = new PdfPCell(new Phrase("Flight Itenarary:"));
		//to merge the number of columns
		cell.setColspan(2);
		//add cell to table
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Flight Details:"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDepartureCity());
		
		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());
		
		table.addCell("Flight Number");
		table.addCell(reservation.getFlight().getFlightNumber());
		
		table.addCell("Departure Time");
		table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
		
		cell = new PdfPCell(new Phrase("Passenger Details:"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFirstName());
		
		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastName());		
		
		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhone());
		
		return table;
	}
	
	
	
	
}
