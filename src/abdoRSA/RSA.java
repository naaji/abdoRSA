package abdoRSA;

import java.math.BigInteger;
import static java.math.BigInteger.*;

public class RSA {

    private BigInteger privatekey;
    private BigInteger publickey;
    private BigInteger N;

    RSA() {
        // two prime numbers p AND q
        BigInteger p = new BigInteger("11");
        BigInteger q = new BigInteger("7");

        // N
        N = p.multiply(q);

        // phiN = (p-1) * (q-1)
        BigInteger phiN = p.subtract(ONE).multiply(q.subtract(ONE));

        // Corresponding public key e
        publickey = new BigInteger("47");
        // Private key corresponding to the modular inverse of d e
        privatekey = publickey.modInverse(phiN);
    }

    private BigInteger encode(BigInteger openmessage) {
        // C = M^e mod N
        return openmessage.modPow(publickey, N);
    }

    private BigInteger decode(BigInteger secretmessage) {
        // M = C^e mod N
        return secretmessage.modPow(privatekey, N);
    }

    public String toString() {
        String s = "";
        s += "public key = " + publickey + "\n";
        s += "private key = " + privatekey + "\n";
        s += "N = " + N;
        return s;
    }

    public static void main(String[] args) {
    	

        RSA key = new RSA();
        System.out.println(key);
        // Message to be encrypted (number)
        BigInteger message = new BigInteger("2");

        BigInteger encryptedmessage = key.encode(message);
        BigInteger decryptedmessage = key.decode(encryptedmessage);

        System.out.println("message: " + message);
        System.out.println("encrypted message: " + encryptedmessage);
        System.out.println("decrypted message: " + decryptedmessage);
    }
}