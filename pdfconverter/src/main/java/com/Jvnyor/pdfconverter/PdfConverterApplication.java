package com.Jvnyor.pdfconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import io.github.jonathanlink.PDFLayoutTextStripper;

public class PdfConverterApplication {

    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
    	
        String string = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Insira o caminho do pdf:");
        String pdfPath = in.nextLine();
        byte[] pdfPathBytes = pdfPath.getBytes();
        String pdfPathToUTF8 = new String(pdfPathBytes, StandardCharsets.UTF_8);
        try {
        	File file = new File(pdfPathToUTF8);
            String txtName = file.getName().replace(".pdf", "");
            String txtPath = "C:\\pdfConverter\\txts\\"+txtName+".txt";
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(file, "r"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            string = pdfTextStripper.getText(pdDocument);
                
            PrintWriter out = new PrintWriter(new FileOutputStream(txtPath));
            String lines[] = string.split("\\r?\\n");
    	    for (String line : lines) {	
    	        out.println(line);
    	    }
    	    
            out.flush();
            out.close();
            System.out.println("Conversão completa!");
            System.out.println("Arquivo criado em "+txtPath);
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         };
            
    }

}