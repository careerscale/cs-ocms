package in.careerscale.apps.ocms.pdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Collection;
import java.util.Date;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
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

	public static final String SRC = "hello.pdf";
	public static final String DEST = "hello_cacert.pdf";

	public static void main(String[] args) throws IOException, GeneralSecurityException, DocumentException
	{
		/**
		 * Properties properties = new Properties(); properties.load(new
		 * FileInputStream("c:/home/blowagie/key.properties")); String path = properties.getProperty("PRIVATE"); char[]
		 * pass = properties.getProperty("PASSWORD").toCharArray();
		 */
		String path = "/Users/hmallepally/dsc/HARINATH MALLEPALLY.pfx";
		char[] pass = "password".toCharArray();

		BouncyCastleProvider provider = new BouncyCastleProvider();
		Security.addProvider(provider);
		KeyStore ks = KeyStore.getInstance("pkcs12", provider.getName());
		ks.load(new FileInputStream(path), pass);
		String alias = (String) ks.aliases().nextElement();
		PrivateKey pk = (PrivateKey) ks.getKey(alias, pass);
		Certificate[] chain = ks.getCertificateChain(alias);
		PDFGenerator app = new PDFGenerator();
		app.generateSimplePDF();
		app.sign(SRC, DEST, chain, pk, DigestAlgorithms.SHA256, provider.getName(), CryptoStandard.CMS, "Test",
				"Ghent", null, null, null, 0);
	}

	public void sign(String src, String dest, Certificate[] chain, PrivateKey pk, String digestAlgorithm,
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
		appearance.setVisibleSignature(new Rectangle(36, 748, 144, 780), 1, "sig");
		// Creating the signature
		ExternalSignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
		ExternalDigest digest = new BouncyCastleDigest();
		MakeSignature.signDetached(appearance, digest, pks, chain, crlList, ocspClient, tsaClient, estimatedSize,
				subfilter);
	}

	public void generateSimplePDF() throws IOException
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
