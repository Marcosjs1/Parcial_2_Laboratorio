import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Parcial {
    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Bienvenido al detector de ADN");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Tendrás que ingresar el ADN para saber si eres mutante o no");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Las únicas letras que se permiten son: A, T, C, G");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Representan cada base nitrogenada del ADN y además se guardarán en una matriz de 6x6 que comprobará si eres mutante o no");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        int opc;
        int counter = 1;
        List<List<Character>> matriz = new ArrayList<>();
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese [0] para empezar: ");
        opc = scanner.nextInt();
        if (opc == 0) {
            do {

                if (counter > 0) {
                    IS_MUTANT_DNA(matriz);
                    int sequences = count_sequences(matriz);
                    if (sequences > 1) {
                        System.out.println("-----------------------------------------");
                        System.out.println("[ES MUTANTE]");
                        System.out.println("SE ENCONTRARON MÁS DE UNA SECUENCIA DE 4 LETRAS IGUALES");
                        System.out.println("-----------------------------------------");
                        counter--;
                    } else {
                        System.out.println("-----------------------------------------");
                        System.out.println("[NO ES MUTANTE]");
                        System.out.println("NO SE ENCONTRARON SUFICIENTES SECUENCIAS DE 4 LETRAS IGUALES");
                        System.out.println("-----------------------------------------");
                        counter--;
                    }
                }
            } while (opc == 0);
        }
    }

    public static void IS_MUTANT_DNA(List<List<Character> > matriz) {
        Scanner scanner = new Scanner(System.in);
        int i = 6;
        while (i > 0) {
            System.out.print("Ingrese las letras del ADN: ");
            String data = scanner.next().toUpperCase();
            System.out.println("-----------------------------------------");

            if (data.length() != 6) {
                System.out.println("-----------------------------------------");
                System.out.println("NO INGRESO EL NUMERO CORRECTO DE LETRAS");
                System.out.println("POR FAVOR INGRESE LA CANTIDAD CORRECTA");
                System.out.println("-----------------------------------------");
            } else if (validation(data)) {
                List<Character> dataChars = new ArrayList<>();
                for (char c : data.toCharArray()) {
                    dataChars.add(c);
                }
                matriz.add(dataChars);
                i--;
            } else {
                System.out.println("-----------------------------------------");
                System.out.println("INGRESE LAS LETRAS VALIDAS UNICAMENTE");
                System.out.println("-----------------------------------------");
            }
        }

        for (List<Character> row : matriz) {
            for (Character element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static boolean validation(String letters) {
        String validDna = "ACTG";
        for (char letter : letters.toCharArray()) {
            if (validDna.indexOf(letter) == -1) {
                return false;
            }
        }
        return true;
    }

    public static int row_horizontal(List<List<Character>> matriz) {
        int sequences = 0;
        for (List<Character> row : matriz) {
            int counter = 1;
            char previousLetter = row.get(0);
            for (int i = 1; i < row.size(); i++) {
                char letter = row.get(i);
                if (letter == previousLetter) {
                    counter++;
                    if (counter == 4) {
                        sequences++;
                    } else if (counter > 4) {
                        sequences--;
                    }
                } else {
                    counter = 1;
                }
                previousLetter = letter;
            }
        }
        return sequences;
    }

    public static int count_sequences(List<List<Character>> matriz) {
        int sequences = 0;
        sequences += row_horizontal(matriz);
        return sequences;
    }
}
