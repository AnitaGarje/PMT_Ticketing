package com.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;


@Component
public class PdfGenaratorUtil {
	@Autowired
	private TemplateEngine templateEngine;
	public void createPdf(String templateName, Map<String, String> map) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
		     Iterator itMap = map.entrySet().iterator();
		       while (itMap.hasNext()) {
			  Map.Entry pair = (Map.Entry) itMap.next();
		          ctx.setVariable(pair.getKey().toString(), pair.getValue());
		         System.out.println("pair.getKey().toString()="+pair.getKey().toString());
		         System.out.println("pair.getValue()="+pair.getValue());
			}
		}
		
		String processedHtml = templateEngine.process(templateName, ctx);
		System.out.println("templateName="+templateName);
		System.out.println("ctx="+ctx);
		System.out.println("processedHtml="+processedHtml);
		  FileOutputStream os = null;
		  String fileName = UUID.randomUUID().toString();
		  System.out.println("fileName="+fileName);
	        try {
	            final File outputFile = File.createTempFile(fileName, ".pdf");
	            os = new FileOutputStream(outputFile);

	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(processedHtml);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
	            System.out.println("PDF created successfully");
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { /*ignore*/ }
	            }
	        }
	}
}