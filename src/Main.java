public class Main {

    public static void main(String[] args){
        String publicKey = "suepk";
        String signature = "sig";
        String fileName = "input.txt";

        Encrypt enc = new Encrypt();
        enc.main(publicKey, signature, fileName);

        Decrypt dec = new Decrypt();
        dec.main(publicKey, signature, fileName);
    }
}
