package com.example.icroqueta.utils;


public class ValidadorDNI {
    private final String dni;

    public ValidadorDNI(String dni) {
        this.dni = dni;
    }

    /**
     * Método para validar el DNI
     * @return true cuando está correcto, false cuando hay algún fallo
     */
    public boolean validar() {
        String letraMayuscula; //Guardaremos la letra introducida en formato mayúscula

        // Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra
        if (dni.length() != 9 || !Character.isLetter(this.dni.charAt(8))) {
            return false;
        }


        // Al superar la primera restricción, la letra la pasamos a mayúscula
        letraMayuscula = (this.dni.substring(8)).toUpperCase();

        // Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
        // Llamamos a los métodos privados de la clase soloNumeros() y letraDNI()
        return soloNumeros() && letraDNI().equals(letraMayuscula);
    }

    /**
     * Método para comprobar que los primeros dígitos del DNI son numéricos
     * @return true si solo son números y falso en caso de fallo
     */
    private boolean soloNumeros() {

        int i;
        int j;
        String numero; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
        StringBuilder miDNI = new StringBuilder(); // Guardamos en una cadena los números para después calcular la letra
        String[] unoNueve = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (i = 0; i < this.dni.length() - 1; i++) {
            numero = this.dni.substring(i, i + 1);

            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) {
                    miDNI.append(unoNueve[j]);
                }
            }
        }

        return miDNI.length() == 8;
    }

    /**
     * Comprueba que el último dígito sea una letra y se corresponda con el algoritmo de comprobación de los DNIs
     * @return el último dígito
     */
    private String letraDNI() {
        // El método es privado porque lo voy a usar internamente en esta clase, no se necesita fuera de ella
        // pasar miNumero a integer
        int miDNI = Integer.parseInt(this.dni.substring(0, 8));
        int resto;
        String miLetra;
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        resto = miDNI % 23;

        miLetra = asignacionLetra[resto];

        return miLetra;
    }
}
