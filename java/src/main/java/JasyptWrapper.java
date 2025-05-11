import org.apache.commons.cli.*;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;

public class JasyptWrapper {

    public static void main(String[] args) throws Exception {
        // Add BouncyCastle provider
        Security.addProvider(new BouncyCastleProvider());

        Options options = new Options();

        Option inputOption = new Option("i", "input", true, "Input text");
        inputOption.setRequired(true);
        options.addOption(inputOption);

        Option passwordOption = new Option("p", "password", true, "Password");
        passwordOption.setRequired(true);
        options.addOption(passwordOption);

        Option algorithmOption = new Option("a", "algorithm", true, "Encryption algorithm");
        algorithmOption.setRequired(true);
        options.addOption(algorithmOption);

        Option providerClassOption = new Option("c", "providerClassName", true, "Provider class name");
        providerClassOption.setRequired(true);
        options.addOption(providerClassOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String input = cmd.getOptionValue("input");
        String password = cmd.getOptionValue("password");
        String algorithm = cmd.getOptionValue("algorithm");
        String providerClassName = cmd.getOptionValue("providerClassName");

        PBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);

        // Set the algorithm and provider
        encryptor.setAlgorithm(algorithm);
        encryptor.setProvider(new BouncyCastleProvider());

        if ("encrypt".equalsIgnoreCase(cmd.getArgList().get(0))) {
            String encryptedText = encryptor.encrypt(input);
            System.out.println("Encrypted: " + encryptedText);
        } else if ("decrypt".equalsIgnoreCase(cmd.getArgList().get(0))) {
            String decryptedText = encryptor.decrypt(input);
            System.out.println("Decrypted: " + decryptedText);
        }
    }
}
