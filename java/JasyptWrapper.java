import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import org.apache.commons.cli.*;

public class JasyptWrapper {
    public static void main(String[] args) {
        Options options = new Options();

        Option inputOption = new Option("i", "input", true, "Input string to encrypt/decrypt");
        inputOption.setRequired(true);
        options.addOption(inputOption);

        Option passwordOption = new Option("p", "password", true, "Password for encryption/decryption");
        passwordOption.setRequired(true);
        options.addOption(passwordOption);

        Option algorithmOption = new Option("a", "algorithm", true, "Encryption algorithm");
        algorithmOption.setRequired(true);
        options.addOption(algorithmOption);

        Option providerOption = new Option("c", "providerClassName", true, "Provider class name");
        providerOption.setRequired(true);
        options.addOption(providerOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String input = cmd.getOptionValue("input");
            String password = cmd.getOptionValue("password");
            String algorithm = cmd.getOptionValue("algorithm");
            String providerClassName = cmd.getOptionValue("providerClassName");

            Security.addProvider(new BouncyCastleProvider());

            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(password);
            encryptor.setAlgorithm(algorithm);
            encryptor.setProvider(providerClassName);

            if (cmd.hasOption("encrypt")) {
                String encrypted = encryptor.encrypt(input);
                System.out.println("Encrypted: " + encrypted);
            } else if (cmd.hasOption("decrypt")) {
                String decrypted = encryptor.decrypt(input);
                System.out.println("Decrypted: " + decrypted);
            }
        } catch (ParseException exp) {
            System.out.println("Error: " + exp.getMessage());
        }
    }
}