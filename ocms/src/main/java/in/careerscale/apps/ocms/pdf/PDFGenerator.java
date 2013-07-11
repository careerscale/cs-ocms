package in.careerscale.apps.ocms.pdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Collection;
import java.util.Date;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.ibm.icu.util.Calendar;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.CrlClient;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.OcspClient;
import com.itextpdf.text.pdf.security.PrivateKeySignature;
import com.itextpdf.text.pdf.security.TSAClient;

public class PDFGenerator
{

	private Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
	private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	private Font plainFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);

	public static void sign(String src, String name, String dest, Certificate[] chain, PrivateKey pk,
			String digestAlgorithm, String provider, CryptoStandard subfilter, String reason, String location)
			throws GeneralSecurityException, IOException, DocumentException
	{
		// Creating the reader and the stamper
		PdfReader reader = new PdfReader(src);
		FileOutputStream os = new FileOutputStream(dest);
		PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
		// Creating the appearance
		PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
		appearance.setReason(reason);
		appearance.setLocation(location);
		appearance.setVisibleSignature(name);
		// Creating the signature
		ExternalSignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
		ExternalDigest digest = new BouncyCastleDigest();
		MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, subfilter);
	}

	private void addPageTitle(Document document) throws DocumentException, MalformedURLException, IOException
	{
		Paragraph title = new Paragraph("RECEIPT", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(Chunk.NEWLINE);
		PdfPTable table = new PdfPTable(2);
		// table.setHeaderRows(1);

		table.setWidthPercentage(100);
		Image logo = Image.getInstance("tmad_logo.jpg");
		logo.setAlignment(Element.ALIGN_CENTER);

		// first movie
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell = new PdfPCell(logo);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		PdfPCell address = new PdfPCell();
		address.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Paragraph p1 = new Paragraph("TO MAKE A DIFFERENCE(TMAD),Regd.No:1506/2007,", plainFont);
		p1.setAlignment(Element.ALIGN_RIGHT);
		address.addElement(p1);
		Paragraph p2 = new Paragraph("(Regd. under the A.P.Societies Registration Act, 2001)", plainFont);
		p2.setAlignment(Element.ALIGN_RIGHT);
		address.addElement(p2);

		Paragraph p3 = new Paragraph("Contributions to TMAD is eligible for Tax Exemption", plainFont);
		p3.setAlignment(Element.ALIGN_RIGHT);
		address.addElement(p3);

		Paragraph p4 = new Paragraph("Under U/s 80G(2) & 5 of I.T.Act, 1961", plainFont);
		p4.setAlignment(Element.ALIGN_RIGHT);
		address.addElement(p4);

		Paragraph p5 = new Paragraph("TMAD PAN Number -AABAT6619C", plainFont);
		p5.setAlignment(Element.ALIGN_RIGHT);
		address.addElement(p5);

		Paragraph p6 = new Paragraph("2-1-7/2/88, Venkateswara Enclave, Upparpally,", plainFont);
		p6.setAlignment(Element.ALIGN_RIGHT);
		address.addElement(p6);

		Paragraph p7 = new Paragraph("Rajendra Nagar, Hyderabad - 500048", plainFont);
		p7.setAlignment(Element.ALIGN_RIGHT);
		address.addElement(p7);

		address.setBorder(Rectangle.NO_BORDER);
		table.addCell(address);
		document.add(table);
		document.add(Chunk.NEWLINE);
		LineSeparator separator = new LineSeparator(2, 100, BaseColor.DARK_GRAY, Element.ALIGN_CENTER, 1);
		document.add(separator);

	}

	private void addDonationInformation(Document document, String name, String amount, String reason, String format,
			String date, String receiptNumber) throws DocumentException
	{
		String certNumber = "Receipt Number : " + receiptNumber
				+ "                                                                " + "Date  " + date;
		Paragraph certParagraph = new Paragraph(certNumber, plainFont);
		certParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(certParagraph);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(new Paragraph("Received with thanks from " + name.toUpperCase() + "  Rupees "
				+ amount.toUpperCase() + " towards  " + reason.toUpperCase() + "  in the form of "
				+ format.toUpperCase() + " on   " + date));
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

	}

	private void addFooter(Document document) throws DocumentException
	{

		document.add(Chunk.NEWLINE);
		LineSeparator separator = new LineSeparator(1, 100, BaseColor.DARK_GRAY, Element.ALIGN_CENTER, 1);
		document.add(separator);
		document.add(new Paragraph("This document is generated by OCMS on " + Calendar.getInstance().getTime(),
				smallBold));
		document.add(Chunk.NEWLINE);
		document.add(new Paragraph(
				"This receipt is generated in the demo mode and should not be treated as REAL/ORIGINAL, DEMO purpose only",
				redFont));
	}

	public void generateReceipt(String name, String amount, String reason, String format, String date,
			String receiptNumber)
			throws IOException, GeneralSecurityException
	{
		try
		{
			String fileName = name;
			Document document = new Document();

			FileOutputStream out = new FileOutputStream(fileName + ".pdf");
			PdfWriter.getInstance(document, out);
			document.open();
			addPageTitle(document);
			addDonationInformation(document, name, amount, reason, format, date, receiptNumber);
			addFooter(document);
			document.close();
			sign(fileName + ".pdf", fileName + "_signed.pdf");
		}
		catch (DocumentException de)
		{
			throw new IOException(de.getMessage());
		}
	}

	private void sign(String source, String destination) throws IOException, GeneralSecurityException,
			DocumentException
	{
		String path = "/Users/hmallepally/dsc/HARINATH MALLEPALLY.pfx";
		char[] pass = "1234".toCharArray();

		BouncyCastleProvider provider = new BouncyCastleProvider();
		Security.addProvider(provider);
		KeyStore ks = KeyStore.getInstance("pkcs12", provider.getName());
		ks.load(new FileInputStream(path), pass);
		String alias = (String) ks.aliases().nextElement();
		PrivateKey pk = (PrivateKey) ks.getKey(alias, pass);
		Certificate[] chain = ks.getCertificateChain(alias);
		PDFGenerator app = new PDFGenerator();
		app.generateSimplePDF();
		app.sign(source, destination, chain, pk, DigestAlgorithms.SHA256, provider.getName(), CryptoStandard.CMS,
				"OCMS", "HYDERABAD", null, null, null, 0);
	}

	public static final String SRC = "hello.pdf";
	public static final String DEST = "hello_cacert.pdf";

	public static void main(String[] args) throws IOException, GeneralSecurityException, DocumentException
	{
		/**
		 * Properties properties = new Properties(); properties.load(new
		 * FileInputStream("c:/home/blowagie/key.properties")); String path = properties.getProperty("PRIVATE"); char[]
		 * pass = properties.getProperty("PASSWORD").toCharArray();
		 */

		PDFGenerator generator = new PDFGenerator();
		generator.generateReceipt("Harinath", "Two thousand", " Demo purpose ", " Online", "09/07/2013", "202");

	}

	private void sign(String src, String dest, Certificate[] chain, PrivateKey pk, String digestAlgorithm,
			String provider, CryptoStandard subfilter, String reason, String location, Collection<CrlClient> crlList,
			OcspClient ocspClient, TSAClient tsaClient, int estimatedSize) throws GeneralSecurityException,
			IOException, DocumentException
	{ // Creating the reader and the stamper
		PdfReader reader = new PdfReader(src);
		FileOutputStream os = new FileOutputStream(dest);
		PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
		// Creating the appearance
		PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
		appearance.setReason(reason);
		appearance.setLocation(location);
		appearance.setVisibleSignature(new Rectangle(36, 748, 600, 780), 1, "OCMS");
		// Creating the signature
		ExternalSignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
		ExternalDigest digest = new BouncyCastleDigest();
		MakeSignature.signDetached(appearance, digest, pks, chain, crlList, ocspClient, tsaClient, estimatedSize,
				subfilter);
	}

	private void generateSimplePDF() throws IOException
	{

		try
		{
			Document document = new Document();
			FileOutputStream out = new FileOutputStream("hello.pdf");
			PdfWriter.getInstance(document, out);
			document.open();
			document.addTitle("Welcome to OCMS");
			document.addHeader("Hello Welcome to OCMS", "The Online Case Management System");
			document.add(new Paragraph("Hello Harinath"));
			document.add(new Paragraph("Current time is " + new Date().toString()));
			document.close();
		}
		catch (DocumentException de)
		{
			throw new IOException(de.getMessage());
		}
	}

}
