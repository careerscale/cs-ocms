package in.careerscale.apps.ocms.common.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("encryptionService")
public class EncryptionService
{
	@Value("${encryptionKey")
	private String encryptionKey;

	SecretKey key;

	@PostConstruct
	public void setupKeys()
	{

		DESKeySpec keySpec;
		try
		{
			keySpec = new DESKeySpec(encryptionKey.getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			key = keyFactory.generateSecret(keySpec);
		}
		catch (InvalidKeyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidKeySpecException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String encryptedKey(String input)
	{
		try
		{
			Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.encodeBase64URLSafeString(cipher.doFinal(input.getBytes()));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}

	public String decryptKey(String input)
	{
		try
		{
			Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] cipherText = Base64.decodeBase64(input);
			byte[] plainTextPwdBytes = (cipher.doFinal(cipherText));
			return new String(plainTextPwdBytes);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args)
	{
		EncryptionService service = new EncryptionService();
		String encrypted = service.encryptedKey("5");
		System.out.println(encrypted);
		System.out.println(service.decryptKey(encrypted));

	}

}
