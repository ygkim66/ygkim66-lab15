public class Enigma{

    private String rotorInit[] = 
    {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"};


    private Rotor rotors[];
        
    public Enigma(int id1, int id2, int id3, String start){

        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
        
    }

    public String decrypt(String message){        
        int outerCount = -1;
        char middleRotor = 0;
        String dec = "";

        for (int i = 0; i < message.length(); i++){
            //grabs location of character of message on outer rotor
            outerCount = rotors[2].indexOf(message.charAt(i));
            //grabs char of middle rotor at same location of outer 
            middleRotor = rotors[1].charAt(outerCount);

            //finds middleRotor character in outer rotor
            outerCount = rotors[2].indexOf(middleRotor);

            dec = dec + rotors[0].charAt(outerCount);
            rotate();
        }
        return dec;
    }


    
    public String encrypt(String message){
        String enc = "";
        int innerCount = -1;
        int middleCount = -1;
        char outerRotor = 0;
        for (int i = 0; i < message.length(); i++){
            //finds location of first letter in message
            innerCount = rotors[0].indexOf(message.charAt(i));
            //finds character at location of innercount
            outerRotor = rotors[2].charAt(innerCount);
            //finds location of outerRotor character
            middleCount = rotors[1].indexOf(outerRotor);
            //adds outer rotor character from index of middle rotor
            enc = enc + rotors[2].charAt(middleCount);
            //rotates necessary rotors
            rotate();
        }
        return enc;
    }

    
    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }
    
}
