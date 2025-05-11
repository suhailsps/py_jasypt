import subprocess

def encrypt(text, password):
    result = subprocess.run(
        ['java', '-cp', 'jasypt-wrapper.jar', 'JasyptWrapper', 
         'encrypt', f'input={text}', f'password={password}', 
         'algorithm=PBEWITHSHA256AND256BITAES-CBC-BC', 
         'providerClassName=org.bouncycastle.jce.provider.BouncyCastleProvider'],
        capture_output=True, text=True
    )
    return result.stdout.strip()

def decrypt(encrypted_text, password):
    result = subprocess.run(
        ['java', '-cp', 'jasypt-wrapper.jar', 'JasyptWrapper', 
         'decrypt', f'input={encrypted_text}', f'password={password}', 
         'algorithm=PBEWITHSHA256AND256BITAES-CBC-BC', 
         'providerClassName=org.bouncycastle.jce.provider.BouncyCastleProvider'],
        capture_output=True, text=True
    )
    return result.stdout.strip()
