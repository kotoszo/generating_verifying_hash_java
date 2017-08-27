import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Decrypt {


        public static void main(String publicKey, String signature, String fileName) {

        /* Verify a DSA signature */
            try {

                FileInputStream keyfis = new FileInputStream(publicKey);
                byte[] encKey = new byte[keyfis.available()];
                keyfis.read(encKey);

                keyfis.close();

                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");

                PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);



                FileInputStream sigfis = new FileInputStream(signature);
                byte[] sigToVerify = new byte[sigfis.available()];
                sigfis.read(sigToVerify);
                sigfis.close();

                Signature sig = Signature.getInstance("SHA1withDSA", "SUN");

                sig.initVerify(pubKey);

                FileInputStream datafis = new FileInputStream(fileName);
                BufferedInputStream bufin = new BufferedInputStream(datafis);

                byte[] buffer = new byte[1024];
                int len;
                while (bufin.available() != 0) {
                    len = bufin.read(buffer);
                    sig.update(buffer, 0, len);
                }

                bufin.close();

                boolean verifies = sig.verify(sigToVerify);

                System.out.println("signature verifies: " + verifies);



            } catch (Exception e) {
                System.err.println("Caught exception " + e.toString());
            }
        }

}

