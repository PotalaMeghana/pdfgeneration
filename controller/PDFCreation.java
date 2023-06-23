/*
 * package eStoreProduct.controller; import com.itextpdf.text.Document; import
 * com.itextpdf.text.DocumentException; import com.itextpdf.text.Paragraph;
 * import com.itextpdf.text.pdf.PdfWriter; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession; import java.io.ByteArrayOutputStream; import
 * java.io.IOException; import java.io.OutputStream; import java.io.PrintWriter;
 * import java.io.StringWriter;
 * 
 * @Controller public class PDFCreation { HttpServletRequest request;
 * HttpServletResponse response;
 * 
 * @GetMapping(value = "/invoice") public void generatePDF(HttpServletResponse
 * response, HttpSession session) throws IOException, ServletException { try
 * (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
 * // Create a new document Document document = new Document();
 * 
 * // Create a PDF writer PdfWriter writer = PdfWriter.getInstance(document,
 * byteArrayOutputStream);
 * 
 * // Open the document document.open();
 * 
 * // Render the JSP file and add its content to the document String renderedJsp
 * = renderJspAsString(session); document.add(new Paragraph(renderedJsp));
 * 
 * // Close the document document.close();
 * 
 * // Set the response content type to PDF
 * response.setContentType("application/pdf");
 * 
 * // Set the response headers response.setHeader("Content-Disposition",
 * "inline; filename=invoice.pdf");
 * 
 * // Write the PDF content to the response OutputStream out =
 * response.getOutputStream(); byteArrayOutputStream.writeTo(out); out.flush();
 * } catch (DocumentException e) { e.printStackTrace(); } }
 * 
 * // Helper method to render the JSP file as a string private String
 * renderJspAsString(HttpSession session) throws IOException, ServletException {
 * // Create a new StringWriter to capture the JSP output StringWriter
 * stringWriter = new StringWriter();
 * 
 * // Execute the JSP file and capture its output try (PrintWriter printWriter =
 * new PrintWriter(stringWriter)) { // Include the JSP file
 * session.getServletContext().getRequestDispatcher("/your-invoice-jsp-file.jsp"
 * ).include(request, response);
 * 
 * // Flush and close the writer printWriter.flush(); }
 * 
 * // Return the rendered JSP output as a string return stringWriter.toString();
 * } }
 */











/*
 * package eStoreProduct.controller;
 * 
 * import com.itextpdf.text.Document; import
 * com.itextpdf.text.DocumentException; import com.itextpdf.text.Paragraph;
 * import com.itextpdf.text.pdf.PdfWriter; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * javax.servlet.ServletException; import javax.servlet.http.HttpServletRequest;
 * import javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession; import java.io.ByteArrayOutputStream; import
 * java.io.IOException; import java.io.OutputStream; import java.io.PrintWriter;
 * import java.io.StringWriter;
 * 
 * @Controller public class PDFCreation {
 * 
 * @GetMapping(value = "/invoice") public void generatePDF(HttpServletRequest
 * request, HttpServletResponse response, HttpSession session) throws
 * IOException, ServletException {
 * 
 * System.out.println("hiiiiiiiiiiiiiiiiiiiiiii"); try (ByteArrayOutputStream
 * byteArrayOutputStream = new ByteArrayOutputStream()) { // Create a new
 * document Document document = new Document();
 * 
 * // Create a PDF writer PdfWriter writer = PdfWriter.getInstance(document,
 * byteArrayOutputStream);
 * 
 * // Open the document document.open();
 * 
 * // Render the JSP file and add its content to the document String renderedJsp
 * = renderJspAsString(request, response, session); document.add(new
 * Paragraph(renderedJsp));
 * 
 * // Close the document document.close();
 * 
 * // Set the response content type to PDF
 * response.setContentType("application/pdf");
 * 
 * // Set the response headers response.setHeader("Content-Disposition",
 * "inline; filename=invoice.pdf");
 * 
 * // Write the PDF content to the response OutputStream out =
 * response.getOutputStream(); byteArrayOutputStream.writeTo(out); out.flush();
 * } catch (DocumentException e) { e.printStackTrace(); } }
 * 
 * // Helper method to render the JSP file as a string private String
 * renderJspAsString(HttpServletRequest request, HttpServletResponse response,
 * HttpSession session) throws IOException, ServletException { // Create a new
 * StringWriter to capture the JSP output StringWriter stringWriter = new
 * StringWriter();
 * 
 * // Execute the JSP file and capture its output try (PrintWriter printWriter =
 * new PrintWriter(stringWriter)) { // Include the JSP file
 * request.getRequestDispatcher("/WEB-INF/views/invoice.jsp").include(request,
 * response);
 * 
 * // Flush and close the writer printWriter.flush(); }
 * 
 * // Return the rendered JSP output as a string return stringWriter.toString();
 * } }
 */